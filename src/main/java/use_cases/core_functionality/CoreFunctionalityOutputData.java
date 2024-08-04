package use_cases.core_functionality;

import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

public class CoreFunctionalityOutputData {
    User user;
    PropertyChangeFirer parentModel;

    /**
     * Constructor for CoreFunctionalityOutputData
     *
     * @param user        The user to whom the recipe has been added.
     * @param parentModel The model that will be notified of property changes.
     */
    public CoreFunctionalityOutputData(User user, PropertyChangeFirer parentModel) {
        this.user = user;
        this.parentModel = parentModel;
    }

    public User getUser() {return user;}
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
