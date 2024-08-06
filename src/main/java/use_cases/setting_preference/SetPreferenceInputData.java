package use_cases.setting_preference;

public class SetPreferenceInputData {

    private boolean isNightMode;
    private boolean subtractFridgeFromGrocery;

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
