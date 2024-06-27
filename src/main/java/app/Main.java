package app;

import use_case.interactor.SearchRecipeInteractor;
import view.SearchRecipeView;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {

        // Configuration
        Config config = new Config();
        SearchRecipeInteractor searchRecipeUseCase = config.searchRecipeInteractor();

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

        // Create SearchRecipeView
        SearchRecipeView searchRecipeView = new SearchRecipeView(searchRecipeUseCase);
        views.add(searchRecipeView, searchRecipeView.viewname);

        // Set current panel to searchRecipeView
        CardLayout currPanel = (CardLayout) views.getLayout();
        currPanel.show(views, searchRecipeView.viewname);

        // Set application visible
        application.setVisible(true);
    }
}
