import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Pattern;

public class XML {
    private final String FORMATERR = "Not an xml file";
    private final String FORMAT = ".xml";
    private final String NEWLINE = "\n";
    private final char GAP = ' ';
    private final char OPEN = '<';
    private final char CLOSE = '>';

    public String getXml() {
        return xml;
    }

    private final String path;
    private final ArrayList<SyntaxException> exceptions;
    private String xml;

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
                System.out.println(FORMATERR);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void makePretty() {
        try {
            int indent = 4;
            int depth = 0;
            int row = 0;
            boolean skipSpaces = true;
            boolean insideRoot = false;
            boolean insideTag = false;
            boolean checkClose = false;
            boolean skipTagAttributes = false;
            boolean isClosingTag = false;
            boolean needNewLine = true;
            boolean isException = false;
            Stack<String> syntaxStack = new Stack<>();
            char[] aux;
            StringBuilder pretty = new StringBuilder();
            StringBuilder tag = new StringBuilder();




            /*while (scanner.hasNext()) {
                aux = scanner.nextLine().toCharArray();
                row++;
                  for (int i = 0; i < aux.length; i++) {
                    // Checking if spaces could be skipped
                    if ((!skipSpaces || aux[i] != ' ') && !skipTagAttributes) {
                        // Checking if pointer inside root
                        if (!insideRoot && aux[i] != '<')
                            exceptions.add(new SyntaxException(i,row));
                        // Checking if its close tag
                        if(checkClose) {
                            checkClose = false;
                            if (aux[i] == '/') {
                                // If it's close check tags nesting
                                pretty.append(aux[i]);
                                i++;
                                char[] prevTag = syntaxStack.pop().toCharArray();
                                int j = 0;
                                while (j < prevTag.length)
                                {
                                    if (aux[i + j] != prevTag[j] && !isException) {
                                        exceptions.add(new SyntaxException(row, i + j));
                                        isException = true;
                                    }

                                    pretty.append(aux[i + j]);
                                    j++;
                                }
                                isException = false;
                                i +=j;
                                isClosingTag = true;
                            }

                        }
                        //  Tag start check
                        if (!insideTag && aux[i] == '<') {
                            if (!insideRoot) insideRoot = true;
                            insideTag = true;
                            checkClose = true;
                            skipSpaces = false;
                            if(aux[i+1]=='/') depth--;
                            pretty.append(getIntend(indent,depth));
                            if(aux[i+1]!='/') depth++;
                            pretty.append(aux[i]);
                        // Tag ending check
                        } else if (insideTag && aux[i] == '>') {
                            insideTag = false;
                            pretty.append(aux[i]);
                            if(aux[i-1] =='/') depth--;
                            if(!isClosingTag && aux[i-1] !='/') syntaxStack.push(tag.toString());
                            isClosingTag = false;
                            skipSpaces = true;
                            needNewLine = true;
                            tag.setLength(0);
                        // Tag body appending
                        } else if (insideTag) {
                            if(aux[i] == ' ') {
                                pretty.append(aux[i]);
                                skipTagAttributes = true;
                            }
                            else{
                                tag.append(aux[i]);
                                pretty.append(aux[i]);
                            }
                        // Content append
                        } else {
                            if(needNewLine) {
                                pretty.append(getIntend(indent, depth));
                            }
                            pretty.append(aux[i]);
                            needNewLine = false;
                            skipSpaces = false;
                        }

                    } else if (insideTag) {
                        if(aux[i] == '>'){
                            i--;
                            skipTagAttributes = false;
                        }
                        if(skipTagAttributes) pretty.append(aux[i]);
                    }

                }
            }
            xml =  pretty.toString();*/
        } catch (Exception e) {
            if (e instanceof SyntaxException err) {
                System.out.println(err.getDetails());
            } else {
                System.out.println(e.getMessage());
            }
        }

    }

    private String getIntend(int indent, int depth) {
        return "\n" +
                String.join("", Collections.nCopies(indent * depth, " "));
    }

    private void parseContent(char[] target, int idx) {

    }

    private int skipSpaces(int i, char[] target) {
        i++;
        for (; i < target.length; i++) {
            if (target[i] != GAP)
                return i;
        }
        return i;
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
