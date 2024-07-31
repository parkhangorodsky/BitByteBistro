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

public class UserSerializer implements Serializer<Document, User> {
    private final RecipeSerializer recipeSerializer = new RecipeSerializer();

    @Override
    public Document serialize(User user){
        Document document = new Document()
                .append("userName", user.getUserName())
                .append("userEmail", user.getUserEmail())
                .append("userPassword", user.getUserPassword())
                .append("createdAt", user.getCreatedAt())
                .append("shoppingList", user.getShoppingLists());

        List<Document> recipes = new ArrayList<>();
        for (Recipe recipe : user.getRecipes()) {
            recipes.add(recipeSerializer.serialize(recipe));
        }

        document.append("recipes", recipes);
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

        List<Recipe> recipes = new ArrayList<>();
        for (Document recipe : bson.getList("recipes", Document.class)) {
            recipes.add(recipeSerializer.deserialize(recipe));
        }

        User user = new User(userName, userEmail, userPassword, createdAt);
        user.setShoppingLists(shoppingList);
        user.setRecipes(recipes);
        return user;
    }
}
