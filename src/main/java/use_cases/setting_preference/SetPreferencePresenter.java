package use_cases.setting_preference;

import app.local.LocalAppSetting;

public class SetPreferencePresenter implements SetPreferenceOutputBoundary {

    public SetPreferencePresenter() {
        super();
    }

    @Override
    public void updateLocalAppSetting(boolean isNightMode, boolean subtractFridgeFromGrocery) {
        LocalAppSetting.setNightMode(isNightMode);
        LocalAppSetting.setSubtractFridgeFromGrocery(subtractFridgeFromGrocery);
        LocalAppSetting.firePropertyChange("nightMode");
        LocalAppSetting.firePropertyChange("subtractFridgeFromGrocery");
    }
}
