package use_cases.fridge_inventory;

public class FridgeInventoryInputData {
    private final String ingredientName;
    private final float quantity;
    private final String unit;
    private final String category;

    public FridgeInventoryInputData(String ingredientName, float quantity, String unit, String category) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getCategory() {
        return category;
    }
}
