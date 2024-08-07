package use_cases._common.gui_common.view_components.round_component;

import use_cases._common.gui_common.abstractions.ThemeColoredObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundPanel extends JPanel implements ThemeColoredObject {

    private int cornerRadius = 10;
    Color borderColor = getBackground();// Adjust the corner radius as needed

    public RoundPanel() {
        super();
        setOpaque(false); // Ensure the panel is transparent
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Create a rounded rectangle shape
        Shape roundedRect = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        // Use antialiasing to smooth out the edges
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill the panel with a background color (if needed)
        g2d.setColor(getBackground());
        g2d.fill(roundedRect);

        // Draw the border of the rounded rectangle
        g2d.setColor(borderColor);
        g2d.draw(roundedRect);

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize(); // Adjust size as needed
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        this.revalidate();
        this.repaint();
    }
}
