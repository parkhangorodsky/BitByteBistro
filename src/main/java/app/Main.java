package app;

import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import interface_adapter.view_model.ViewModel;
import use_case.interactor.SearchRecipeInteractor;
import view.SearchRecipeView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {

        // Create ViewModels
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel("search recipe");

        // Configuration
        Config config = new Config();
        SearchRecipeInteractor searchRecipeUseCase = config.searchRecipeInteractor(viewManagerModel, searchRecipeViewModel);

        // Initialize Frame
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setTitle("BitByteBistro");
        application.setLocationRelativeTo(null);
        application.setSize(800, 600);

        // Initialize Layout and Panel
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Create Views
        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);
        SearchRecipeView searchRecipeView = new SearchRecipeView(searchRecipeViewModel, searchRecipeUseCase);
        views.add(searchRecipeView, searchRecipeView.viewname);

        // Set current panel to searchRecipeView
        viewManagerModel.setActiveView(searchRecipeView.viewname);
        viewManagerModel.firePropertyChanged();
        // Set application visible
        application.setVisible(true);
    }
}
