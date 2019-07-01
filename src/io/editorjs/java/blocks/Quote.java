package io.editorjs.java.blocks;

import io.editorjs.java.Block;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class Quote extends Block {

    String blockType;
    String text;
    String caption;

    @Override
    public Quote parse(JSONObject json) throws JSONException {
        JSONObject jsonData = json.getJSONObject("data");
        Quote block = new Quote();

        block.blockType = cleanAlphaNum(json.getString("type"));

        block.text = Jsoup.clean(jsonData.getString("text"), Whitelist.none());

        block.caption = Jsoup.clean(jsonData.getString("caption"), Whitelist.none());

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
            data.put("caption", caption);
        } catch (JSONException e) {
        }
        return json;
    }

    @Override
    public String toHTML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<blockquote><p>").append(text).append("</p>");
        if (caption != null && !caption.isEmpty()) {
            sb.append("<footer>").append(caption).append("</footer>");
        }
        sb.append("</blockquote>");
        return sb.toString();
    }
}
