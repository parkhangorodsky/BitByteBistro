package entity;

public class Grocery {
    Ingredient ingredient;
    float quantity;

    public Grocery(Ingredient ingredient, float quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public String getName() {
        return ingredient.getIngredientName();
    }

    public float getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return ingredient.getIngredientMeasure();
    }

    @Override
    public String toString() {
        return this.ingredient.getIngredientName()
                + ": " + this.quantity
                + " " + this.ingredient.getIngredientMeasure();
    }
}
