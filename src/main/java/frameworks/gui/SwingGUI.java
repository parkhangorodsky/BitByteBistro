package frameworks.gui;

import app.Config;
import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.search_recipe.gui.view.SearchRecipeView;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class SwingGUI implements GUI {

    // ViewManager
    private ViewManagerModel viewManagerModel;
    private ViewManager viewManager;

    // ViewModels
    private SearchRecipeViewModel searchRecipeViewModel;
    private AdvancedSearchRecipeViewModel advancedSearchRecipeViewModel;


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
    };

    /**
     * Initializes all the visible component of GUI.
     * @param config
     */
    public void initialize(Config config) {


        // Initialize main Layout and main Panel
        initializeMainFrame();
        createMainPanel();

        // Create ViewManager
        this.viewManager =  new ViewManager(this.mainPanel, this.mainCardLayout, this.viewManagerModel);

        // Create Views
        SearchRecipeView searchRecipeView = createUseCaseIntegratedSearchRecipeView(config.getSearchRecipeController());

        // Set initial View and make frame visible
        this.setActiveView(searchRecipeView);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void initializeMainFrame() {
        // Initialize Frame of frame
        this.frame = new JFrame(); // Initialize Frame
        this.frame.setSize(1000, 750);
        this.frame.setLayout(new BorderLayout());// Set size of the this.frame
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
    @Override
    public SearchRecipeView createUseCaseIntegratedSearchRecipeView(SearchRecipeController searchRecipeController) {
        SearchRecipeView searchRecipeView = new SearchRecipeView(searchRecipeViewModel, searchRecipeController, nutritionDisplayController, advancedSearchRecipeViewModel);
        viewManager.addView(searchRecipeView);
        return searchRecipeView;
    }
}
