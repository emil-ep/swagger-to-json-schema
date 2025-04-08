package org.xperia.jsonproperties;

import net.minidev.json.JSONObject;

public class JsonStringProperty extends JsonProperty{

    public JsonStringProperty(String description) {
        super("string", description);
    }

    @Override
    protected JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", this.type);
        jsonObject.put("description", this.description);
        return jsonObject;
    }
}
