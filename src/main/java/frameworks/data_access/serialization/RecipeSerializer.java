package frameworks.data_access.serialization;

import entity.Recipe;
import entity.builder.DefaultRecipeBuilder;
import entity.builder.RecipeBuilder;
import org.bson.Document;
import org.bson.types.Binary;

import java.util.ArrayList;
import java.util.List;


/**
 * Serializer class for converting {@code Recipe} objects to and from MongoDB {@code Document} objects.
 * This class implements the {@code Serializer} interface to handle the serialization and deserialization
 * of {@code Recipe} instances, including handling of associated images and ingredient lists.
 */
public class RecipeSerializer implements Serializer<Document, Recipe> {

    private final BufferedImageSerializer bufferedImageSerializer = new BufferedImageSerializer();
    private final NutritionSerializer nutritionMapSerializer = new NutritionSerializer();
    private final IngredientSerializer ingredientSerializer = new IngredientSerializer();

    /**
     * Serializes a {@code Recipe} object to a MongoDB {@code Document}.
     *
     * This method converts the properties of a {@code Recipe} into a {@code Document} format that
     * can be stored in MongoDB. It includes handling for optional image data.
     *
     * @param recipe The {@code Recipe} object to be serialized.
     * @return A {@code Document} representing the serialized form of the {@code Recipe}.
     */
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

    /**
     * Deserializes a MongoDB {@code Document} to a {@code Recipe} object.
     *
     * This method converts a {@code Document} retrieved from MongoDB back into a {@code Recipe} instance,
     * including handling of optional image data.
     *
     * @param bson The {@code Document} representing the serialized form of a {@code Recipe}.
     * @return A {@code Recipe} object created from the {@code Document}.
     */
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

    /**
     * Serializes a list of {@code Recipe} objects to a list of MongoDB {@code Document} objects.
     *
     * This method converts each {@code Recipe} in the list to a {@code Document} and returns a list
     * of {@code Document} objects.
     *
     * @param recipeList A list of {@code Recipe} objects to be serialized.
     * @return A list of {@code Document} objects representing the serialized form of the {@code Recipe} instances.
     */
    public List<Document> serializeRecipeList(List<Recipe> recipeList){
        List<Document> documentList = new ArrayList<>();

        for (Recipe recipe : recipeList) {
            documentList.add(serialize(recipe));
        }

        return documentList;
    }

    /**
     * Deserializes a list of MongoDB {@code Document} objects to a list of {@code Recipe} objects.
     *
     * This method converts each {@code Document} in the list to a {@code Recipe} and returns a list
     * of {@code Recipe} objects.
     *
     * @param bsonList A list of {@code Document} objects representing serialized {@code Recipe} instances.
     * @return A list of {@code Recipe} objects created from the list of {@code Document} objects.
     */
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
