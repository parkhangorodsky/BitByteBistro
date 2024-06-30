package entity;

public class Grocery {
    Ingredient ingredient;
    float quantity;
    public Grocery(Ingredient ingredient, float quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return this.ingredient.getIngredientName()
                + ": " + this.quantity
                + " " + this.ingredient.getIngredientMeasure();
    }
}
