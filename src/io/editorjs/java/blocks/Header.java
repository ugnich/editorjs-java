package io.editorjs.java.blocks;

import io.editorjs.java.Block;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class Header extends Block {

    String blockType;
    int level;
    String text;

    @Override
    public Header parse(JSONObject json) throws JSONException {
        JSONObject jsonData = json.getJSONObject("data");
        Header block = new Header();

        block.blockType = cleanAlphaNum(json.getString("type"));

        block.level = jsonData.getInt("level");
        if (block.level < 1) {
            block.level = 1;
        } else if (block.level > 6) {
            block.level = 6;
        }

        block.text = Jsoup.clean(jsonData.getString("text"), Whitelist.none());

        return block;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("type", blockType);
            JSONObject data = new JSONObject();
            json.put("data", data);
            data.put("level", level);
            data.put("text", text);
        } catch (JSONException e) {
        }
        return json;
    }

    @Override
    public String toHTML() {
        return "<h" + level + ">" + text + "</h" + level + ">";
    }
}
