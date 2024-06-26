package entity;

public abstract class Nutrition {
    private String label;
    private String unit;
    private float quantity;
    private float percentage;

    public Nutrition(String label, String unit, float quantity, float percentage) {
        this.label = label;
        this.unit = unit;
        this.quantity = quantity;
        this.percentage = percentage;
    }

}

