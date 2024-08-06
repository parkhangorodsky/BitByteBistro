package frameworks.data_access;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import frameworks.data_access.serialization.RecipeSerializer;
import frameworks.data_access.serialization.ShoppingListSerializer;
import frameworks.data_access.serialization.UserSerializer;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.logging.Filter;
import java.util.prefs.Preferences;

public class MongoUserDAO implements UserDataAccessInterface{

    private final MongoCollection<Document> userCollection;

    public MongoUserDAO(MongoDatabase database) {
        this.userCollection = database.getCollection("users", Document.class);
    }

    @Override
    public void addUser(User user) {
        UserSerializer userSerializer = new UserSerializer();
        userCollection.insertOne(userSerializer.serialize(user));
    }

    @Override
    public void updateUser(User user) {
    }

    @Override
    public void deleteUser(User user) {
        userCollection.deleteOne(Filters.eq("userEmail", user.getUserEmail()));
    }

    @Override
    public User getUserByEmail(String email) {
        UserSerializer userSerializer = new UserSerializer();
        Document bsonUser = userCollection.find(Filters.eq("userEmail", email)).first();
        if (bsonUser != null) {
            return userSerializer.deserialize(bsonUser);
        } else return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return getUserByEmail(email) != null;
    }

    @Override
    public void addRecipe(User user, Recipe recipe) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());

        RecipeSerializer recipeSerializer = new RecipeSerializer();
        Bson update = Updates.addToSet("recipes", recipeSerializer.serialize(recipe));
        userCollection.updateOne(filter, update);
    }

    @Override
    public void addShoppingList(User user, ShoppingList shoppingList) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());

        ShoppingListSerializer shoppingListSerializer = new ShoppingListSerializer();
        Bson update = Updates.addToSet("shoppingLists", shoppingListSerializer.serialize(shoppingList));
        userCollection.updateOne(filter, update);
    }

    @Override
    public void addRecipeToShoppingList(User user, ShoppingList shoppingList, Recipe recipe) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());
        String shoppingListName = shoppingList.getShoppingListName();

        RecipeSerializer recipeSerializer = new RecipeSerializer();
        Bson update = Updates.addToSet("shoppingList." + shoppingListName + ".recipes", recipeSerializer.serialize(recipe));
    }

    @Override
    public void updateRecentlyViewedRecipes(User user) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());

        RecipeSerializer recipeSerializer = new RecipeSerializer();
        Bson update = Updates.set("recentlyViewedRecipes", recipeSerializer.serializeRecipeList(user.getRecentlyViewedRecipes()));
        userCollection.updateOne(filter, update);
    }

    @Override
    public void updateUserPreference(User user, String fieldName, Object value) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());
        Bson update = Updates.set( "preference." + fieldName, value);
        userCollection.updateOne(filter, update);
    }

}
