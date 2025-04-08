package org.xperia;

import io.swagger.models.Model;
import io.swagger.models.Swagger;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.parser.SwaggerParser;
import net.minidev.json.JSONObject;
import org.xperia.jsonproperties.JsonArrayProperty;
import org.xperia.jsonproperties.JsonObjectProperty;
import org.xperia.jsonproperties.JsonProperty;
import org.xperia.jsonproperties.PropertyFactory;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String jsonSchema = generateJsonSchemaFromSwaggerSpec("/Users/emil/Desktop/petstore.json", "Pet");
        System.out.println(jsonSchema);
    }

    public static String generateJsonSchemaFromSwaggerSpec(String swaggerPath, String fieldName){

        Swagger swagger = new SwaggerParser().read(swaggerPath);
        Map<String, Model> definitions = swagger.getDefinitions();
        Model schemaGenerationDefinition = definitions.get(fieldName);
        Map<String, Property> propertyMap = schemaGenerationDefinition.getProperties();

        Map<String, JsonProperty> customJsonPropertyMap = new HashMap<>();
        propertyMap.forEach((propertyName, property) -> {
            JsonProperty jsonProperty = processSwaggerProperties(propertyName, property, definitions);
            customJsonPropertyMap.put(propertyName, jsonProperty);
        });
        JsonObjectProperty objectProperty = new JsonObjectProperty(customJsonPropertyMap, schemaGenerationDefinition.getDescription());
        JSONObject generatedObject = objectProperty.toJsonObject();
        String jsonSchema = generatedObject.toJSONString();
        return jsonSchema;
    }

    private static JsonProperty processReference(String referenceName, String type, Map<String, Model> definitions){

        Model model = definitions.get(referenceName);
        Map<String, Property> propertyMap = model.getProperties();
        Map<String, JsonProperty> jsonPropertyMap = new HashMap<>();
        propertyMap.forEach((propertyName, property) -> {
            JsonProperty jsonProperty = processSwaggerProperties(propertyName, property, definitions);
            jsonPropertyMap.put(propertyName, jsonProperty);
        });
        if (type.equalsIgnoreCase("array")){
            JsonArrayProperty jsonProperty = new JsonArrayProperty(model.getDescription());
            jsonProperty.loadPropertiesFromMap(jsonPropertyMap);
            return jsonProperty;
        }else{
            JsonObjectProperty objectProperty = new JsonObjectProperty(jsonPropertyMap, model.getDescription());
            return objectProperty;
        }
    }

    private static JsonProperty processSwaggerProperties(String propertyName, Property property, Map<String, Model> propertyDefinitions){
        String definitionRefPath = "";
        String type = "";
        JsonProperty jsonProperty = null;
        if (property.getType().equalsIgnoreCase("ref")){
            definitionRefPath = ((RefProperty) property).getOriginalRef();
            type = "object";
        }else if (property.getType().equalsIgnoreCase("array")){
            type = "array";
            Property childProperty = ((ArrayProperty) property).getItems();
            if (childProperty instanceof RefProperty){
                RefProperty refProperty = (RefProperty) ((ArrayProperty) property).getItems();
                definitionRefPath = refProperty.getOriginalRef();
            }else{
                JsonArrayProperty arrayProperty = new JsonArrayProperty(property.getDescription());
                arrayProperty.loadChildProperty(childProperty);
                return arrayProperty;
            }
        }else{
            jsonProperty = PropertyFactory.createJsonProperty(property);
            return jsonProperty;
        }
        String[] splitResult = definitionRefPath.split("/");
        if (splitResult.length == 3) {
            String propertyPath = splitResult[2];
            System.out.println(propertyPath);
            jsonProperty = processReference(propertyPath, type, propertyDefinitions);
        }
        return jsonProperty;
    }
}