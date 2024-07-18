package use_cases._common.gui_common.view;

import use_cases._common.gui_common.abstractions.ThemeColoredObject;

import javax.swing.*;
import java.awt.*;

/**
 * Sidebar is a custom JPanel that serves as a sidebar component shared across different views.
 */
public class Sidebar extends JPanel implements ThemeColoredObject {
    public Sidebar() {
        setPreferredSize(new Dimension(230, 750));
        setBackground(claudeWhiteEmph);
    }
}
