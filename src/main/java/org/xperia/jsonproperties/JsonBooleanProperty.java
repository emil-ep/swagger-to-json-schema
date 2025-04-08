package org.xperia.jsonproperties;

import net.minidev.json.JSONObject;

public class JsonBooleanProperty extends JsonProperty{


    public JsonBooleanProperty(String description) {
        super("boolean", description);
    }

    @Override
    protected JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", this.type);
        jsonObject.put("description", this.description);
        return jsonObject;
    }
}
