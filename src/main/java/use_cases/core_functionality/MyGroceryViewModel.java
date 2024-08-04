package use_cases.core_functionality;

import entity.LoggedUserData;
import entity.ShoppingList;
import entity.User;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MyGroceryViewModel extends ViewModel {
    private User user;
    private PropertyChangeSupport support;

    public MyGroceryViewModel(String viewName) {
        super(viewName);
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName) {
        if (propertyName.equals("grocery")) {
            this.user = LoggedUserData.getLoggedInUser();
            support.firePropertyChange(propertyName, null, user.getShoppingLists().getFirst());
        }
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {this.user = user;}

}