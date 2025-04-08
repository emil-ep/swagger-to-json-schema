package org.xperia.jsonproperties;

import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.LongProperty;
import io.swagger.models.properties.Property;

public class PropertyFactory {


    public static JsonProperty createJsonProperty(Property property){
        JsonProperty jsonProperty = null;
        switch (property.getType()){
            case "number":
                jsonProperty = new JsonNumberProperty(property.getFormat(), property.getDescription());
                break;
            case "string":
                jsonProperty = new JsonStringProperty(property.getDescription());
                break;
            case "boolean":
                jsonProperty = new JsonBooleanProperty(property.getDescription());
                break;
            case "integer":
                jsonProperty = new JsonIntegerProperty(property.getFormat(), property.getDescription());
                if (property instanceof IntegerProperty){
                    IntegerProperty integerProperty = (IntegerProperty) property;
                    if (integerProperty.getMinimum() != null)
                        ((JsonIntegerProperty) jsonProperty).setMinimum(integerProperty.getMinimum());
                    if (integerProperty.getMaximum() != null)
                        ((JsonIntegerProperty) jsonProperty).setMaximum(integerProperty.getMaximum());
                }else if (property instanceof LongProperty){
                    LongProperty longProperty = (LongProperty) property;
                    if (longProperty.getMinimum() != null)
                        ((JsonIntegerProperty) jsonProperty).setMinimum(longProperty.getMinimum());
                    if (longProperty.getMaximum() != null)
                        ((JsonIntegerProperty) jsonProperty).setMaximum(longProperty.getMaximum());
                }
                break;
            default:
                System.out.println("Unhandled type");
        }

        return jsonProperty;
    }
}
