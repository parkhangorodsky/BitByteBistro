package use_cases.setting_preference;

/**
 * Data class representing the input data required for setting user preferences.
 * This class holds the user's preferences that will be passed to the interactor for processing.
 */
public class SetPreferenceInputData {

    private boolean isNightMode;
    private boolean subtractFridgeFromGrocery;

    /**
     * Constructs a new {@code SetPreferenceInputData} object with the specified preferences.
     *
     * @param isNightMode              A boolean indicating if night mode should be enabled.
     * @param subtractFridgeFromGrocery A boolean indicating if items in the fridge should be subtracted from the grocery list.
     */
    public SetPreferenceInputData(boolean isNightMode, boolean subtractFridgeFromGrocery) {
        this.isNightMode = isNightMode;
        this.subtractFridgeFromGrocery = subtractFridgeFromGrocery;
    }

    public boolean getNightMode() {
        return isNightMode;
    }

    public boolean getSubtractFridgeFromGrocery() {
        return subtractFridgeFromGrocery;
    }
}
