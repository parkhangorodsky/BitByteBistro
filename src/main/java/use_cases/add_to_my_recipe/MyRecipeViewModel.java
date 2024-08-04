package use_cases.add_to_my_recipe;

import entity.LoggedUserData;
import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Overview: ViewModel for managing the state of the user's recipes in the view.
 * Encapsulation: This class encapsulates logic for storing data required to
 * display the MyRecipeView and firing property change upon updates.
 */
public class MyRecipeViewModel extends ViewModel {
    private User user;
    private PropertyChangeSupport support;

    public MyRecipeViewModel(String viewName) {
        super(viewName);
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName) {
        if (propertyName.equals("added recipe")) {
            this.user = LoggedUserData.getLoggedInUser();
            support.firePropertyChange(propertyName, null, user.getRecipes());
        }
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {this.user = user;}



}
