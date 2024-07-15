package use_case.interactor;

import entity.Recipe;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class RecipeProcessing {

    public static void main(String[] args) {
        try {
            // Read JSON file into string
            String jsonString = new String(Files.readAllBytes(Paths.get("src/main/resources/example-recipe.json")));

            // Convert string to JSONObject
            JSONObject recipeJSON = new JSONObject(jsonString);

            // Call convertJSONtoRecipe method
            RecipeProcessor obj = new RecipeProcessor();
            Recipe recipe = obj.convertJSONtoRecipe(recipeJSON);

            // Now you can work with the 'recipe' object as needed
            System.out.println("Recipe Name: " + recipe.getName());
            System.out.println("Image URL: " + recipe.getImage());
            List<Recipe> list = Collections.singletonList(recipe);
            System.out.println("Grocery List: " + recipe.getGroceryList(list));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
