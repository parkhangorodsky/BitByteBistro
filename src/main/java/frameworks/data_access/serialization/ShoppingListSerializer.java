package frameworks.data_access.serialization;

import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import entity.builder.DefaultRecipeBuilder;
import entity.builder.RecipeBuilder;
import org.bson.Document;
import org.bson.types.Binary;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShoppingListSerializer implements Serializer<Document, ShoppingList> {
    private final RecipeSerializer recipeSerializer = new RecipeSerializer();
    private final IngredientSerializer ingredientSerializer = new IngredientSerializer();
    private final UserSerializer userSerializer = new UserSerializer();
    @Override
    public Document serialize(ShoppingList shoppingList){

        Document document = new Document()
                .append("listOwner", userSerializer.serialize(shoppingList.getListOwner()))
                .append("name", shoppingList.getShoppingListName())
                .append("groceries", ingredientSerializer.serializeList(shoppingList.getListItems()))
                .append("cost", shoppingList.getEstimatedTotalCost())
                .append("recipes", recipeSerializer.serializeRecipeList(shoppingList.getRecipes()));

        return document;
    }

    @Override
    public ShoppingList deserialize(Document bson){

        String listOwner = bson.getString("userName");
        String name = bson.getString("name");
        List<Ingredient>
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

    public List<Document> serializeRecipeList(List<Recipe> recipeList){
        List<Document> documentList = new ArrayList<>();

        for (Recipe recipe : recipeList) {
            documentList.add(serialize(recipe));
        }

        return documentList;
    }

    public List<Recipe> deserializeRecipeList(List<Document> bsonList){
        List<Recipe> recipeList = new ArrayList<>();

        if (bsonList != null && !bsonList.isEmpty()) {
            for (Document document : bsonList) {
                recipeList.add(deserialize(document));
            }
        }
        return recipeList;
    }

}
