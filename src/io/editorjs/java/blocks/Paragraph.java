package io.editorjs.java.blocks;

import io.editorjs.java.Block;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class Paragraph extends Block {

    String blockType;
    String text;

    @Override
    public Paragraph parse(JSONObject json) throws JSONException {
        JSONObject jsonData = json.getJSONObject("data");
        Paragraph block = new Paragraph();

        block.blockType = cleanAlphaNum(json.getString("type"));

        block.text = Jsoup.clean(jsonData.getString("text"), Whitelist.basic());

        return block;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("type", blockType);
            JSONObject data = new JSONObject();
            json.put("data", data);
            data.put("text", text);
        } catch (JSONException e) {
        }
        return json;
    }

    @Override
    public String toHTML() {
        return "<p>" + text + "</p>";
    }
}
