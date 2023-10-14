package ru.vsu.cs.xmlpretty.file;

import ru.vsu.cs.xmlpretty.tokens.*;
import ru.vsu.cs.xmlpretty.tokens.tags.AutoClosableTag;
import ru.vsu.cs.xmlpretty.tokens.tags.GeneralTag;
import ru.vsu.cs.xmlpretty.tokens.tags.Tag;
import ru.vsu.cs.xmlpretty.tokens.tags.VersionTag;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class XML implements XMLConstants {
    private VersionTag root;
    private String xml;
    private char[] target;
    private int idx;
    private Stack<String> stack;
    public String getXml() {
        return xml;
    }
    public XML(String path) {
        this.readFromFile(path);
    }

    public void readFromFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            StringBuilder temp = new StringBuilder();
            if (path.endsWith(FORMAT)) {
                while (scanner.hasNext()) {
                    temp.append(scanner.nextLine());
                    temp.append(NEWLINE);
                }
                xml = temp.toString();
            } else {

                System.out.println(FORMATERROR);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPretty(int intend) {
        return root.getToken(intend, 0);
    }

    public void makePretty() {
        stack = new Stack<>();
        idx = 0;
        target = xml.toCharArray();
        parseVersion();
    }

    private ArrayList<Token> parseContent() {
        ArrayList<Token> tokens = new ArrayList<>();
        do {
            skipSpaces();
            if (target[idx] == START) {
                tokens.add(parseTag());
            } else {
                tokens.add(parseText());
            }
        } while (stack.size() > 1 && checkClose());
        return tokens;
    }

    private boolean checkClose() {
        boolean result = false;
        try {
            int back = idx;
            skipUntil(START);
            skipSpaces();
            if (target[idx] == CLOSE) {
                skipUntil(START);
                skipSpaces();
                String name = replaceSpaces(getUntil(FINISH), String.valueOf(SPACE)).split(String.valueOf(SPACE))[0];
                skipUntil(FINISH);
                if (!stack.pop().equals(name))
                    throw new SyntaxException(idx);
            } else {
                result = true;
                idx = back;
            }


        } catch (Exception e) {
            if (e instanceof SyntaxException err) {
                System.out.println(err.getDetails());
            } else {
                System.err.println();
            }
        }
        return result;
    }

    private Text parseText() {
        return new Text(getUntil(START));
    }

    private GeneralTag parseTag() {
        GeneralTag tag;
        skipUntil(START);
        skipSpaces();
        String[] aux = replaceSpaces(getUntil(FINISH), String.valueOf(SPACE)).split(String.valueOf(SPACE));
        String name = aux[0];
        skipUntil(FINISH);
        if (aux[aux.length - 1].contains(String.valueOf(CLOSE))) {
            int border;
            if (aux[aux.length - 1].equals(String.valueOf(CLOSE))) {
                border = aux.length - 1;
            } else {
                border = aux.length;
                name = aux[aux.length - 1].substring(0, aux[aux.length - 1].length() - 1);
            }

            tag = new AutoClosableTag(name);
            for (int i = 1; i < border; i++) {
                tag.addAttribute(aux[i]);
            }
        } else {
            tag = new Tag(name);
            for (int i = 1; i < aux.length; i++)
                tag.addAttribute(aux[i]);
            stack.push(name);
            ((Tag) tag).addContent(parseContent());
        }
        return tag;
    }

    private void parseVersion() {
        int back = idx;
        skipUntil(START);
        if (target[idx] == QM) {
            skipUntil(QM);
            skipSpaces();
            root = new VersionTag(getUntil(SPACE));
            String[] aux = replaceSpaces(getUntil(QM), String.valueOf(SPACE)).split(String.valueOf(SPACE));
            for (String attribute : aux) {
                root.addAttribute(attribute);
            }
        } else {
            idx = back;
            root = new VersionTag(XML);
            root.addAttribute(VERSIONATTRIBUTE1);
            root.addAttribute(VERSIONATTRIBUTE2);
        }
        stack.push(root.getName());
        root.addContent(parseContent());
    }

    private String getUntil(char border) {
        StringBuilder substring = new StringBuilder();
        while (target[idx] != border) {
            substring.append(target[idx]);
            idx++;
        }
        return substring.toString();
    }

    public String replaceSpaces(String target, String replacement) {
        char[] aux = target.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < aux.length; i++) {
            if (aux[i - 1] == SPACE && aux[i] != SPACE) {
                result.append(replacement);
            } else if (aux[i - 1] != SPACE) {
                result.append(aux[i - 1]);
            }
        }
        if (aux[aux.length - 1] != SPACE) {
            result.append(aux[aux.length - 1]);
        }
        return result.toString();
    }

    private void skipUntil(char border) {
        while (target[idx] != border && idx < target.length - 1) {
            idx++;
        }
        idx++;
    }

    private void skipSpaces() {
        for (; idx < target.length - 1; idx++) {
            if (target[idx] != SPACE && target[idx] != NEWLINE) {
                return;
            }
        }
    }

    public void printFile() {
        System.out.println(xml);
    }

}
