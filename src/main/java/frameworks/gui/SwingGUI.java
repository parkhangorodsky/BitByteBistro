package frameworks.gui;

import app.Config;
import entity.User;
import entity.LoggedUserData;

import use_cases._common.authentication.AuthenticationService;
import use_cases._common.authentication.AuthenticationViewManager;
import use_cases._common.gui_common.view.*;
import use_cases._common.authentication.AuthenticationViewModel;
import use_cases._common.gui_common.view.Sidebar;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view.AppViewManager;

import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.add_to_my_recipe.MyRecipeView;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import entity.User;

import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases.search_recipe.gui.view.SearchRecipeView;

import use_cases.log_in.interface_adapter.controller.LoginController;
import use_cases.log_in.interface_adapter.presenter.LoginPresenter;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases.log_in.gui.view.LoginView;
import use_cases.log_in.use_case.interactor.LoginInteractor;

import use_cases.settting_preference.PreferenceView;
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
import java.beans.PropertyChangeEvent;

public class SwingGUI implements GUI {

    private final ViewManagerModel authenticationViewManagerModel;

    // ViewManager
    private ViewManagerModel viewManagerModel;
    private AppViewManager viewManager;
    private AuthenticationViewManager authenticationViewManager;

    // ViewModels
    private SearchRecipeViewModel searchRecipeViewModel;
    private AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel;
    private LoginViewModel loginViewModel;
    private SignUpViewModel signUpViewModel;
    private RecipeToGroceryViewModel recipeToGroceryViewModel;
    private AuthenticationViewModel authenticationViewModel;

    // Config
    private Config config;

    // UI
    private JFrame mainFrame;
    private JFrame loginFrame;
    private CardLayout mainCardLayout;
    private CardLayout loginCardLayout;
    private JPanel viewPanel;
    private JPanel loginPanel;
    private JPanel sideBar;

    /**
     * Constructor for the Swing GUI. Takes in a Config Argument and stores ViewModels.
     * @param config
     */
    public SwingGUI(Config config) {
        // Get ViewModels from config and save it.
        this.config = config;
        this.viewManagerModel = config.getViewManagerModel();
        this.authenticationViewManagerModel = config.getViewManagerModel();
        this.searchRecipeViewModel = config.getSearchRecipeViewModel();
        this.advancedSearchRecipeViewModel = config.getAdvancedSearchRecipeViewModel();
        this.loginViewModel = config.getLoginViewModel();
        this.signUpViewModel = config.getSignUpViewModel();
        this.recipeToGroceryViewModel = config.getRecipeToGroceryViewModel();
        this.authenticationViewModel = config.getAuthenticationViewModel();
        this.authenticationViewModel.addPropertyChangeListener(this);

    }

    /**
     * Initializes all the visible component of GUI.
     * @param config
     */
    public void initialize(Config config) {
        // Initialize the frames
        initializeLoginFrame();
        createLoginPanel();

        // Create ViewManagers
        this.authenticationViewManager = new AuthenticationViewManager(this.loginPanel, this.loginCardLayout, this.authenticationViewManagerModel);

        // Create Login components
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel, authenticationViewManagerModel, authenticationViewModel);
        LoginInteractor loginInteractor = new LoginInteractor(loginPresenter, config.getDataAccessInterface());
        LoginController loginController = new LoginController(loginInteractor);
        LoginView loginView = new LoginView(loginController, loginViewModel, authenticationViewManagerModel, this);

        // Add LoginView to authentication ViewManager with a unique card name
        authenticationViewManager.addView(loginView);

        // Create Sign-Up components
        SignUpPresenter signUpPresenter = new SignUpPresenter(signUpViewModel, authenticationViewManagerModel);
        SignUpInteractor signUpInteractor = new SignUpInteractor(signUpPresenter, config.getDataAccessInterface());
        SignUpController signUpController = new SignUpController(signUpInteractor);
        SignUpView signUpView = new SignUpView(signUpController, signUpViewModel, authenticationViewManagerModel);

        // Add SignUpView to authentication ViewManager with a unique card name
        authenticationViewManager.addView(signUpView);

        // Listen to view changes
        this.authenticationViewManagerModel.addPropertyChangeListener(evt -> {
            if ("view change".equals(evt.getPropertyName())) {
                String newViewName = (String) evt.getNewValue();
                loginCardLayout.show(loginPanel, newViewName);
            }
        });

        // Show the login view by default
        this.authenticationViewManagerModel.setActiveView("LoginView");

        // Show login frame
        this.loginFrame.pack();
        this.loginFrame.setVisible(true);
    }

    private void initializeMainFrame() {
        this.mainFrame = new JFrame();
        this.mainFrame.setSize(1000, 750);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setResizable(true);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("");
        ImageIcon icon = new ImageIcon("src/main/resources/images/smiley.png");
        this.mainFrame.setIconImage(icon.getImage());
        this.mainFrame.getContentPane().setBackground(new Color(238, 237, 227));
        this.mainFrame.setLocationRelativeTo(null);

        if (System.getProperty("os.name").equals("Mac OS X")) {
            this.mainFrame.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
            this.mainFrame.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
        }
    }

    public void initializeLoginFrame() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(1000, 750);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new BorderLayout());
    }

    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.mainCardLayout = new CardLayout();
        this.viewPanel = new JPanel(mainCardLayout);
        mainPanel.add(viewPanel, BorderLayout.CENTER);
        this.mainFrame.add(mainPanel);
    }

    private void createLoginPanel() {
        this.loginPanel = new JPanel(new CardLayout());
        this.loginCardLayout = new CardLayout();
        this.loginPanel.setLayout(loginCardLayout);
        this.loginFrame.add(loginPanel, BorderLayout.CENTER);
    }


    public void initializeOtherViews() {
        initializeMainFrame();
        createMainPanel();

        this.viewManager = new AppViewManager(this.viewPanel, this.mainCardLayout, this.viewManagerModel);

        JPanel mainPanel = (JPanel) this.mainFrame.getContentPane().getComponent(0);
        sideBar = new Sidebar(this.viewManagerModel, config.getLogoutController());
        mainPanel.add(sideBar, BorderLayout.WEST);
        AuthenticationService authService = new AuthenticationService(config.getDataAccessInterface());

        // Create HomeView components
        HomeView homeView = new HomeView(viewManagerModel);
        viewManager.addView(homeView);

        // Create SearchRecipe components
        SearchRecipeController searchRecipeController = config.getSearchRecipeController();
        DisplayRecipeDetailController displayRecipeDetailController = config.getDisplayRecipeDetailController();
        AddToMyRecipeController addToMyRecipeController = config.getAddToMyRecipeController();
        // Get the NutritionDisplayController from config
        SearchRecipeView searchRecipeView = new SearchRecipeView(searchRecipeViewModel,
                searchRecipeController,
                displayRecipeDetailController,
                addToMyRecipeController,
                advancedSearchRecipeViewModel,
                viewManagerModel);

        // Add SearchRecipeView to ViewManager
        viewManager.addView(searchRecipeView);


        MyRecipeView myRecipeView = new MyRecipeView(config.getMyRecipeViewModel());
        viewManager.addView(myRecipeView);


        // Create RecipeToGrocery components
        RecipeToGroceryPresenter recipeToGroceryPresenter = new RecipeToGroceryPresenter(viewManagerModel, recipeToGroceryViewModel);
        RecipeToGroceryInteractor recipeToGroceryInteractor = new RecipeToGroceryInteractor(recipeToGroceryPresenter, config.getRecipeAPI());
        RecipeToGroceryController recipeToGroceryController = new RecipeToGroceryController(recipeToGroceryInteractor, authService);
        RecipeToGroceryView recipeToGroceryView = new RecipeToGroceryView(recipeToGroceryViewModel, recipeToGroceryController, authService, viewManagerModel);

        // Add RecipeToGroceryView to ViewManager
        viewManager.addView(recipeToGroceryView);

        //Create PopUpView
        PreferenceView preferenceView = new PreferenceView(mainFrame, config.getSetPreferenceController());
        mainFrame.setEnabled(true);
        viewManager.addPopupView("Preference", preferenceView);

        this.mainFrame.pack();
        this.mainFrame.setVisible(true);


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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("authenticationSuccess".equals(evt.getPropertyName())) {
            // Handle authentication success
            this.loginFrame.dispose();
            initializeOtherViews();
        }
        // Other property changes...
        if ("logoutSuccess".equals(evt.getPropertyName())){
            System.out.println("hi");
            // Dispose of all frames on logout
            disposeAllFrames();
            // Optionally, re-initialize the login frame
            initialize(config);
        }

    }

    public void disposeAllFrames() {
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            frame.dispose();
        }
    }



}
