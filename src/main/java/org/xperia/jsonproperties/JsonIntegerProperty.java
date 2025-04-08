package org.xperia.jsonproperties;

import net.minidev.json.JSONObject;

import java.math.BigDecimal;

public class JsonIntegerProperty extends JsonNumberProperty{

    private String pattern;

    private BigDecimal minimum;

    private BigDecimal maximum;


    public JsonIntegerProperty(String format, String description) {
        super("integer", format, description);
    }

    @Override
    protected JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", this.type);
        jsonObject.put("description", this.description);
        if (this.format != null)
            jsonObject.put("format", this.format);
        if (this.minimum != null)
            jsonObject.put("minimum", this.minimum);
        if (this.maximum != null)
            jsonObject.put("maximum", this.maximum);
        return jsonObject;
    }


    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }
}
