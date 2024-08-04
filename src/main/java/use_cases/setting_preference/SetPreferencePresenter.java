package use_cases.setting_preference;

import app.LocalAppSetting;

public class SetPreferencePresenter implements SetPreferenceOutputBoundary{

    public SetPreferencePresenter() {
        super();
    }

    @Override
    public void updateLocalAppSetting(boolean isNightMode) {
        LocalAppSetting.setNightMode(isNightMode);
        LocalAppSetting.firePropertyChange("nightMode");
    }
}
