package use_cases.settting_preference;

/**
 * Data class representing the input data for setting user preferences.
 * <p>
 * This class encapsulates the user's preference for night mode, which is passed to the
 * interactor to execute the preference setting use case.
 * </p>
 */
public class SetPreferenceInputData {

    private boolean isNightMode;

    /**
     * Constructs a new SetPreferenceInputData object with the specified night mode preference.
     *
     * @param isNightMode A boolean indicating whether night mode should be enabled or not.
     *                    If true, night mode is enabled; if false, it is disabled.
     */
    public SetPreferenceInputData(boolean isNightMode) {
        this.isNightMode = isNightMode;
    }

    /**
     * Returns the night mode preference.
     *
     * @return A boolean indicating whether night mode is enabled.
     */
    public boolean getNightMode() {
        return isNightMode;
    }

}

