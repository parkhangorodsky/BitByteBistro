package frameworks.data_access.serialization;


import entity.Ingredient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class IngredientSerializer implements Serializer<Document, Ingredient> {
    @Override
    public Document serialize(Ingredient ingredient) {
        Document document = new Document()
                .append("ingredientID", ingredient.getIngredientID())
                .append("ingredientName", ingredient.getIngredientName())
                .append("quantity", ingredient.getQuantity())
                .append("quantityUnit", ingredient.getQuantityUnit())
                .append("category", ingredient.getCategory());

        return document;
    }

    @Override
    public Ingredient deserialize(Document bson) {
        String ingredientID = bson.getString("ingredientID");
        String ingredientName = bson.getString("ingredientName");
        int quantity = bson.getDouble("quantity").intValue();
        String quantityUnit = bson.getString("quantityUnit");
        String category = bson.getString("category");

        return new Ingredient(ingredientID, ingredientName, quantityUnit, category, quantity);
    }

    public List<Ingredient> deserializeList(List<Document> bsonList) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Document document : bsonList) {
            ingredients.add(deserialize(document));
        }
        return ingredients;
    }

    public List<Document> serializeList(List<Ingredient> ingredients) {
        List<Document> bsonList = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            bsonList.add(serialize(ingredient));
        }
        return bsonList;
    }
}
