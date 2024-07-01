package app.gui;

import app.Config;
import interface_adapter.controller.SearchRecipeController;
import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.SearchRecipeView;
import view.View;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import com.apple.eawt.Application;

public class SwingGUI implements GUI {

    // ViewManager
    private ViewManagerModel viewManagerModel;
    private ViewManager viewManager;

    // ViewModels
    private SearchRecipeViewModel searchRecipeViewModel;

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

    };


    /**
     * Initializes all the visible component of GUI.
     * @param config
     */
    public void initialize(Config config) {

        // Initialize Frame of frame
        frame = new JFrame(); // Initialize Frame
        frame.setSize(1000, 750);
        frame.setLayout(new BorderLayout());// Set size of the frame
        frame.setResizable(true); // Disable resizing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close program upon clicking exit button

        frame.setTitle(" "); // Set tile of the frame
        ImageIcon icon = new ImageIcon("src/main/resources/images/smiley.png"); // Create ImageIcon
        frame.setIconImage(icon.getImage()); // Set Icon of the app

        frame.getContentPane().setBackground(new Color(238, 237, 227)); // Set background color
        frame.setLocationRelativeTo(null);

        // Disable title bar (to look better) for mac OS.
        if (System.getProperty("os.name").equals("Mac OS X")) {
            frame.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
            frame.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
        }

        // Initialize main Layout and main Panel
        this.mainCardLayout = new CardLayout();
        this.mainPanel = new JPanel(mainCardLayout);
        this.frame.add(mainPanel);

        // Create ViewManager
        this.viewManager =  new ViewManager(mainPanel, mainCardLayout, this.viewManagerModel);

        // Create Views
        SearchRecipeView searchRecipeView = createUseCaseIntegratedSearchRecipeView(config.getSearchRecipeController());

        // Set initial View and make frame visible
        this.setActiveView(searchRecipeView);
        this.frame.setVisible(true);
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
        SearchRecipeView searchRecipeView = new SearchRecipeView(searchRecipeViewModel, searchRecipeController);
        viewManager.addView(searchRecipeView);
        return searchRecipeView;
    }


}
