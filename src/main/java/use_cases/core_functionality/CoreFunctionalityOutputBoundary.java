package use_cases.core_functionality;

/**
 * The `CoreFunctionalityOutputBoundary` interface defines the contract for preparing the success view
 * in response to core functionality operations. It is used to present the results of use cases related
 * to managing recipes and shopping lists.
 */
public interface CoreFunctionalityOutputBoundary {

    /**
     * Prepares the success view with the provided output data. This method is called to present the
     * results of a successful operation, such as adding a recipe to a shopping list.
     *
     * @param outputData The data to be used for preparing the success view. This includes the updated
     *                   shopping list and any necessary information for the view model.
     */
    void prepareSuccessView(CoreFunctionalityOutputData outputData);
}
