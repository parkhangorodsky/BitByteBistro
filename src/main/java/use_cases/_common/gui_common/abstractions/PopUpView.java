package use_cases._common.gui_common.abstractions;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Overview: Abstract class representing a popup view that extends {@link JFrame} and implements {@link ThemeColoredObject}.
 * This class provides common functionality for popup windows,
 * including customization for macOS, centering the popup,
 * and managing the state of the parent window.
 */
public abstract class PopUpView extends JFrame implements ThemeColoredObject {
    JFrame parent;

    public PopUpView(JFrame parent){
        this.parent = parent;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.parent.setEnabled(false);
        this.setBackground(claudeWhite);

        // Disable title bar (to look better) for mac OS.
        if (System.getProperty("os.name").equals("Mac OS X")) {
            this.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
            this.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
        }

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parent.setEnabled(true); // Enable mainFrame when newWindow is closed
            }
        });
    }

    public void positionFrameAtCenter(JFrame parentFrame) {
        // Calculate centered position relative to parent frame
        int parentX = parentFrame.getX();
        int parentY = parentFrame.getY();
        int parentWidth = parentFrame.getSize().width;
        int parentHeight = parentFrame.getSize().height;

        // Calculate the position, and set location.
        int newX = parentX + (parentWidth - this.getWidth()) / 2;
        int newY = parentY + (parentHeight - this.getHeight()) / 2;
        this.setLocation(newX, newY);
    }

    public void enableParent() {
        this.parent.setEnabled(true);
    }

}
