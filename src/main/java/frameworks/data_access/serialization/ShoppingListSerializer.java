package frameworks.data_access.serialization;

import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import entity.builder.DefaultRecipeBuilder;
import entity.builder.RecipeBuilder;
import org.bson.Document;
import org.bson.types.Binary;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Serializer class for converting {@code ShoppingList} objects to and from MongoDB {@code Document} objects.
 * This class implements the {@code Serializer} interface to handle the serialization and deserialization
 * of {@code ShoppingList} instances, including handling maps of shopping lists.
 */
public class ShoppingListSerializer implements Serializer<Document, ShoppingList> {
    private final RecipeSerializer recipeSerializer = new RecipeSerializer();
    private final IngredientSerializer ingredientSerializer = new IngredientSerializer();

    /**
     * Serializes a {@code ShoppingList} object to a MongoDB {@code Document}.
     *
     * This method converts the properties of a {@code ShoppingList} instance into a {@code Document} format
     * suitable for storage in MongoDB.
     *
     * @param shoppingList The {@code ShoppingList} object to be serialized.
     * @return A {@code Document} representing the serialized form of the {@code ShoppingList} instance.
     */
    @Override
    public Document serialize(ShoppingList shoppingList){

        Document document = new Document();
        document.append("listOwner", shoppingList.getListOwner());
        document.append("shoppingListName", shoppingList.getShoppingListName());
        document.append("listItems", ingredientSerializer.serializeList(shoppingList.getListItems()));
        document.append("estimatedTotalCost", shoppingList.getEstimatedTotalCost());
        document.append("recipes", recipeSerializer.serializeRecipeList(shoppingList.getRecipes()));

        return document;
    }


    /**
     * Deserializes a MongoDB {@code Document} to a {@code ShoppingList} object.
     *
     * This method converts a {@code Document} retrieved from MongoDB back into a {@code ShoppingList} instance.
     * It populates all fields including list items and recipes.
     *
     * @param bson The {@code Document} representing the serialized form of a {@code ShoppingList} instance.
     * @return A {@code ShoppingList} object created from the {@code Document}.
     */
    @Override
    public ShoppingList deserialize(Document bson){

        String listOwner = bson.getString("listOwner");
        String shoppingListName = bson.getString("shoppingListName");
        List<Ingredient> listItems = ingredientSerializer.deserializeList(bson.getList("listItems", Document.class));
        Double cost = bson.getDouble("cost");
        List<Recipe> recipes = recipeSerializer.deserializeRecipeList(bson.getList("recipes", Document.class));

        ShoppingList shoppingList = new ShoppingList(listOwner, shoppingListName);
        shoppingList.setListItems(listItems);
        shoppingList.setEstimatedTotalCost(cost);
        shoppingList.setRecipes(recipes);
        return shoppingList;
    }


    /**
     * Serializes a map of {@code ShoppingList} objects to a MongoDB {@code Document}.
     *
     * This method converts each {@code ShoppingList} instance in the map to a {@code Document} and stores them
     * in a {@code Document} where the map keys are used as field names.
     *
     * @param shoppingListMap A map of {@code ShoppingList} objects to be serialized, where the keys are field names.
     * @return A {@code Document} representing the serialized form of the map of {@code ShoppingList} instances.
     */
    public Document serializeShoppingListMap(Map<String, ShoppingList> shoppingListMap){
        Document document = new Document();

        for (Map.Entry<String, ShoppingList> entry : shoppingListMap.entrySet()) {
            document.append(entry.getKey(), serialize(entry.getValue()));
        }

        return document;
    }

    /**
     * Deserializes a MongoDB {@code Document} to a map of {@code ShoppingList} objects.
     *
     * This method converts each {@code Document} within the main {@code Document} back into a {@code ShoppingList}
     * instance and stores them in a map, where the document keys are used as map keys.
     *
     * @param bson The {@code Document} representing a map of serialized {@code ShoppingList} instances.
     * @return A map of {@code ShoppingList} objects created from the {@code Document}.
     */
    public Map<String, ShoppingList> deserializeShoppingListMap(Document bson){
        Map<String, ShoppingList> shoppingListMap = new TreeMap<>();
        for (String key : bson.keySet()) {
            shoppingListMap.put(key, deserialize(bson.get(key, Document.class)));
        }
        return shoppingListMap;
    }

}
