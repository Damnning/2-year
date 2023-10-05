import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class XML {
    public String getXml() {
        return xml;
    }
    private String path;

    private String xml;

    public XML(String path) {
        this.readFromFile(path);
        this.path = path;
    }

    public void readFromFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            StringBuilder temp = new StringBuilder();
            if (path.endsWith(".xml")) {
                while (scanner.hasNext()) {
                    temp.append(scanner.nextLine());
                    temp.append("\n");
                }
                xml = temp.toString();
            } else {
                System.out.println("Not an xml file");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void makePretty() {
        try (Scanner scanner = new Scanner(new File(path));) {
            int indent = 4;
            int depth = 0;
            int row = 0;
            boolean skipSpaces = true;
            boolean insideTag = false;
            boolean insideRoot = false;
            boolean checkClose = false;
            boolean skipTagAttributes = false;
            boolean isClosingTag = false;
            boolean needNewLine = true;
            Stack<String> syntaxStack = new Stack<>();
            char[] aux;
            StringBuilder pretty = new StringBuilder();
            StringBuilder tag = new StringBuilder();
            while (scanner.hasNext()) {
                aux = scanner.nextLine().toCharArray();
                row++;
                  for (int i = 0; i < aux.length; i++) {
                    // Checking if spaces could be skipped
                    if ((!skipSpaces || aux[i] != ' ') && !skipTagAttributes) {
                        // Checking if pointer inside root
                        if (!insideRoot && aux[i] != '<')
                            throw new SyntaxException(i, row);
                        // Checking if its close tag
                        if(checkClose) {
                            checkClose = false;
                            if (aux[i] == '/') {
                                pretty.append(aux[i]);
                                i++;
                                char[] prevTag = syntaxStack.pop().toCharArray();
                                int j = 0;
                                while (j < prevTag.length)
                                {
                                    if (aux[i + j] != prevTag[j])
                                        throw new SyntaxException(row, i + j);
                                    pretty.append(aux[i + j]);
                                    j++;
                                }
                                i +=j;
                                isClosingTag = true;

                            }

                        }
                        if (!insideTag && aux[i] == '<') {
                            if (!insideRoot) insideRoot = true;
                            insideTag = true;
                            checkClose = true;
                            skipSpaces = false;
                            if(aux[i+1]=='/') depth--;
                            pretty.append(getIntend(indent,depth));
                            if(aux[i+1]!='/') depth++;

                            pretty.append(aux[i]);
                        } else if (insideTag && aux[i] == '>') {
                            insideTag = false;
                            pretty.append(aux[i]);
                            if(aux[i-1] =='/') depth--;
                            if(!isClosingTag && aux[i-1] !='/') syntaxStack.push(tag.toString());
                            isClosingTag = false;
                            skipSpaces = true;
                            needNewLine = true;
                            tag.setLength(0);
                        } else if (insideTag) {
                            if(aux[i] == ' ') {
                                pretty.append(aux[i]);
                                skipTagAttributes = true;
                            }
                            else{
                                tag.append(aux[i]);
                                pretty.append(aux[i]);
                            }
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
            xml =  pretty.toString();
        } catch (Exception e) {
            if (e instanceof SyntaxException err) {
                System.out.println(err.getDetails());
            } else {
                System.out.println(e.getMessage());
            }
        }

    }
    private String getIntend(int indent, int depth){
        StringBuilder str = new StringBuilder();
        str.append("\n");
        str.append(String.join("", Collections.nCopies(indent * depth, " ")));
        return str.toString();
    }
    public void printFile() {
        System.out.println(xml);
    }

}
