package view;

import interface_adapter.view_model.SearchRecipeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;

public class SearchRecipeView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewname = "search recipe";
    private final String fieldLabel = "Search Recipe";
    private final int fieldwWidth = 30;
    private final String buttonLabel = "Search";

    public JTextField recipeName;
    public JButton searchButton;

    public SearchRecipeView() {

        // Initialize input & output panel
        JPanel inputPanel = new JPanel();
        JPanel outputPanel = new JPanel();

        // Components
        JLabel title = new JLabel(fieldLabel);
        title.setAlignmentX(CENTER_ALIGNMENT);

        this.recipeName = new JTextField(fieldwWidth);

        searchButton = new JButton(buttonLabel);
        searchButton.addActionListener(this);

        // Pack input & output panel
        inputPanel.add(title);
        inputPanel.add(recipeName);
        inputPanel.add(searchButton);

        // Set Layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(inputPanel);
        this.add(outputPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(searchButton)) {
            String recipeName = this.recipeName.getText();
            System.out.println(recipeName);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
