package use_cases.settting_preference;

public class SetPreferenceInteractor implements SetPreferenceInputBoundary {

    SetPreferenceOutputBoundary presenter;

    public SetPreferenceInteractor(SetPreferenceOutputBoundary presenter) {
        this.presenter = presenter;
    }

    public void execute(SetPreferenceInputData inputData) {
        presenter.updateLocalAppSetting(inputData.getNightMode());
    }
}
