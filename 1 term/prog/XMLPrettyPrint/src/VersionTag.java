public class VersionTag extends Tag{
    public VersionTag(String name) {
        super(name);
    }
    private String getTag(){
        return START + QM + getName() + GAP + getAttributes() + QM + FINISH + NL;
    }
    @Override
    public String getToken(int intend, int depth){
        String tag = getTag();
        for (Token token:getContent()) {
            tag +=token.getToken(intend, depth);
        }
        return tag;
    }
}
