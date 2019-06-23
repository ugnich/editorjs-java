package io.editorjs.java;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Document {

    long time = 0;
    ArrayList<Block> blocks = new ArrayList<>();
    String version = null;

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            if (time > 0) {
                json.put("time", time);
            }

            JSONArray jsonBlocks = new JSONArray();
            json.put("blocks", jsonBlocks);
            for (Block block : blocks) {
                jsonBlocks.put(block.toJSONObject());
            }

            if (version != null && !version.isEmpty()) {
                json.put("version", version);
            }
        } catch (JSONException e) {
        }
        return json;
    }

    public String toHTML() {
        StringBuilder sb = new StringBuilder();
        for (Block block : blocks) {
            sb.append(block.toHTML());
        }
        return sb.toString();
    }
}
