package entity;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String name;
    private String image;
    private String instructions;
    private List<Grocery> ingredientList;
    private List<Nutrition> nutritionalInfo;
    private float rating;
    private float estimatedCostPerServing;
    private boolean privacyStatus;

    /**
     * Constructor for Recipe Class.
     * @param name
     * @param image
     * @param instructions
     * @param ingredientList
     * @param nutritionalInfo
     * @param estimatedCostPerServing
     * @param privacyStatus
     */
    public Recipe(String name, String image, String instructions,
                  List<Grocery> ingredientList, List<Nutrition> nutritionalInfo,
                  float estimatedCostPerServing, boolean privacyStatus) {
        this.name = name;
        this.image = image;
        this.instructions = instructions;
        this.ingredientList = ingredientList;
        this.nutritionalInfo = nutritionalInfo;
        this.rating = 0;
        this.estimatedCostPerServing = estimatedCostPerServing;
        this.privacyStatus = true;
    }

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
