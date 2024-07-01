package view.view_components.layouts;

import javax.swing.*;
import java.awt.*;

public class VerticalFlowLayout implements LayoutManager {

    private int vgap;

    public VerticalFlowLayout(int vgap) {
        this.vgap = vgap;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        // No-op
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        // No-op
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return calculateSize(parent, true);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return calculateSize(parent, false);
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int maxWidth = parent.getWidth() - insets.left - insets.right;
        int y = insets.top;

        for (Component comp : parent.getComponents()) {
            if (comp.isVisible()) {
                Dimension d = comp.getPreferredSize();
                comp.setBounds(insets.left, y, maxWidth, d.height);
                y += d.height + vgap;
            }
        }
    }

    private Dimension calculateSize(Container parent, boolean preferred) {
        int width = 0;
        int height = 0;

        for (Component comp : parent.getComponents()) {
            if (comp.isVisible()) {
                Dimension d = preferred ? comp.getPreferredSize() : comp.getMinimumSize();
                width = Math.max(width, d.width);
                height += d.height + vgap;
            }
        }

        Insets insets = parent.getInsets();
        width += insets.left + insets.right;
        height += insets.top + insets.bottom - vgap; // Remove extra gap added after the last component
        return new Dimension(width, height);
    }

    public static void main(String[] args) {
        // Create a frame and set its default close operation
        JFrame frame = new JFrame("Custom Vertical Flow Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with the custom VerticalFlowLayout
        JPanel panel = new JPanel(new VerticalFlowLayout(10));

        // Add some buttons to the panel
        panel.add(new JButton("Button 1"));
        panel.add(new JButton("Button 2"));
        panel.add(new JButton("Button 3"));

        // Add the panel to the frame
        frame.add(panel);

        // Pack the frame and make it visible
        frame.pack();
        frame.setVisible(true);
    }
}
