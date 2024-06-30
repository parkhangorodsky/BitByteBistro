package entity;

public class Ingredient {

    private String ingredientID;
    private String ingredientName;
    private String quantityUnit;
    private String category;

    /**
     * Requires:
     * @param ingredientName
     */
    Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(String ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}