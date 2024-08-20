package use_cases.setting_preference;

/**
 * Controller class responsible for handling the user's preferences for the application.
 * This class acts as a bridge between the user interface and the underlying business logic.
 */
public class SetPreferenceController {
    /**
     * The input boundary interface for setting user preferences.
     * This follows the Dependency Inversion Principle by depending on an abstraction.
     */
    SetPreferenceInputBoundary interactor;

    /**
     * Constructs a new {@code SetPreferenceController} with the specified interactor.
     *
     * @param interactor The interactor that handles the execution of the set preference use case.
     *                   This interactor should implement the {@code SetPreferenceInputBoundary} interface.
     */
    public SetPreferenceController(SetPreferenceInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the set preference use case by passing the user's preference data to the interactor.
     *
     * @param isNightMode            A boolean indicating if night mode should be enabled.
     * @param subtractFridgeFromGrocery A boolean indicating if items in the fridge should be subtracted from the grocery list.
     */
    public void execute(boolean isNightMode, boolean subtractFridgeFromGrocery) {
        SetPreferenceInputData inputData = new SetPreferenceInputData(isNightMode, subtractFridgeFromGrocery);
        interactor.execute(inputData);
    }
}
