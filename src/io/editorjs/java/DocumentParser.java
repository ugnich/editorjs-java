package io.editorjs.java;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DocumentParser {

    HashMap<String, Block> parsers = new HashMap<>();

    public void addParser(String type, Block blockParser) {
        parsers.put(type, blockParser);
    }

    public Document parseJSON(JSONObject json) {
        Document document = new Document();

        if (json.has("time")) {
            try {
                document.time = json.getLong("time");
            } catch (JSONException e) {
            }
        }

        if (json.has("blocks")) {
            try {
                JSONArray jsonBlocks = json.getJSONArray("blocks");
                for (int i = 0; i < jsonBlocks.length(); i++) {
                    try {
                        JSONObject jsonBlock = jsonBlocks.getJSONObject(i);
                        String blockType = jsonBlock.getString("type");
                        Block parser = parsers.get(blockType);
                        if (parser != null) {
                            Block block = parser.parse(jsonBlock);
                            if (block != null) {
                                document.blocks.add(block);
                            }
                        }
                    } catch (JSONException e) {
                    }
                }
            } catch (JSONException e) {
            }
        }

        if (json.has("version")) {
            try {
                document.version = json.getString("version");
            } catch (JSONException e) {
            }
        }

        return document;
    }
}
