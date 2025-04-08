package org.xperia.jsonproperties;

import io.swagger.models.properties.Property;
import net.minidev.json.JSONObject;

import java.util.Map;

public class JsonArrayProperty extends JsonProperty{


    private JsonProperty items;


    public JsonArrayProperty(String description){
        super("array", description);
    }

    @Override
    protected JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", this.type);
        jsonObject.put("description", this.description);
        jsonObject.put("items", this.items.toJsonObject());
        return jsonObject;
    }

    public void loadPropertiesFromMap(Map<String, JsonProperty> propertyMap){
        this.items = new JsonObjectProperty(propertyMap, this.description);
    }

    public void loadChildProperty(Property childProperty){
        this.items = PropertyFactory.createJsonProperty(childProperty);
    }

}
