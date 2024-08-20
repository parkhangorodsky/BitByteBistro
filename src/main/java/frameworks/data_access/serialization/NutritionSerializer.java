package frameworks.data_access.serialization;

import entity.Nutrition;
import org.bson.Document;

import java.util.Map;
import java.util.TreeMap;

/**
 * Serializer class for converting {@code Nutrition} objects to and from MongoDB {@code Document} objects.
 * This class implements the {@code Serializer} interface to handle the serialization and deserialization
 * of {@code Nutrition} instances, including handling of maps of nutrition data.
 */
public class NutritionSerializer implements Serializer<Document, Nutrition> {

    /**
     * Serializes a {@code Nutrition} object to a MongoDB {@code Document}.
     *
     * This method converts the properties of a {@code Nutrition} instance into a {@code Document} format
     * suitable for storage in MongoDB.
     *
     * @param nutrition The {@code Nutrition} object to be serialized.
     * @return A {@code Document} representing the serialized form of the {@code Nutrition} instance.
     */
    @Override
    public Document serialize(Nutrition nutrition) {
        Document document = new Document()
                .append("label", nutrition.getLabel())
                .append("unit", nutrition.getUnit())
                .append("quantity", nutrition.getQuantity())
                .append("percentage", nutrition.getPercentage());

        return document;
    }

    /**
     * Deserializes a MongoDB {@code Document} to a {@code Nutrition} object.
     *
     * This method converts a {@code Document} retrieved from MongoDB back into a {@code Nutrition} instance.
     * It handles the optional presence of the percentage field.
     *
     * @param bson The {@code Document} representing the serialized form of a {@code Nutrition} instance.
     * @return A {@code Nutrition} object created from the {@code Document}.
     */
    @Override
    public Nutrition deserialize(Document bson) {
        String label = bson.getString("label");
        String unit = bson.getString("unit");
        float quantity = bson.getDouble("quantity").floatValue();

        if (bson.getDouble("percentage") != null) {
            Float percentage = bson.getDouble("percentage").floatValue();
            return new Nutrition(label, quantity, unit, percentage);
        } else {
            return new Nutrition(label, quantity, unit);
        }
    }

    /**
     * Serializes a map of {@code Nutrition} objects to a MongoDB {@code Document}.
     *
     * This method converts each {@code Nutrition} instance in the map to a {@code Document} and stores them
     * in a {@code Document} where the map keys are used as field names.
     *
     * @param map A map of {@code Nutrition} objects to be serialized, where the keys are field names.
     * @return A {@code Document} representing the serialized form of the map of {@code Nutrition} instances.
     */
    public Document serializeMap(Map<String, Nutrition> map){
        Document document = new Document();
        for (Map.Entry<String, Nutrition> entry : map.entrySet()) {
            document.append(entry.getKey(), serialize(entry.getValue()));
        }
        return document;
    }

    /**
     * Deserializes a MongoDB {@code Document} to a map of {@code Nutrition} objects.
     *
     * This method converts each {@code Document} within the main {@code Document} back into a {@code Nutrition}
     * instance and stores them in a map, where the document keys are used as map keys.
     *
     * @param bson The {@code Document} representing a map of serialized {@code Nutrition} instances.
     * @return A map of {@code Nutrition} objects created from the {@code Document}.
     */
    public Map<String, Nutrition> deserializeMap(Document bson){
        Map<String, Nutrition> map = new TreeMap<>();
        for (String key : bson.keySet()){
            map.put(key, deserialize(bson.get(key, Document.class)));
        }

        return map;
    }



}
