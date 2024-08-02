package frameworks.data_access.serialization;

import entity.Nutrition;
import org.bson.Document;

import java.util.Map;
import java.util.TreeMap;

public class NutritionSerializer implements Serializer<Document, Nutrition> {

    @Override
    public Document serialize(Nutrition nutrition) {
        Document document = new Document()
                .append("label", nutrition.getLabel())
                .append("unit", nutrition.getUnit())
                .append("quantity", nutrition.getQuantity())
                .append("percentage", nutrition.getPercentage());

        return document;
    }

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

    public Document serializeMap(Map<String, Nutrition> map){
        Document document = new Document();
        for (Map.Entry<String, Nutrition> entry : map.entrySet()) {
            document.append(entry.getKey(), serialize(entry.getValue()));
        }
        return document;
    }

    public Map<String, Nutrition> deserializeMap(Document bson){
        Map<String, Nutrition> map = new TreeMap<>();
        for (String key : bson.keySet()){
            map.put(key, deserialize(bson.get(key, Document.class)));
        }

        return map;
    }



}
