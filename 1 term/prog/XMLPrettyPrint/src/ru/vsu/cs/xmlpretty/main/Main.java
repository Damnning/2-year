package ru.vsu.cs.xmlpretty.main;

import ru.vsu.cs.xmlpretty.file.XML;

public class Main {
    public static void main(String[] args) {
        XML file = new XML("input.xml");
        file.makePretty();
        System.out.println(file.getPretty(4));

    }
}