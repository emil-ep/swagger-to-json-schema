package org.xperia.jsonproperties;

import net.minidev.json.JSONObject;

public class JsonNumberProperty extends JsonProperty {

    protected String format;

    public JsonNumberProperty(String format, String description) {
        super("number", description);
        this.format = format;
    }

    public JsonNumberProperty(String type, String format, String description){
        super(type, description);
        this.format = format;
    }

    @Override
    protected JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", this.type);
        jsonObject.put("description", this.description);
        if (this.format != null)
            jsonObject.put("format", this.format);
        return jsonObject;
    }


}
