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
    private final String path;
    private final ArrayList<SyntaxException> exceptions;
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
        this.path = path;
        exceptions = new ArrayList<>();
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
        skipUntil(START);
        idx++;
        parseVersion();
    }

    private ArrayList<Token> parseContent(String parent) {
        ArrayList<Token> tokens = new ArrayList<>();
        do {
            skipSpaces();
            switch (target[idx]) {
                case START -> tokens.add(parseTag());
                default -> tokens.add(parseText());
            }
        } while (stack.size() > 1 && checkClose(parent));
        return tokens;
    }

    private boolean checkClose(String parent) {
        boolean result = false;
        try {
            int back = idx;
            skipUntil(START);
            idx++;
            skipSpaces();
            if (target[idx] == CLOSE) {
                idx++;
                String name = replaceSpaces(getUntil(FINISH), String.valueOf(SPACE)).split(String.valueOf(SPACE))[0];
                idx++;
                if (stack.pop().equals(name))
                    result = false;
                else {
                    throw new SyntaxException(idx);
                }
            } else {
                result = true;
                idx = back;
            }


        } catch (Exception e) {
            if (e instanceof SyntaxException err) {
                System.out.println(err.getDetails());
            } else {
                System.out.println(e.getCause());
            }
        }
        return result;
    }

    private Text parseText() {
        return new Text(getUntil(START));
    }

    private GeneralTag parseTag() {
        idx++;
        GeneralTag tag;
        String[] aux = replaceSpaces(getUntil(FINISH), String.valueOf(SPACE)).split(String.valueOf(SPACE));
        String name = aux[0];
        idx++;
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
            ((Tag) tag).addContent(parseContent(tag.getName()));
        }
        return tag;
    }

    private void parseVersion() {
        if (target[idx] == QM) {
            idx++;
            root = new VersionTag(getUntil(SPACE));
            String[] aux = replaceSpaces(getUntil(QM), String.valueOf(SPACE)).split(String.valueOf(SPACE));
            for (String attribute : aux) {
                root.addAttribute(attribute);
            }
        } else {
            idx--;
            root = new VersionTag(XML);
            root.addAttribute(VERSIONATTRIBUTE1);
            root.addAttribute(VERSIONATTRIBUTE2);
        }
        stack.push(root.getName());
        root.addContent(parseContent(root.getName()));
    }

    private String getUntil(char border) {
        String substring = "";
        while (target[idx] != border) {
            substring += target[idx];
            idx++;
        }
        return substring;
    }

    public String replaceSpaces(String target, String replacement) {
        char[] aux = target.toCharArray();
        String result = "";
        for (int i = 1; i < aux.length; i++) {
            if (aux[i - 1] == SPACE && aux[i] != SPACE) {
                result += replacement;
            } else if (aux[i - 1] != SPACE) {
                result += aux[i - 1];
            }
        }
        if (aux[aux.length - 1] != SPACE) {
            result += aux[aux.length - 1];
        }
        return result;
    }

    private void skipUntil(char border) {
        while (target[idx] != border && idx < target.length - 1) {
            idx++;
        }
    }

    private void skipSpaces() {
        for (; idx < target.length - 1; idx++) {
            if (target[idx] != SPACE && target[idx] != NEWLINE) {
                return;
            }
        }
    }

    public void printExceptions() {
        for (var item : exceptions) {
            System.out.println(item.getDetails());
        }

    }

    public void printFile() {
        System.out.println(xml);
    }

}
