package view.view_components.round_component;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundBorder extends AbstractBorder {
    private final Color borderColor;
    private final int arc;

    public RoundBorder(Color borderColor, int arc) {
        this.borderColor = borderColor;
        this.arc = arc;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, arc, arc));
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(arc, arc, arc, arc); // Adjust as needed
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = arc; // Adjust as needed
        return insets;
    }
}
