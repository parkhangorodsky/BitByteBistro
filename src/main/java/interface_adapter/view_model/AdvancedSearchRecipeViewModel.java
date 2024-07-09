package interface_adapter.view_model;

import view.AdvancedSearchView;
import view.view_components.interfaces.StringCaseEditor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvancedSearchRecipeViewModel extends ViewModel implements StringCaseEditor {

    private String queryString;
    private HashMap<String, List<String>> ingredients;
    private List<String> excluded;
    private List<String> diet;
    private List<String> health;
    private List<String> cuisineType;
    private List<String> mealType;
    private List<String> dishType;

    private List<String> dietOptions;
    private List<String> healthOptions;
    private List<String> cuisineTypeOptions;
    private List<String> mealTypeOptions;
    private List<String> dishTypeOptions;

    public Map<String, String> dietData;
    public Map<String, String> healthData;
    public Map<String, String> cuisineTypeData;
    public Map<String, String> dishTypeData;
    public Map<String, String> mealTypeData;


    public AdvancedSearchRecipeViewModel() {
        super("Advanced Search");

        dietData = createHashmapFromFourColumnCsv("src/main/resources/csv/diet.csv");
        healthData = createHashmapFromFourColumnCsv("src/main/resources/csv/health.csv");
        cuisineTypeData = createHashmapFromFourColumnCsv("src/main/resources/csv/cuisinetype.csv");
        dishTypeData = createHashmapFromFourColumnCsv("src/main/resources/csv/dishtype.csv");
        mealTypeData = createListFromTwoColumnCsv("src/main/resources/csv/mealtype.csv");
    }

    private Map<String, String> createHashmapFromFourColumnCsv(String fileName) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            Map<String, String> target = new HashMap<>();
            String line = csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] row = line.split(",");
                String key = capitalizeWords(row[1]);
                String value = row[2];
                target.put(key, value);
            }
            return target;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private Map<String, String> createListFromTwoColumnCsv(String fileName) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            Map<String, String> target = new HashMap<>();
            String line = csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] row = line.split(",");
                target.put(capitalizeWords(row[1]), row[1]);
            }
            return target;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public Map<String, String> getDietOptions() {
        return dietData;
    }

    public Map<String, String> getHealthOptions() {
        return healthData;
    }

    public Map<String, String> getCuisineTypeOptions() {
        return cuisineTypeData;
    }

    public Map<String, String> getDishTypeOptions() {
        return dishTypeData;
    }

    public Map<String, String> getMealTypeOptions() {
        return mealTypeData;
    }


    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setIngredients(HashMap<String, List<String>> ingredients) {
        this.ingredients = ingredients;
    }

    public void setExcluded(List<String> excluded) {
        this.excluded = excluded;
    }

    public void setDiet(List<String> diet) {
        this.diet = diet;
    }

    public void setHealth(List<String> health) {
        this.health = health;
    }

    public void setCuisineType(List<String> cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setMealType(List<String> mealType) {
        this.mealType = mealType;
    }

    public void setDishType(List<String> dishType) {
        this.dishTypeOptions = dishType;
    }
}
