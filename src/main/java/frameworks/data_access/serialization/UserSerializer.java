package frameworks.data_access.serialization;

import com.fasterxml.jackson.databind.JsonSerializer;
import entity.*;
import net.bytebuddy.asm.Advice;
import org.bson.Document;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Serializer class for converting {@code User} objects to and from MongoDB {@code Document} objects.
 * This class implements the {@code Serializer} interface to handle the serialization and deserialization
 * of {@code User} instances, including nested objects like recipes, shopping lists, and fridge contents.
 */
public class UserSerializer implements Serializer<Document, User> {
    private final RecipeSerializer recipeSerializer = new RecipeSerializer();
    private final ShoppingListSerializer shoppingListSerializer = new ShoppingListSerializer();
    private final FridgeSerializer fridgeSerializer = new FridgeSerializer();

    /**
     * Serializes a {@code User} object to a MongoDB {@code Document}.
     *
     * This method converts the properties of a {@code User} instance into a {@code Document} format
     * suitable for storage in MongoDB, including nested lists and maps.
     *
     * @param user The {@code User} object to be serialized.
     * @return A {@code Document} representing the serialized form of the {@code User} instance.
     */
    @Override
    public Document serialize(User user){
        Document document = new Document()
                .append("userName", user.getUserName())
                .append("userEmail", user.getUserEmail())
                .append("userPassword", user.getUserPassword())
                .append("createdAt", user.getCreatedAt())
                .append("preference", user.getPreference());

        document.append("recentlyViewedRecipes", recipeSerializer.serializeRecipeList(user.getRecentlyViewedRecipes()));
        document.append("recipes", recipeSerializer.serializeRecipeList(user.getRecipes()));
        document.append("shoppingLists", shoppingListSerializer.serializeShoppingListMap(user.getShoppingLists()));
        document.append("fridge", fridgeSerializer.serialize(user.getFridge()));

        return document;
    }

    /**
     * Deserializes a MongoDB {@code Document} to a {@code User} object.
     *
     * This method converts a {@code Document} retrieved from MongoDB back into a {@code User} instance,
     * including the conversion of nested lists and maps.
     *
     * @param bson The {@code Document} representing the serialized form of a {@code User} instance.
     * @return A {@code User} object created from the {@code Document}.
     */
    @Override
    public User deserialize(Document bson){
        String userName = bson.getString("userName");
        String userEmail = bson.getString("userEmail");
        String userPassword = bson.getString("userPassword");
        Date createdDate = bson.getDate("createdAt");
        LocalDateTime createdAt = LocalDateTime.ofInstant((createdDate.toInstant()), ZoneId.systemDefault());
        Map<String, ShoppingList> shoppingList = shoppingListSerializer.deserializeShoppingListMap(bson.get("shoppingLists", Document.class));
        List<Recipe> recipes = recipeSerializer.deserializeRecipeList(bson.getList("recipes", Document.class));
        List<Recipe> recentlyViewedRecipes = recipeSerializer.deserializeRecipeList(bson.getList("recentlyViewedRecipes", Document.class));
        Map<String, Object> preference = bson.get("preference", Map.class);
        Fridge fridge = fridgeSerializer.deserialize(bson.get("fridge", Document.class));

        User user = new User(userName, userEmail, userPassword, createdAt);
        user.setShoppingLists(shoppingList);
        user.setRecipes(recipes);
        user.setRecentlyViewedRecipes(recentlyViewedRecipes);
        user.setPreference(preference);
        user.setFridge(fridge);
        return user;
    }
}
