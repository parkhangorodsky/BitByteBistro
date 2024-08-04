package use_cases.settting_preference;

import app.LocalAppSetting;

/**
 * The SetPreferencePresenter class implements the SetPreferenceOutputBoundary interface
 * and is responsible for updating the local application settings based on user preferences.
 * <p>
 * This presenter updates the night mode setting and triggers any necessary property change events.
 * </p>
 */
public class SetPreferencePresenter implements SetPreferenceOutputBoundary {

    /**
     * Constructs a SetPreferencePresenter instance.
     */
    public SetPreferencePresenter() {
        super();
    }

    /**
     * Updates the local application settings to reflect the user's night mode preference.
     * <p>
     * This method sets the night mode in LocalAppSetting and triggers a property change event
     * for the "nightMode" property to notify any listeners of the change.
     * </p>
     *
     * @param isNightMode true to enable night mode, false to disable it.
     */
    @Override
    public void updateLocalAppSetting(boolean isNightMode) {
        LocalAppSetting.setNightMode(isNightMode);
        LocalAppSetting.firePropertyChange("nightMode");
    }
}

