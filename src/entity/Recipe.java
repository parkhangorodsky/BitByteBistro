package entity;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private List<Grocery> ingredientList;
    private String name;
    private String instructions;
    private List<Nutrition> nutritionalInfo;
    private boolean privacyStatus;
    private String image;
    private float rating;
    private float estimatedCostPerServing;

    /**
     * Getters
     */

    public List<Grocery> getIngredientList() {
        return this.ingredientList;
    }
    public String getName() {
        return this.name;
    }
    public String getInstructions() {
        return this.instructions;
    }
    public List<Nutrition> getNutritionalInfoList() {
        return this.nutritionalInfo;
    }
    public boolean getPrivacyStatus() {
        return this.privacyStatus;
    }
    public String getImage() {
        return this.image;
    }
    public float getRating() {
        return this.rating;
    }
    public float getEstimatedCostPerServing() {
        return this.estimatedCostPerServing;
    }

    /**
     * Setters
     */
    public void setIngredientList(List<Grocery> ingredientList) {
        this.ingredientList = ingredientList;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public void setNutritionalInfoList(List<Nutrition> nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }
    public void setPrivacyStatus(boolean privacyStatus) {
        this.privacyStatus = privacyStatus;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public void setEstimatedCostPerServing(float estimatedCostPerServing) {
        this.estimatedCostPerServing = estimatedCostPerServing;
    }

}
