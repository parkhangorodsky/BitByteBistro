package use_cases._common.gui_common.view_components.round_component;

import use_cases._common.gui_common.abstractions.ThemeColoredObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundImageIcon extends JLabel implements ThemeColoredObject {

    private ImageIcon icon;

    public RoundImageIcon(ImageIcon icon) {
        this.icon = icon;
        setIcon(icon);
        setOpaque(false); // Ensure JLabel is transparent
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(createRoundBorder(5));// Center the image
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Paint the background with rounded shape
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a round rectangle shape
        Shape roundRect = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, width, height);
        graphics.setColor(getBackground()); // Use JLabel's background color
        graphics.fill(roundRect);

        // Draw the image icon in the center
        if (icon != null) {
            int x = (getWidth() - icon.getIconWidth()) / 2;
            int y = (getHeight() - icon.getIconHeight()) / 2;
            icon.paintIcon(this, graphics, x, y);
        }

        graphics.dispose();
    }

    private Border createRoundBorder(int radius) {
        return new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create a round rectangle
                RoundRectangle2D roundRect = new RoundRectangle2D.Float(x, y, width - 1, height - 1, radius, radius);
                g2d.setColor(claudeWhiteEmph); // Border color
                g2d.draw(roundRect);

                g2d.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(radius, radius, radius, radius); // Adjust as needed
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageIcon icon = new ImageIcon("path/to/your/image.png"); // Replace with your image path
            JFrame frame = new JFrame("Round Image Icon Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);

            RoundImageIcon roundIcon = new RoundImageIcon(icon);
            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(roundIcon, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }


}
