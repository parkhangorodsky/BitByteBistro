package use_cases._common.gui_common.abstractions;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class PopUpView extends JFrame implements ThemeColoredObject {

    public PopUpView(JFrame parent){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        parent.setEnabled(false);
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

        int newX = parentX + (parentWidth - this.getWidth()) / 2;
        int newY = parentY + (parentHeight - this.getHeight()) / 2;

        this.setLocation(newX, newY);
    }

}
