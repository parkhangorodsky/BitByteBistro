package frameworks.data_access.serialization;

import entity.Recipe;
import entity.builder.DefaultRecipeBuilder;
import entity.builder.RecipeBuilder;
import org.bson.Document;
import org.bson.types.Binary;

public class RecipeSerializer implements Serializer<Document, Recipe> {
    private final BufferedImageSerializer bufferedImageSerializer = new BufferedImageSerializer();
    private final NutritionSerializer nutritionMapSerializer = new NutritionSerializer();
    private final IngredientSerializer ingredientSerializer = new IngredientSerializer();
    @Override
    public Document serialize(Recipe recipe){

        Document document = new Document()
                .append("name", recipe.getName())
                .append("yield", recipe.getYield())
                .append("instructions", recipe.getInstructions())
                .append("ingredientList", ingredientSerializer.serializeList(recipe.getIngredientList()))
                .append("nutritionMap", nutritionMapSerializer.serializeMap(recipe.getNutritionMap()))
                .append("id", recipe.getId())
                .append("dietLabels", recipe.getDietLabels())
                .append("healthLabels", recipe.getHealthLabels())
                .append("cautions", recipe.getCautions())
                .append("tags", recipe.getTags())
                .append("cuisineType", recipe.getCuisineType())
                .append("mealType", recipe.getMealType())
                .append("dishType", recipe.getDishType());

        if (recipe.getImage() != null) {
            document.append("image", bufferedImageSerializer.serialize(recipe.getImage()));
        }
        if (recipe.getSmallImage() != null) {
            document.append("smallImage", bufferedImageSerializer.serialize(recipe.getSmallImage()));
        }

        return document;
    }

    @Override
    public Recipe deserialize(Document bson){

        RecipeBuilder recipeBuiler = new DefaultRecipeBuilder(bson.getString("id"))
                .buildName(bson.getString("name"))
                .buildYield(bson.getInteger("yield"))
                .buildInstruction(bson.getString("instructions"))
                .buildIngredientList(ingredientSerializer.deserializeList(bson.getList("ingredientList", Document.class)))
                .buildNutritionMap(nutritionMapSerializer.deserializeMap(bson.get("nutritionMap", Document.class)))
                .buildDietLabels(bson.getList("dietLabels", String.class))
                .buildHealthLabels(bson.getList("healthLabels", String.class))
                .buildCautions(bson.getList("cautions", String.class))
                .buildTags(bson.getList("tags", String.class))
                .buildCuisineType(bson.getList("cuisineType", String.class))
                .buildMealType(bson.getList("mealType", String.class))
                .buildDishType(bson.getList("dishType", String.class))
                ;

        if (bson.containsKey("image")) {
            recipeBuiler.buildImage(bufferedImageSerializer.deserialize(bson.get("image", Binary.class).getData()));
        }
        if (bson.containsKey("smallImage")) {
            recipeBuiler.buildSmallImage(bufferedImageSerializer.deserialize(bson.get("smallImage", Binary.class).getData()));
        }

        return recipeBuiler.get();
    }

}
