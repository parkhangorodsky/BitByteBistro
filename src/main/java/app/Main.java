package app;

import frameworks.gui.GUI;

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
