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

        User listOwner = userSerializer.deserialize(bson);
        String name = bson.getString("name");
        List<Ingredient> groceries = ingredientSerializer.deserializeList(bson.getList("groceries", Document.class));
        Double cost = bson.getDouble("cost");
        List<Recipe> recipes = recipeSerializer.deserializeRecipeList(bson.getList("recipes", Document.class));

        ShoppingList shoppingList = new ShoppingList(listOwner, name);
        shoppingList.setListItems(groceries);
        shoppingList.setEstimatedTotalCost(cost);
        shoppingList.setRecipes(recipes);
        return shoppingList;
    }

}
