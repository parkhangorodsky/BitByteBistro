package app;

import app.gui.GUI;
import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.interactor.SearchRecipeInteractor;
import view.SearchRecipeView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {

        // Configuration. Config object is where we save the setting.
        // This object contains all the initial setting value and its getter method.
        Config config = new Config();

        // Get GUI from config. Currently, GUI is set to be SwingGUI in config.
        // Then the SwingGUI is initialized.
        GUI gui = config.getGUI();
        gui.initialize(config);
    }
}
