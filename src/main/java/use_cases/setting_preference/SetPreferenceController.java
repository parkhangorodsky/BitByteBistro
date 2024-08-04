package use_cases.settting_preference;

import java.util.List;

public class SetPreferenceController {
    SetPreferenceInputBoundary interactor;

    public SetPreferenceController(SetPreferenceInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(boolean isNightMode) {
        SetPreferenceInputData inputData = new SetPreferenceInputData(isNightMode);
        interactor.execute(inputData);
    }

}
