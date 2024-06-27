package entity;
public class Ingredient {
    String ingredientID;
    String ingredientName;
    String ingredientMeasure;
    String ingredientCategory;

    public Ingredient(String ingredientID, String ingredientName, String ingredientMeasure, String ingredientCategory) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.ingredientMeasure = ingredientMeasure;
        this.ingredientCategory = ingredientCategory;
    }
}
