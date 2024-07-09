package view.view_components;

import view.view_components.interfaces.ThemedComponent;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel implements ThemedComponent {
    public Sidebar() {
        setPreferredSize(new Dimension(230, 750));
        setBackground(claudeWhiteEmph);
    }
}
