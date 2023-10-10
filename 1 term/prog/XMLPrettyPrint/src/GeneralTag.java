import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class GeneralTag implements Token{
    private String name;
    private ArrayList<String> attributes;
    public String getName(){
        return name;
    }
    public GeneralTag(String name) {
        this.name = name;
    }
    public void addAttribute(String attribute) {
        attributes.add(attribute);
    }
    public String getAttributes(){
        return attributes.stream().collect(Collectors.joining(GAP));
    }
}
