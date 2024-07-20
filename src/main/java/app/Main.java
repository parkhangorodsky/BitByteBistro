package app;
import frameworks.gui.GUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Check if the user wants to run in console mode or GUI mode
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'console' to run in console mode or press Enter to run in GUI mode:");
        String mode = scanner.nextLine();

        if (mode.equalsIgnoreCase("console")) {
            ConsoleModeRunner consoleModeRunner = new ConsoleModeRunner();
            consoleModeRunner.run();
        } else {
            runGUIMode();
        }
    }

    private static void runGUIMode() {
        // Configuration. Config object is where we save the setting.
        // This object contains all the initial setting value and its getter method.
        Config config = new Config();

        // Get GUI from config. Currently, GUI is set to be SwingGUI in config.
        // Then the SwingGUI is initialized.
        GUI gui = config.getGUI();
        gui.initialize(config);
    }
}
