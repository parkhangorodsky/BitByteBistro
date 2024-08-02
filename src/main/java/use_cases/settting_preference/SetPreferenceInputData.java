package use_cases.settting_preference;

public class SetPreferenceInputData {

    private boolean isNightMode;

    public SetPreferenceInputData(boolean isNightMode) {
        this.isNightMode = isNightMode;
    }

    public boolean getNightMode() {
        return isNightMode;
    }

}
