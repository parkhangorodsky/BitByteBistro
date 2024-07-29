package use_cases._common.gui_common.view_components.round_component;

import use_cases._common.gui_common.abstractions.ThemeColoredObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundButton extends JButton implements ThemeColoredObject {


    private Color borderColor = claudeWhiteEmph;

    private Color backgroundColor = claudeWhite;
    private Color hoverBackgroundColor = claudeWhiteEmph;

    private Color foregroundColor = claudeWhiteEmph;
    private Color hoverForegroundColor = claudeBlack;

    private Color pressedBackground = claudeOrange; // Gray for pressed effect
    private int ARC = 10; // Corner radius

    public RoundButton(String text) {
        super(text);

        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        setBackground(backgroundColor);
        setForeground(foregroundColor);
//        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Adjust padding

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
                setForeground(hoverForegroundColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
                setForeground(foregroundColor);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedBackground);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (getModel().isRollover()) {
                    setBackground(hoverBackgroundColor);
                } else {
                    setBackground(backgroundColor);
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g); // Call the superclass method first

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set color based on the current background color
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);

        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC);

        // Set the font and calculate the position of the text
        FontMetrics metrics = g2.getFontMetrics(getFont());
        int textWidth = metrics.stringWidth(getText());
        int textHeight = metrics.getHeight();
        int textX = (getWidth() - textWidth) / 2;
        int textY = (getHeight() - textHeight + 1) / 2 + metrics.getAscent();

        g2.setFont(getFont());
        g2.setColor(getForeground());
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        revalidate(); // Ensure layout manager takes the new size into account
        repaint(); // Repaint the button with the new size
    }

    public void setHoverColor(Color backgroundColor, Color hoverBackgroundColor, Color foregroundColor, Color hoverForegroundColor) {
        this.backgroundColor = backgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.foregroundColor = foregroundColor;
        this.hoverForegroundColor = hoverForegroundColor;
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        this.revalidate();
        this.repaint();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        this.revalidate();
        this.repaint();
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        setForeground(foregroundColor);
        this.revalidate();
        this.repaint();
    }

    public  void setPressedColor(Color pressedBackground) {
        this.pressedBackground = pressedBackground;
        this.revalidate();
        this.repaint();
    }

}
