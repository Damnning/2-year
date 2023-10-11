import java.util.ArrayList;

public class Tag extends GeneralTag {
    private ArrayList<Token> content;
    public ArrayList<Token> getContent(){
        return content;
    }
    public Tag(String name) {
        super(name);
        content = new ArrayList<>();
    }
    public void addContent(ArrayList<Token> tokens){
        content.addAll(tokens);
    }
    protected String getOpenTag(int intend, int depth) {
        String tag = SPACE.repeat(intend*depth) + START + getName();
        if(getAttributes().length() > 0)
            tag += GAP + getAttributes();
        tag += FINISH + NL;
        return tag;
    }
    private String getCloseTag(int intend, int depth){
        return SPACE.repeat(intend*depth) + START + CLOSE + getName()  + FINISH + NL;
    }
    @Override
    public String getToken(int intend, int depth) {
        String tag = getOpenTag(intend, depth);
        for (Token token:content) {
            tag += token.getToken(intend, depth + 1);
        }
        tag +=getCloseTag(intend, depth);
        return tag;
    }
}
