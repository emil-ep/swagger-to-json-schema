package org.xperia.jsonproperties;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public abstract class JsonProperty {

    protected String type;

    protected JSONArray required;

    protected String description;


    protected JsonProperty(String type, String description){
        this.type = type;
        this.description = description;
    }

    protected abstract JSONObject toJsonObject();

}
