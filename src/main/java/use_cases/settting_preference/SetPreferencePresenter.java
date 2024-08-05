package use_cases.settting_preference;

import app.local.LocalAppSetting;

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
