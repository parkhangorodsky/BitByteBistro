package entity;

import java.util.Objects;

public class Ingredient {

    private String ingredientID;
    private String ingredientName;
    private String quantityUnit;
    private String category;
    float quantity;

    public Ingredient(String ingredientID, String ingredientName, String quantityUnit, String category, float quantity) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        if (Objects.equals(quantityUnit, "<unit>")) {
            this.quantityUnit = "whole";
        } else {
            this.quantityUnit = quantityUnit;
        }
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
        return this.getIngredientQuantity() + " " + this.getIngredientMeasure() + " " + this.getIngredientName();
    }
}


