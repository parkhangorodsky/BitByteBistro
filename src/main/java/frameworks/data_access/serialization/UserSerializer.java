package frameworks.data_access.serialization;

import com.fasterxml.jackson.databind.JsonSerializer;
import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
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

public class UserSerializer implements Serializer<Document, User> {
    private final RecipeSerializer recipeSerializer = new RecipeSerializer();
    private final ShoppingListSerializer shoppingListSerializer = new ShoppingListSerializer();

    @Override
    public Document serialize(User user){
        Document document = new Document()
                .append("userName", user.getUserName())
                .append("userEmail", user.getUserEmail())
                .append("userPassword", user.getUserPassword())
                .append("createdAt", user.getCreatedAt())
                .append("shoppingList", user.getShoppingLists())
                .append("preference", user.getPreference());

        document.append("recentlyViewedRecipes", recipeSerializer.serializeRecipeList(user.getRecentlyViewedRecipes()));
        document.append("recipes", recipeSerializer.serializeRecipeList(user.getRecipes()));
        document.append("shoppingLists", shoppingListSerializer.serializeShoppingListMap(user.getShoppingListsMap()));

        return document;
    }

    @Override
    public User deserialize(Document bson){
        String userName = bson.getString("userName");
        String userEmail = bson.getString("userEmail");
        String userPassword = bson.getString("userPassword");
        Date createdDate = bson.getDate("createdAt");
        LocalDateTime createdAt = LocalDateTime.ofInstant((createdDate.toInstant()), ZoneId.systemDefault());
        List<ShoppingList> shoppingList = new ArrayList<>();
        List<Recipe> recipes = recipeSerializer.deserializeRecipeList(bson.getList("recipes", Document.class));
        List<Recipe> recentlyViewedRecipes = recipeSerializer.deserializeRecipeList(bson.getList("recentlyViewedRecipes", Document.class));
        Map<String, Object> preference = bson.get("preference", Map.class);

        User user = new User(userName, userEmail, userPassword, createdAt);
        user.setShoppingLists(shoppingList);
        user.setRecipes(recipes);
        user.setRecentlyViewedRecipes(recentlyViewedRecipes);
        user.setPreference(preference);
        return user;
    }
}
