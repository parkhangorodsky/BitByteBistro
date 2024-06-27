package interface_adapter.view_model;

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
