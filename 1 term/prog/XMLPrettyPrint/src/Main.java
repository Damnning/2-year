import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        XML file = new XML("input.xml");
        file.printFile();
        file.makePretty();
        System.out.println(String.join("", Collections.nCopies(10,"-")));
        file.printFile();
        file.printExceptions();
    }
}