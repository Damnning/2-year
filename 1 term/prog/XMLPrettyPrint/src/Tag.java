import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tag extends GeneralTag {
    private ArrayList<Token> content;
    public Tag(String name) {
        super(name);
    }
    public void addContent(Token token){
        content.add(token);
    }
    private String getOpenTag() {
        return START + getName() + GAP + getAttributes() + FINISH;
    }
    private String getCloseTag(){
        return NL + START + getName() + CLOSE  + FINISH;
    }
    @Override
    public String getToken() {
        String tag = getOpenTag();
        for (Token token:content) {
            tag +=token.getToken();
        }
        tag +=getCloseTag();
        return tag;
    }
}
