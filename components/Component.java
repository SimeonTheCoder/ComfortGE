package components;

import parser.Parser;

public class Component {
    public float[][] parsed;
    public String name;

    public Component(String name, String[] text) {
        this.name = name;

        parsed = Parser.parse(text);
    }
}
