package use_cases.settting_preference;

/**
 * Interface for handling the execution of user preference settings.
 * <p>
 * Implementations of this interface will process the provided preference data.
 * </p>
 */
public interface SetPreferenceInputBoundary {

    /**
     * Executes the preference setting with the given input data.
     *
     * @param inputData Data object containing user preferences.
     */
    void execute(SetPreferenceInputData inputData);
}
