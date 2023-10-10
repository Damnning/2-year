
public class AutoClosableTag extends GeneralTag{
    public AutoClosableTag(String name) {
        super(name);
    }
    private String getTag(){
        return START + getName() + GAP + getAttributes() + CLOSE + FINISH;
    }
    @Override
    public String getToken() {
        return getTag();
    }
}
