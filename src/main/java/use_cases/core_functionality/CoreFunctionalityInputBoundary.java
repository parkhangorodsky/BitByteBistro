package use_cases.core_functionality;

public interface CoreFunctionalityInputBoundary {
    void addRecipe(CoreFunctionalityInputData inputData);
    void removeRecipe(CoreFunctionalityInputData inputData);
    void removeIngredients(CoreFunctionalityInputData inputData);
}
