package org.xperia.jsonproperties;

import net.minidev.json.JSONObject;

import java.util.Map;

public class JsonObjectProperty extends JsonProperty{

    private Map<String, JsonProperty> properties;


    public JsonObjectProperty(Map<String, JsonProperty> properties, String description){
        super("object", description);
        this.properties = properties;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", this.type);
        jsonObject.put("description", this.description);
        JSONObject propertyObject = new JSONObject();
        this.properties.forEach((propertyName, jsonProperty) -> {
            if (jsonProperty != null) {
                JSONObject object = jsonProperty.toJsonObject();
                propertyObject.put(propertyName, object);
            }
        });
        jsonObject.put("properties", propertyObject);
        return jsonObject;
    }


    public Map<String, JsonProperty> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, JsonProperty> properties) {
        this.properties = properties;
    }

}
