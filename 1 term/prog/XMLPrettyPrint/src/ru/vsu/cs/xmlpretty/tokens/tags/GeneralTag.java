package ru.vsu.cs.xmlpretty.tokens.tags;

import ru.vsu.cs.xmlpretty.tokens.Token;

import java.util.ArrayList;

public abstract class GeneralTag implements Token {
    private final String name;
    private final ArrayList<String> attributes;

    public String getName() {
        return name;
    }

    public GeneralTag(String name) {
        this.name = name;
        attributes = new ArrayList<>();
    }

    public void addAttribute(String attribute) {
        attributes.add(attribute);
    }

    public String getAttributes() {
        return String.join(GAP, attributes);
    }

    @Override
    public String getToken(int intend, int depth) {
        return null;
    }
}
