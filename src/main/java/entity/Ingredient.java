package entity;

public class Ingredient {

    private String ingredientID;
    private String ingredientName;
    private String quantityUnit;
    private String category;
    float quantity;

    public Ingredient(String ingredientID, String ingredientName, String quantityUnit, String category, float quantity) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.quantityUnit = quantityUnit;
        this.category = category;
        this.quantity = quantity;
    }

    public String getIngredientID() {return ingredientID;}
    public String getIngredientName() {return ingredientName;}
    public String getIngredientMeasure() {return quantityUnit;}
    public String getIngredientCategory() {return category;}
    public float getIngredientQuantity() {return quantity;}

    @Override
    public String toString() {
        return this.getIngredientName()
                + ": " + this.quantity
                + " " + this.getIngredientMeasure();
    }
}


