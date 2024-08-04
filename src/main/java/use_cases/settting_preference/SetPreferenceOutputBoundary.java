package use_cases.settting_preference;

/**
 * Interface for updating local application settings based on user preferences.
 */
public interface SetPreferenceOutputBoundary {

    /**
     * Updates the local application settings to reflect the user's night mode preference.
     *
     * @param isNightMode true to enable night mode, false to disable it.
     */
    void updateLocalAppSetting(boolean isNightMode);

}


