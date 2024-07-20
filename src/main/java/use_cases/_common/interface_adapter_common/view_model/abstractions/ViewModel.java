package use_cases._common.interface_adapter_common.view_model.abstractions;

/**
 * Overview: The abstract class for ViewModel. It provides the default constructor and default method.
 */
public abstract class ViewModel {
    private String viewName;

    // Constructor
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    // Getter for viewName
    public String getViewName() {
        return this.viewName;
    }
}
