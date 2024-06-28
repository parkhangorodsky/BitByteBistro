package entity;
public class Ingredient {
    private String ingredientID;
    private String ingredientName;
    private String ingredientMeasure;
    private String ingredientCategory;

    public Ingredient(String ingredientID, String ingredientName, String ingredientMeasure, String ingredientCategory) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.ingredientMeasure = ingredientMeasure;
        this.ingredientCategory = ingredientCategory;
    }

    public String getIngredientID() {return ingredientID;}
    public String getIngredientName() {return ingredientName;}
    public String getIngredientMeasure() {return ingredientMeasure;}
    public String getIngredientCategory() {return ingredientCategory;}
}
