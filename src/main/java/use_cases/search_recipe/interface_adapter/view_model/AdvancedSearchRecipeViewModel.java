package use_cases.search_recipe.interface_adapter.view_model;

import use_cases._common.gui_common.abstractions.StringCaseEditor;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdvancedSearchRecipeViewModel extends ViewModel implements StringCaseEditor {

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
            csvReader.close();
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
            csvReader.close();
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
    public Map<String, String> getCuisineTypeOptions() {return cuisineTypeData;}
    public Map<String, String> getDishTypeOptions() {
        return dishTypeData;
    }
    public Map<String, String> getMealTypeOptions() {
        return mealTypeData;
    }

}
