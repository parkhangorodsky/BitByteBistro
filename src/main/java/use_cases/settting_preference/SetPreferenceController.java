package use_cases.settting_preference;

/**
 * The SetPreferenceController class is responsible for handling user input related to
 * application preferences and delegating the processing of these preferences to an interactor
 * that implements the SetPreferenceInputBoundary interface.
 * <p>
 * This controller serves as a mediator between the user interface
 * and the business logic, which is encapsulated by an interactor conforming to the
 * SetPreferenceInputBoundary interface.
 * </p>
 */
public class SetPreferenceController {

    /**
     * The interactor that processes the user's preference settings, implementing the
     * SetPreferenceInputBoundary interface.
     */
    private final SetPreferenceInputBoundary interactor;

    /**
     * Constructs a SetPreferenceController with the specified interactor.
     *
     * @param interactor The interactor responsible for processing the preference setting use case,
     *                   implementing the SetPreferenceInputBoundary interface.
     */
    public SetPreferenceController(SetPreferenceInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the preference setting use case with the provided night mode preference.
     * This method creates an instance of SetPreferenceInputData containing the user's
     * night mode preference and passes it to the interactor for further processing.
     *
     * @param isNightMode A boolean indicating whether night mode should be enabled or not.
     *                    If true, night mode is enabled; if false, it is disabled.
     */
    public void execute(boolean isNightMode) {
        SetPreferenceInputData inputData = new SetPreferenceInputData(isNightMode);
        interactor.execute(inputData);
    }
}


