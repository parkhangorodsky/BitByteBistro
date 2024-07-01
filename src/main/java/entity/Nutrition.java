package entity;

public class Nutrition {
    private String label;
    private String unit;
    private float quantity;

    public Nutrition(String label, float quantity, String unit) {
        this.label = label;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return label + " " + quantity + " " + unit;
    }
}

