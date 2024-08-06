package frameworks.gui;

import app.config.Config;

import frameworks.gui.view_factory.SwingViewFactory;
import use_cases._common.authentication.AuthenticationViewManager;
import use_cases._common.authentication.AuthenticationViewModel;
import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.view.Sidebar;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases._common.gui_common.view.AppViewManager;
import use_cases.setting_preference.PreferenceView;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class SwingGUI extends GUI {

    // ViewManager
    private ViewManagerModel viewManagerModel;

    // View Factory

    // Config
    private Config config;

    // UI

    private JFrame mainFrame;
    private CardLayout mainCardLayout;
    private JPanel mainPanel;

    // Initialize Login View
    private CardLayout loginCardLayout;
    private JPanel loginPanel;
    private AuthenticationViewManager authenticationViewManager;

    private CardLayout appCardLayout;
    private AppViewManager appViewManager;
    private JPanel appViewPanel;

    /**
     * Constructor for the Swing GUI. Takes in a Config Argument and stores ViewModels.
     * */
    public SwingGUI() {}

    @Override
    public void initialize(Config config) {
        this.config = config;
        this.viewFactory = new SwingViewFactory(config);

        this.viewManagerModel = config.getViewManagerModel();
        AuthenticationViewModel authenticationViewModel = config.getAuthenticationViewModel();
        authenticationViewModel.addPropertyChangeListener(this);

        initializeMainFrame();
        initializeLoginView();
        initializeAppViews();
    }

    public void run() {
        generateLoginView();
        showLoginView();
    }

    private void initializeMainFrame() {

        this.mainFrame = new JFrame();
        this.mainCardLayout = new CardLayout();
        this.mainPanel = new JPanel(mainCardLayout);

        mainFrame.setSize(1000, 750);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setResizable(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("");
        ImageIcon icon = new ImageIcon("src/main/resources/images/smiley.png");
        mainFrame.setIconImage(icon.getImage());
        mainFrame.getContentPane().setBackground(new Color(238, 237, 227));
        mainFrame.setLocationRelativeTo(null);

        if (System.getProperty("os.name").equals("Mac OS X")) {
            mainFrame.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
            mainFrame.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
        }

        mainFrame.add(mainPanel);
    }

    /**
     * Initializes all the visible component of GUI.
     */

    private void initializeLoginView() {
        // Initialize Login View
        loginCardLayout = new CardLayout();
        loginPanel = new JPanel(loginCardLayout);

        // Create ViewManagers
        authenticationViewManager = new AuthenticationViewManager(loginPanel, loginCardLayout, this.viewManagerModel);

    }

    private void generateLoginView() {
        // Add LoginView to authentication ViewManager with a unique card name
        authenticationViewManager.addView(viewFactory.generateLoginView());

        // Add SignUpView to authentication ViewManager with a unique card name
        authenticationViewManager.addView(viewFactory.generateSignUpView());
        this.mainPanel.add(loginPanel, "Login Window");

    }

    private void showLoginView() {
        this.viewManagerModel.setActiveView("LoginView");
        viewManagerModel.firePropertyChanged();
        this.mainCardLayout.show(mainPanel, "Login Window");
        this.mainFrame.setVisible(true);
    }

    private void disposeLoginView() {
        loginPanel.removeAll();
    }

    private void initializeAppViews() {

        appCardLayout = new CardLayout();
        appViewPanel = new JPanel(appCardLayout);
        appViewManager = new AppViewManager(appViewPanel, appCardLayout, this.viewManagerModel);

    }

    private void generateAppViews() {
        JPanel appPanel = new JPanel(new BorderLayout());

        JPanel sideBar = new Sidebar(config.getViewManagerModel(), config.getLogoutController());

        this.appViewManager.addView(viewFactory.generateHomeView());
        this.appViewManager.addView(viewFactory.generateSearchRecipeView());
        this.appViewManager.addView(viewFactory.generateMyRecipeView());
        this.appViewManager.addView(viewFactory.generateSearchRecipeView());

        //Create PopUpView
        PreferenceView preferenceView = new PreferenceView(mainFrame, config.getSetPreferenceController());
        this.mainFrame.setEnabled(true);
        this.appViewManager.addPopupView("Preference", preferenceView);

        appPanel.add(appViewPanel, BorderLayout.CENTER);
        appPanel.add(sideBar, BorderLayout.WEST);

        this.mainPanel.add(appPanel, "App Window");

    }

    private void showAppView() {
        this.viewManagerModel.setActiveView("Home");
        viewManagerModel.firePropertyChanged();
        mainCardLayout.show(mainPanel, "App Window");
        mainFrame.setVisible(true);
    }

    private void disposeAppViews() {
        appViewManager.removePopupViews();
        appViewPanel.removeAll();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("authenticationSuccess".equals(evt.getPropertyName())) {
            // Handle authentication success
            disposeLoginView();
            generateAppViews();
            showAppView();


        } else if ("logoutSuccess".equals(evt.getPropertyName())){
            disposeAppViews();
            generateLoginView();
            showLoginView();
        }
    }



}
