package assets;

import components.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ScriptHolder {
    public HashMap<String, Component> components;

    public ScriptHolder() {
        components = new LinkedHashMap<>();
    }
}
