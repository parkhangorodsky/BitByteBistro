package frameworks.gui;

import app.Config;

import frameworks.gui.view_factory.SwingViewFactory;
import use_cases._common.authentication.AuthenticationService;
import use_cases._common.authentication.AuthenticationViewManager;
import use_cases._common.authentication.AuthenticationViewModel;
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
    private AuthenticationViewModel authenticationViewModel;

    private AppViewManager viewManager;
    private AuthenticationViewManager authenticationViewManager;

    // View Factory


    // Config
    private Config config;

    // UI
    private JFrame mainFrame;

    private CardLayout loginCardLayout;
    private JPanel loginPanel;

    private CardLayout mainCardLayout;
    private JPanel viewPanel;

    /**
     * Constructor for the Swing GUI. Takes in a Config Argument and stores ViewModels.
     * */
    public SwingGUI() {}

    @Override
    public void initialize(Config config) {
        this.config = config;
        this.viewFactory = new SwingViewFactory(config);

        this.viewManagerModel = config.getViewManagerModel();
        this.authenticationViewModel = config.getAuthenticationViewModel();
        this.authenticationViewModel.addPropertyChangeListener(this);

        initializeLoginView();
    }

    /**
     * Initializes all the visible component of GUI.
     */
    private void initializeLoginView() {
        // Initialize the frames
        initializeLoginFrame();

        // Create ViewManagers
        this.authenticationViewManager = new AuthenticationViewManager(this.loginPanel, this.loginCardLayout, this.viewManagerModel);

        // Add LoginView to authentication ViewManager with a unique card name
        authenticationViewManager.addView(viewFactory.generateLoginView());

        // Add SignUpView to authentication ViewManager with a unique card name
        authenticationViewManager.addView(viewFactory.generateSignUpView());

        // Show the login view by default
        this.viewManagerModel.setActiveView("LoginView");

        // Show login frame
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    private void initializeLoginFrame() {
        this.mainFrame = createFrame();
        this.loginCardLayout = new CardLayout();
        this.loginPanel = new JPanel(this.loginCardLayout);
        this.mainFrame.add(loginPanel, BorderLayout.CENTER);
    }

    private void initializeAppFrame() {
        this.mainFrame = createFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());

        this.mainCardLayout = new CardLayout();
        this.viewPanel = new JPanel(mainCardLayout);

        JPanel sideBar = new Sidebar(config.getViewManagerModel(), config.getLogoutController());

        mainPanel.add(viewPanel, BorderLayout.CENTER);
        mainPanel.add(sideBar, BorderLayout.WEST);
        this.mainFrame.add(mainPanel);
    }

    private void initializeAppViews() {
        initializeAppFrame();

        this.viewManager = new AppViewManager(this.viewPanel, this.mainCardLayout, this.viewManagerModel);

        AuthenticationService authService = new AuthenticationService(config.getDataAccessInterface());

        viewManager.addView(viewFactory.generateHomeView());
        viewManager.addView(viewFactory.generateSearchRecipeView());
        viewManager.addView(viewFactory.generateMyRecipeView());
        viewManager.addView(viewFactory.generateSearchRecipeView());

        //Create PopUpView
        PreferenceView preferenceView = new PreferenceView(mainFrame, config.getSetPreferenceController());
        mainFrame.setEnabled(true);
        viewManager.addPopupView("Preference", preferenceView);

        this.mainFrame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("authenticationSuccess".equals(evt.getPropertyName())) {
            // Handle authentication success
            this.mainFrame.dispose();
            initializeAppViews();
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

    private JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setSize(1000, 750);
        frame.setLayout(new BorderLayout());
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("");
        ImageIcon icon = new ImageIcon("src/main/resources/images/smiley.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(new Color(238, 237, 227));
        frame.setLocationRelativeTo(null);

        if (System.getProperty("os.name").equals("Mac OS X")) {
            frame.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
            frame.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
        }

        return frame;
    }

    public void disposeAllFrames() {
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            frame.dispose();
        }
    }



}
