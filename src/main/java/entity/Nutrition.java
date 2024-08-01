package entity;

public class Nutrition {
    private String label;
    private String unit;
    private float quantity;
    private Float percentage;

    public Nutrition(String label, float quantity, String unit, float percentage) {
        this.label = label;
        this.quantity = quantity;
        this.unit = unit;
        this.percentage = percentage;
    }

    public Nutrition(String label, float quantity, String unit) {
        this.label = label;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Nutrition() {
        this.label = "";
        this.quantity = 0;
        this.unit = "";
    }

    @Override
    public String toString() {
        return label + ": " + quantity + " " + unit;
    }

    public String getLabel() {return label;}
    public float getQuantity() {return quantity;}
    public Float getPercentage() {return percentage;}
    public String getUnit() {return unit;}
}

