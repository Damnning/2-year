package ru.vsu.cs.xmlpretty.tokens.tags;

import ru.vsu.cs.xmlpretty.tokens.tags.GeneralTag;

public class AutoClosableTag extends GeneralTag {
    public AutoClosableTag(String name) {
        super(name);
    }

    private String getTag(int intend, int depth) {
        String tag = SPACE.repeat(intend * depth) + START + getName();
        if (getAttributes().length() > 0)
            tag += GAP + getAttributes();
        tag += CLOSE + FINISH + NL;
        return tag;
    }

    @Override
    public String getToken(int intend, int depth) {
        return getTag(intend, depth);
    }
}
