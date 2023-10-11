public class Text implements Token {
    private String content;
    public Text(String content){
        this.content = content;
    }
    @Override
    public String getToken(int intend, int depth) {
        return SPACE.repeat(intend*depth) + content + NL;
    }
}
