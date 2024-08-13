package frameworks.data_access.serialization;


import entity.Ingredient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Serializer class for converting {@code Ingredient} objects to and from MongoDB {@code Document} objects.
 * This class implements the {@code Serializer} interface to handle the serialization and deserialization
 * of {@code Ingredient} instances.
 */
public class IngredientSerializer implements Serializer<Document, Ingredient> {

    /**
     * Serializes an {@code Ingredient} object to a MongoDB {@code Document}.
     *
     * This method converts the properties of an {@code Ingredient} into a {@code Document} format that
     * can be stored in MongoDB.
     *
     * @param ingredient The {@code Ingredient} object to be serialized.
     * @return A {@code Document} representing the serialized form of the {@code Ingredient}.
     */
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

    /**
     * Deserializes a MongoDB {@code Document} to an {@code Ingredient} object.
     *
     * This method converts a {@code Document} retrieved from MongoDB back into an {@code Ingredient} instance.
     *
     * @param bson The {@code Document} representing the serialized form of an {@code Ingredient}.
     * @return An {@code Ingredient} object created from the {@code Document}.
     */
    @Override
    public Ingredient deserialize(Document bson) {
        String ingredientID = bson.getString("ingredientID");
        String ingredientName = bson.getString("ingredientName");
        int quantity = bson.getDouble("quantity").intValue();
        String quantityUnit = bson.getString("quantityUnit");
        String category = bson.getString("category");

        return new Ingredient(ingredientID, ingredientName, quantityUnit, category, quantity);
    }

    /**
     * Deserializes a list of MongoDB {@code Document} objects to a list of {@code Ingredient} objects.
     *
     * This method converts each {@code Document} in the list to an {@code Ingredient} and returns a list
     * of {@code Ingredient} objects.
     *
     * @param bsonList A list of {@code Document} objects representing serialized {@code Ingredient} instances.
     * @return A list of {@code Ingredient} objects created from the list of {@code Document} objects.
     */
    public List<Ingredient> deserializeList(List<Document> bsonList) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Document document : bsonList) {
            ingredients.add(deserialize(document));
        }
        return ingredients;
    }

    /**
     * Serializes a list of {@code Ingredient} objects to a list of MongoDB {@code Document} objects.
     *
     * This method converts each {@code Ingredient} in the list to a {@code Document} and returns a list
     * of {@code Document} objects.
     *
     * @param ingredients A list of {@code Ingredient} objects to be serialized.
     * @return A list of {@code Document} objects representing the serialized form of the {@code Ingredient} instances.
     */
    public List<Document> serializeList(List<Ingredient> ingredients) {
        List<Document> bsonList = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            bsonList.add(serialize(ingredient));
        }
        return bsonList;
    }
}
