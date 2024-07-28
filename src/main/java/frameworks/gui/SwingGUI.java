package frameworks.gui;

import app.Config;
import entity.User;
import entity.LoggedUserData;

import use_cases._common.authentication.AuthenticationService;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view.ViewManager;

import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.nutrition_display.interface_adapter.controller.NutritionDisplayController;

import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases.search_recipe.use_case.interactor.SearchRecipeInteractor;
import use_cases.search_recipe.gui.view.SearchRecipeView;

import use_cases.log_in.interface_adapter.controller.LoginController;
import use_cases.log_in.interface_adapter.presenter.LoginPresenter;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases.log_in.gui.view.LoginView;
import use_cases.log_in.use_case.interactor.LoginInteractor;

import use_cases.sign_up.gui.view.SignUpView;
import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.sign_up.interface_adapter.presenter.SignUpPresenter;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases.sign_up.use_case.interactor.SignUpInteractor;

import use_cases.recipe_to_grocery.interface_adapter.controller.RecipeToGroceryController;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeToGroceryViewModel;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryPresenter;
import use_cases.recipe_to_grocery.use_case.interactor.RecipeToGroceryInteractor;
import use_cases.recipe_to_grocery.gui.RecipeToGroceryView;

import javax.swing.*;
import java.awt.*;

public class SwingGUI implements GUI {

    // ViewManager
    private ViewManagerModel viewManagerModel;
    private ViewManager viewManager;

    // ViewModels
    private SearchRecipeViewModel searchRecipeViewModel;
    private AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel;
    private LoginViewModel loginViewModel;
    private SignUpViewModel signUpViewModel;
    private RecipeToGroceryViewModel recipeToGroceryViewModel;

    // UI
    private JFrame frame;
    private CardLayout mainCardLayout;
    private JPanel mainPanel;

    /**
     * Constructor for the Swing GUI. Takes in a Config Argument and stores ViewModels.
     * @param config
     */
    public SwingGUI(Config config) {
        // Get ViewModels from config and save it.
        this.viewManagerModel = config.getViewManagerModel();
        this.searchRecipeViewModel = config.getSearchRecipeViewModel();
        this.advancedSearchRecipeViewModel = config.getAdvancedSearchRecipeViewModel();
        this.loginViewModel = config.getLoginViewModel();
        this.signUpViewModel = config.getSignUpViewModel();
        this.recipeToGroceryViewModel = config.getRecipeToGroceryViewModel();
    }

    /**
     * Initializes all the visible component of GUI.
     * @param config
     */
    public void initialize(Config config) {

        // Initialize main Layout and main Panel
        initializeMainFrame();
        createMainPanel();

        // Create ViewManager
        this.viewManager = new ViewManager(this.mainPanel, this.mainCardLayout, this.viewManagerModel);

        // Create Login components
        AuthenticationService authService = new AuthenticationService(config.getDataAccessInterface());
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel, viewManagerModel); // Pass AuthenticationService to LoginPresenter
        LoginInteractor loginInteractor = new LoginInteractor(loginPresenter, config.getDataAccessInterface()); // Pass AuthenticationService to LoginInteractor
        LoginController loginController = new LoginController(loginInteractor);
        LoginView loginView = new LoginView(loginController, loginViewModel, viewManagerModel);

        // Add LoginView to ViewManager
        viewManager.addView(loginView);

        // Create Sign-Up components
        SignUpPresenter signUpPresenter = new SignUpPresenter(signUpViewModel, viewManagerModel);
        SignUpInteractor signUpInteractor = new SignUpInteractor(signUpPresenter, config.getDataAccessInterface());
        SignUpController signUpController = new SignUpController(signUpInteractor);
        SignUpView signUpView = new SignUpView(signUpController, signUpViewModel, viewManagerModel);

        // Add SignUpView to ViewManager
        viewManager.addView(signUpView);

        // Create SearchRecipe components
        SearchRecipeController searchRecipeController = config.getSearchRecipeController();
        NutritionDisplayController nutritionDisplayController = config.getNutritionDisplayController();
        DisplayRecipeDetailController displayRecipeDetailController = config.getDisplayRecipeDetailController();
        AddToMyRecipeController addToMyRecipeController = config.getAddToMyRecipeController();
        // Get the NutritionDisplayController from config
        SearchRecipeView searchRecipeView = new SearchRecipeView(searchRecipeViewModel,
                searchRecipeController,
                nutritionDisplayController,
                displayRecipeDetailController,
                addToMyRecipeController,
                advancedSearchRecipeViewModel,
                viewManagerModel);

        // Add SearchRecipeView to ViewManager
        viewManager.addView(searchRecipeView);

        // Create RecipeToGrocery components
        RecipeToGroceryPresenter recipeToGroceryPresenter = new RecipeToGroceryPresenter(viewManagerModel, recipeToGroceryViewModel);
        RecipeToGroceryInteractor recipeToGroceryInteractor = new RecipeToGroceryInteractor(recipeToGroceryPresenter, config.getRecipeAPI());
        RecipeToGroceryController recipeToGroceryController = new RecipeToGroceryController(recipeToGroceryInteractor, authService);
        RecipeToGroceryView recipeToGroceryView = new RecipeToGroceryView(recipeToGroceryViewModel, recipeToGroceryController, authService, viewManagerModel);

        // Add RecipeToGroceryView to ViewManager
        viewManager.addView(recipeToGroceryView);

        // Listen to view changes
        this.viewManagerModel.addPropertyChangeListener(evt -> {
            if ("view change".equals(evt.getPropertyName())) {
                String newViewName = (String) evt.getNewValue();
                mainCardLayout.show(mainPanel, newViewName);
            }
        });

        // Show the login view by default
        this.viewManagerModel.setActiveView("LoginView");
        this.frame.pack();
        this.frame.setVisible(true);

    }

    private void initializeMainFrame() {
        // Initialize Frame of frame
        this.frame = new JFrame(); // Initialize Frame
        this.frame.setSize(1000, 750);
        this.frame.setLayout(new BorderLayout());// Set the size of this.frame
        this.frame.setResizable(true); // Disable resizing
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close program upon clicking exit button

        this.frame.setTitle(""); // Set tile of the this.frame
        ImageIcon icon = new ImageIcon("src/main/resources/images/smiley.png"); // Create ImageIcon
        this.frame.setIconImage(icon.getImage()); // Set Icon of the app

        this.frame.getContentPane().setBackground(new Color(238, 237, 227)); // Set background color
        this.frame.setLocationRelativeTo(null);

        // Disable title bar (to look better) for mac OS.
        if (System.getProperty("os.name").equals("Mac OS X")) {
            this.frame.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
            this.frame.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
        }
    }

    private void createMainPanel() {
        this.mainCardLayout = new CardLayout();
        this.mainPanel = new JPanel(mainCardLayout);
        this.frame.add(mainPanel);
    }

    @Override
    public void addView(View view) {
        viewManager.addView(view);
    }

    @Override
    public void setActiveView(View view) {
        viewManagerModel.setActiveView(view.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    // Create UseCasesIntegratedViews
    /**
     * This function creates SearchRecipeView, which is a subclass of JPanel (UI container).
     * Since this function outputs a UI component, it is placed in the GUI class.
     * Note that this function takes a Controller. The Controller parameter is UseCaseIntegrated.
     * @param searchRecipeController
     * @return
     */

}
