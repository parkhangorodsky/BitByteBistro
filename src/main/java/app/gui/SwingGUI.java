package app.gui;


import api.RecipeAPI;
import app.Config;
import app.use_case_factory.SearchRecipeUseCaseFactory;
import interface_adapter.controller.SearchRecipeController;
import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.SearchRecipeView;
import view.View;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class SwingGUI implements GUI {

    // ViewManager
    private ViewManagerModel viewManagerModel;
    private ViewManager viewManager;

    // ViewModels
    private SearchRecipeViewModel searchRecipeViewModel;

    // UI
    private JFrame application;
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

        // Initialize Frame of application
        this.application = new JFrame();

        this.application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.application.setTitle("BitByteBistro");
        this.application.setLocationRelativeTo(null);
        this.application.setSize(800, 600);

        // Initialize main Layout and main Panel
        this.mainCardLayout = new CardLayout();
        this.mainPanel = new JPanel(mainCardLayout);
        this.application.add(mainPanel);

        // Create ViewManager
        this.viewManager =  new ViewManager(mainPanel, mainCardLayout, this.viewManagerModel);

        // Create Views
        SearchRecipeView searchRecipeView = createUseCaseIntegratedSearchRecipeView(config.getSearchRecipeController());

        // Set initial View and make application visible
        this.setActiveView(searchRecipeView);
        this.application.setVisible(true);
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
