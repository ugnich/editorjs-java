package io.editorjs.java;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Block {

    public abstract Block parse(JSONObject json) throws JSONException;

    public abstract JSONObject toJSONObject();

    public abstract String toHTML();

    public static String cleanAlphaNum(String str) {
        return str.replaceAll("[^A-Za-z0-9\\-_]", "");
    }
}
