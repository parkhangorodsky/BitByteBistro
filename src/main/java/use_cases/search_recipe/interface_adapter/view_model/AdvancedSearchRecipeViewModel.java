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

/**
 * Overview: View model for the advanced search recipe use case.
 * Procedure: This class loads and holds type option information loaded from CSV files
 * and provides methods to access these options from the view.
 * Encapsulation: This class encapsulates the logic for loading and formatting
 * the data for types of labels that can be used to search for recipe.
 */
public class AdvancedSearchRecipeViewModel extends ViewModel implements StringCaseEditor {

    public Map<String, String> dietData;
    public Map<String, String> healthData;
    public Map<String, String> cuisineTypeData;
    public Map<String, String> dishTypeData;
    public Map<String, String> mealTypeData;

    /**
     * Constructs an AdvancedSearchRecipeViewModel and initializes the option data
     * by loading it from corresponding CSV files.
     */
    public AdvancedSearchRecipeViewModel(String viewName) {
        super(viewName);

        dietData = createHashmapFromFourColumnCsv("src/main/resources/csv/diet.csv");
        healthData = createHashmapFromFourColumnCsv("src/main/resources/csv/health.csv");
        cuisineTypeData = createHashmapFromFourColumnCsv("src/main/resources/csv/cuisinetype.csv");
        dishTypeData = createHashmapFromFourColumnCsv("src/main/resources/csv/dishtype.csv");
        mealTypeData = createListFromTwoColumnCsv("src/main/resources/csv/mealtype.csv");
    }

    /**
     * Reads a four-column CSV file and creates a hashmap where the key is the
     * capitalized type label and the value is the corresponding API parameter.
     *
     * @param fileName the path to the CSV file
     * @return a hashmap with the processed data
     */
    private Map<String, String> createHashmapFromFourColumnCsv(String fileName) {

        // Try reading the csv in the resource initialization (auto-close when try block terminates).
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {

            // Create a map for the labels: API parameter.
            Map<String, String> labelMap = new HashMap<>();

            // Skip the header
            String line = csvReader.readLine();

            // Format each CSV row into map entry.
            while ((line = csvReader.readLine()) != null) {
                String[] row = line.split(",");
                String key = capitalizeWords(row[1]);
                String value = row[2];
                labelMap.put(key, value);
            }
            return labelMap;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading file: " + fileName);
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a two-column CSV file and creates a hashmap where the key is the capitalized label,
     * and value are the corresponding parameter value for the API.
     *
     * @param fileName the path to the CSV file
     * @return a hashmap with the processed data
     */
    private Map<String, String> createListFromTwoColumnCsv(String fileName) {

        // Try reading the csv in the resource initialization (auto-close when try block terminates).
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            // Create a map for the labels: API parameter.
            Map<String, String> target = new HashMap<>();
            String line = csvReader.readLine();

            // Format each CSV row into map entry.
            while ((line = csvReader.readLine()) != null) {
                String[] row = line.split(",");
                target.put(capitalizeWords(row[1]), row[1]);
            }
            return target;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading file: " + fileName);
            throw new RuntimeException(e);
        }
    }

    // Getters
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
