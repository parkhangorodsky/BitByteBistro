package view.view_components.round_component;

import org.jetbrains.annotations.NotNull;
import view.view_components.interfaces.ThemedComponent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

public class RoundTextField extends JTextField implements ThemedComponent {

    private Shape shape;
    private Color borderColor = claudeWhiteEmph;
    private final int arc = 10;
    private int borderThickness = 1;

    private String placeholder = "";



    public RoundTextField() {
        setOpaque(false);
        setMargin(new Insets(0, 15, 0, 15));// As we will be drawing the background ourselves, set it to not opaque

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Draws the rounded background
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Draws the rounded border
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        if (getText().isEmpty() && !isFocusOwner()) {
            FontMetrics fm = g.getFontMetrics();
            int textHeight = fm.getHeight();
            int y = (getHeight() - textHeight) / 2 + fm.getAscent();
            g2.drawString(placeholder, getInsets().left, y);
        }
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
        g2.dispose();
    }

    @Override
    public void setBorder(Border border) {
        // Check if the border is an instance of LineBorder to extract color and thickness
        if (border instanceof LineBorder) {
            LineBorder lineBorder = (LineBorder) border;
            borderColor = lineBorder.getLineColor();
            borderThickness = lineBorder.getThickness();
        }
        super.setBorder(border);
        repaint(); // Repaint the component to reflect the border change
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
        }
        return shape.contains(x, y);
    }

    public void setMargin(Insets margin) {
        super.setBorder(new EmptyBorder(margin));
        repaint();
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    @Override
    public void setSize(@NotNull Dimension d) {
        super.setSize(d);
    }

    @Override
    public void setFont(Font f) {
        super.setFont(f);
        this.revalidate();
        this.repaint();
    }
}
