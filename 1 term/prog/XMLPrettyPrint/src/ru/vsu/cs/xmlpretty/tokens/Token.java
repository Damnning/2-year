package ru.vsu.cs.xmlpretty.tokens;

public interface Token {
    String GAP = " ", START = "<", FINISH = ">", CLOSE = "/", NL = "\n", QM = "?", SPACE = " ";

    String getToken(int intend, int depth);
}

