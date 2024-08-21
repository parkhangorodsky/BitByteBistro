package use_cases.core_functionality;

/**
 * The `CoreFunctionalityInputBoundary` interface defines the contract for input boundaries in the core functionality use cases.
 * Implementations of this interface are responsible for handling the execution logic based on input data.
 */
public interface CoreFunctionalityInputBoundary {

    /**
     * Executes the core functionality with the given input data.
     *
     * @param inputData The input data required for executing the core functionality.
     */
    void execute(CoreFunctionalityInputData inputData);
}
