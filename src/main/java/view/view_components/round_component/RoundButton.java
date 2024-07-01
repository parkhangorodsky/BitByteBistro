package view.view_components.round_component;

import view.view_components.ViewComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundButton extends JButton implements ViewComponent {


    private static final Color BORDER_COLOR = claudeWhiteEmph;
    ;
    private static final Color BACKGROUND_COLOR = claudeWhite;
    ;
    private static final Color HOVER_COLOR = claudeWhiteEmph;
    ; // Light gray for hover effect
    private static final Color PRESSED_COLOR = claudeOrange; // Gray for pressed effect
    private static final int ARC = 10; // Corner radius

    public RoundButton(String text) {
        super(text);

        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        setBackground(BACKGROUND_COLOR);
        setForeground(claudeBlack);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Adjust padding

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_COLOR);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(BACKGROUND_COLOR);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(PRESSED_COLOR);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (getModel().isRollover()) {
                    setBackground(HOVER_COLOR);
                } else {
                    setBackground(BACKGROUND_COLOR);
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set color based on the current background color
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);

        g2.setColor(BORDER_COLOR);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC);

        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No need to paint border separately, it's handled in paintComponent
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(120, 40); // Adjust preferred size as needed
    }


}
