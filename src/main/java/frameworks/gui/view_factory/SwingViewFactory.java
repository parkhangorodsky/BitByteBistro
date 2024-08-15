package frameworks.gui.view_factory;

import app.config.Config;
import frameworks.gui.view_factory.division.*;

public class SwingViewFactory extends ViewFactory {

    public SwingViewFactory(Config config) {
        super(config);
        this.addDivision("SearchRecipeView", new SearchRecipeViewSwingDivision());
        this.addDivision("LoginView", new LoginViewSwingDivision());
        this.addDivision("SignUpView", new SignUpViewSwingDivision());
        this.addDivision("HomeView", new HomeViewSwingDivision());
        this.addDivision("MyRecipeView", new MyRecipeViewSwingDivision());
        this.addDivision("MyGroceryView", new MyGroceryViewSwingDivision());
        this.addDivision("MyFridgeView", new FridgeInventoryViewSwingDivision());
    }
}
