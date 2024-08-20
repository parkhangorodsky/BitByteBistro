package frameworks.data_access;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entity.Fridge;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import frameworks.data_access.serialization.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.logging.Filter;
import java.util.prefs.Preferences;

/**
 * Provides data access methods for user-related operations using MongoDB.
 * This class implements the {@link UserDataAccessInterface} and performs CRUD operations
 * on user data within a MongoDB collection. It uses serializers to convert between
 * Java objects and MongoDB documents.
 */
public class MongoUserDAO implements UserDataAccessInterface{

    private final MongoCollection<Document> userCollection;

    /**
     * Constructs a {@code MongoUserDAO} instance.
     * Initializes the DAO with the specified MongoDB database and sets up the collection
     * for user documents.
     *
     * @param database The {@code MongoDatabase} instance used to access the database.
     */
    public MongoUserDAO(MongoDatabase database) {
        this.userCollection = database.getCollection("users", Document.class);
    }

    /**
     * Adds a new user to the MongoDB collection.
     * Serializes the {@code User} object and inserts it into the collection.
     *
     * @param user The {@code User} object to be added.
     */
    @Override
    public void addUser(User user) {
        UserSerializer userSerializer = new UserSerializer();
        userCollection.insertOne(userSerializer.serialize(user));
    }

    /**
     * Deletes a user from the MongoDB collection based on their email.
     *
     * @param user The {@code User} object to be deleted.
     */
    @Override
    public void deleteUser(User user) {
        userCollection.deleteOne(Filters.eq("userEmail", user.getUserEmail()));
    }

    /**
     * Retrieves a user from the MongoDB collection based on their email.
     *
     * @param email The email of the user to retrieve.
     * @return The {@code User} object if found, otherwise {@code null}.
     */
    @Override
    public User getUserByEmail(String email) {
        UserSerializer userSerializer = new UserSerializer();
        Document bsonUser = userCollection.find(Filters.eq("userEmail", email)).first();
        if (bsonUser != null) {
            return userSerializer.deserialize(bsonUser);
        } else return null;
    }

    /**
     * Checks if a user with the specified email exists in the MongoDB collection.
     *
     * @param email The email of the user to check.
     * @return {@code true} if the user exists, otherwise {@code false}.
     */
    @Override
    public boolean existsByEmail(String email) {
        return getUserByEmail(email) != null;
    }

    /**
     * Adds a recipe to the user's list of recipes in the MongoDB collection.
     * Updates the user's document to include the new recipe.
     *
     * @param user The {@code User} object to which the recipe will be added.
     * @param recipe The {@code Recipe} object to be added.
     */
    @Override
    public void addRecipe(User user, Recipe recipe) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());

        RecipeSerializer recipeSerializer = new RecipeSerializer();
        Bson update = Updates.addToSet("recipes", recipeSerializer.serialize(recipe));
        userCollection.updateOne(filter, update);
    }

    /**
     * Adds a shopping list to the user's collection of shopping lists.
     * Updates the user's document to include the new shopping list.
     *
     * @param user The {@code User} object to which the shopping list will be added.
     * @param shoppingList The {@code ShoppingList} object to be added.
     */
    @Override
    public void addShoppingList(User user, ShoppingList shoppingList) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());

        ShoppingListSerializer shoppingListSerializer = new ShoppingListSerializer();
        Bson update = Updates.set("shoppingLists." + shoppingList.getShoppingListName(), shoppingListSerializer.serialize(shoppingList));
        userCollection.updateOne(filter, update);
    }

    /**
     * Adds a recipe to a specific shopping list in the user's collection.
     * Updates the shopping list within the user's document to include the new recipe.
     *
     * @param user The {@code User} object containing the shopping list.
     * @param shoppingList The {@code ShoppingList} object to which the recipe will be added.
     * @param recipe The {@code Recipe} object to be added.
     */
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

    /**
     * Updates the user's fridge information in the MongoDB collection.
     *
     * @param user The {@code User} object containing the updated fridge information.
     * @param fridge The {@code Fridge} object to be updated.
     */
    @Override
    public void updateFridge(User user, Fridge fridge) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());
        FridgeSerializer fridgeSerializer = new FridgeSerializer();
        Bson update = Updates.set("fridge", fridgeSerializer.serialize(fridge));
        userCollection.updateOne(filter, update);
    }

    /**
     * Updates the list of recently viewed recipes for a user.
     *
     * @param user The {@code User} object containing the updated list of recently viewed recipes.
     */
    @Override
    public void updateRecentlyViewedRecipes(User user) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());

        RecipeSerializer recipeSerializer = new RecipeSerializer();
        Bson update = Updates.set("recentlyViewedRecipes", recipeSerializer.serializeRecipeList(user.getRecentlyViewedRecipes()));
        userCollection.updateOne(filter, update);
    }

    /**
     * Updates a specific user preference field in the MongoDB collection.
     *
     * @param user The {@code User} object whose preference is being updated.
     * @param fieldName The name of the preference field to update.
     * @param value The new value for the preference field.
     */
    @Override
    public void updateUserPreference(User user, String fieldName, Object value) {
        Bson filter = Filters.eq("userEmail", user.getUserEmail());
        Bson update = Updates.set( "preference." + fieldName, value);
        userCollection.updateOne(filter, update);
    }

    /**
     * Updates the details of an existing user.
     * This method is not yet implemented.
     *
     * @param user The {@code User} object with updated details.
     */
    @Override
    public void updateUser(User user) {
    }

}
