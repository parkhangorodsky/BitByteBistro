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

public class ShoppingListSerializer implements Serializer<Document, ShoppingList> {
    private final RecipeSerializer recipeSerializer = new RecipeSerializer();
    private final IngredientSerializer ingredientSerializer = new IngredientSerializer();
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


    public Document serializeShoppingListMap(Map<String, ShoppingList> shoppingListMap){
        Document document = new Document();

        for (Map.Entry<String, ShoppingList> entry : shoppingListMap.entrySet()) {
            document.append(entry.getKey(), serialize(entry.getValue()));
        }

        return document;
    }

    public Map<String, ShoppingList> deserializeShoppingListMap(Document bson){
        Map<String, ShoppingList> shoppingListMap = new TreeMap<>();
        for (String key : bson.keySet()) {
            shoppingListMap.put(key, deserialize(bson.get(key, Document.class)));
        }
        return shoppingListMap;
    }

}
