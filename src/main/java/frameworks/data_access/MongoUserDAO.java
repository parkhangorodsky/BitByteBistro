package frameworks.data_access;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entity.*;
import frameworks.data_access.serialization.*;
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
        Bson update = Updates.set("shoppingLists." + shoppingList.getShoppingListName(), shoppingListSerializer.serialize(shoppingList));
        userCollection.updateOne(filter, update);
    }

    @Override
    public void addRecipeToShoppingList(User user, ShoppingList shoppingList, Recipe recipe) {
        String shoppingListName = shoppingList.getShoppingListName();
        Bson filter = Filters.eq("userEmail", user.getUserEmail());


        RecipeSerializer recipeSerializer = new RecipeSerializer();
        IngredientSerializer ingredientSerializer = new IngredientSerializer();
        Bson updateRecipes = Updates.addToSet("shoppingLists." + shoppingListName + ".recipes", recipeSerializer.serialize(recipe));
        Bson updateGrocery = Updates.set("shoppingLists." + shoppingListName + ".listItems", ingredientSerializer.serializeList(shoppingList.getListItems()));

        userCollection.updateOne(filter, updateRecipes);
        userCollection.updateOne(filter, updateGrocery);
    }

    @Override
    public void removeRecipeFromShoppingList(User user, ShoppingList shoppingList, Recipe recipe) {
        String shoppingListName = shoppingList.getShoppingListName();
        Bson filter = Filters.eq("userEmail", user.getUserEmail());

        // Step 1: Remove the recipe from the shopping list
        Bson removeRecipe = Updates.pull("shoppingLists." + shoppingListName + ".recipes", new Document("name", recipe.getName()));
        userCollection.updateOne(filter, removeRecipe);

        // Step 2: Remove the ingredients associated with the recipe from the shopping list
        IngredientSerializer ingredientSerializer = new IngredientSerializer();
        List<Ingredient> recipeIngredients = recipe.getIngredientList();

        // Remove each ingredient in the recipe from the shopping list's ingredients
        for (Ingredient ingredient : recipeIngredients) {
            Bson removeIngredient = Updates.pull("shoppingLists." + shoppingListName + ".listItems", ingredientSerializer.serialize(ingredient));
            userCollection.updateOne(filter, removeIngredient);
        }
    }

    @Override
    public void updateFridge(User user, Fridge fridge) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());
        FridgeSerializer fridgeSerializer = new FridgeSerializer();
        Bson update = Updates.set("fridge", fridgeSerializer.serialize(fridge));
        userCollection.updateOne(filter, update);
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
