package entity;

public class Ingredient {

    private String ingredientID;
    private String ingredientName;
    private String quantityUnit;
    private String category;

    public Ingredient(String ingredientID, String ingredientName, String quantityUnit, String category) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.quantityUnit = quantityUnit;
        this.category = category;
    }

    public String getIngredientID() {return ingredientID;}
    public String getIngredientName() {return ingredientName;}
    public String getIngredientMeasure() {return quantityUnit;}
    public String getIngredientCategory() {return category;}

}
