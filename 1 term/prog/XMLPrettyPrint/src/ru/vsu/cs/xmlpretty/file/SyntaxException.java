package ru.vsu.cs.xmlpretty.file;

public class SyntaxException extends Exception {
    private final int symbol;

    public SyntaxException(int symbol) {
        this.symbol = symbol;
    }

    public String getDetails() {
        return "Invalid syntax at " + symbol;
    }
}
