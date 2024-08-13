package use_cases.setting_preference;

import app.local.LocalAppSetting;

/**
 * Presenter class responsible for updating local application settings based on user preferences.
 * This class handles the output boundary of the set preference use case, applying changes to the local app settings.
 */
public class SetPreferencePresenter implements SetPreferenceOutputBoundary {

    /**
     * Constructs a new {@code SetPreferencePresenter}.
     * This default constructor is provided to instantiate the presenter.
     */
    public SetPreferencePresenter() {
        super();
    }

    /**
     * Updates the local application settings with the specified preferences.
     *
     * This method modifies the local app settings to reflect the user's preferences for night mode and
     * whether items in the fridge should be subtracted from the grocery list. It also triggers property
     * change events for each preference, allowing other components to react to these changes.
     *
     * @param isNightMode              A boolean indicating if night mode should be enabled.
     * @param subtractFridgeFromGrocery A boolean indicating if items in the fridge should be subtracted from the grocery list.
     */
    @Override
    public void updateLocalAppSetting(boolean isNightMode, boolean subtractFridgeFromGrocery) {
        LocalAppSetting.setNightMode(isNightMode);
        LocalAppSetting.setSubtractFridgeFromGrocery(subtractFridgeFromGrocery);
        LocalAppSetting.firePropertyChange("nightMode");
        LocalAppSetting.firePropertyChange("subtractFridgeFromGrocery");
    }

}
