package ru.vsu.cs.xmlpretty.tokens.tags;

import ru.vsu.cs.xmlpretty.tokens.Token;
import ru.vsu.cs.xmlpretty.tokens.tags.GeneralTag;

import java.util.ArrayList;

public class Tag extends GeneralTag {
    private final ArrayList<Token> content;

    public ArrayList<Token> getContent() {
        return content;
    }

    public Tag(String name) {
        super(name);
        content = new ArrayList<>();
    }

    public void addContent(ArrayList<Token> tokens) {
        content.addAll(tokens);
    }

    protected String getOpenTag(int intend, int depth) {
        String tag = SPACE.repeat(intend * depth) + START + getName();
        if (getAttributes().length() > 0)
            tag += GAP + getAttributes();
        tag += FINISH + NL;
        return tag;
    }

    private String getCloseTag(int intend, int depth) {
        return SPACE.repeat(intend * depth) + START + CLOSE + getName() + FINISH + NL;
    }

    @Override
    public String getToken(int intend, int depth) {
        StringBuilder tag = new StringBuilder(getOpenTag(intend, depth));
        for (Token token : content) {
            tag.append(token.getToken(intend, depth + 1));
        }
        tag.append(getCloseTag(intend, depth));
        return tag.toString();
    }
}
