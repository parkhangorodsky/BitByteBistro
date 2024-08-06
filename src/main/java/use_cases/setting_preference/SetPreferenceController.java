package use_cases.setting_preference;

public class SetPreferenceController {
    SetPreferenceInputBoundary interactor;

    public SetPreferenceController(SetPreferenceInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(boolean isNightMode, boolean subtractFridgeFromGrocery) {
        SetPreferenceInputData inputData = new SetPreferenceInputData(isNightMode, subtractFridgeFromGrocery);
        interactor.execute(inputData);
    }
}
