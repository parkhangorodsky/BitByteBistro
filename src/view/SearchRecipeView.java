package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;

public class SearchRecipeView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewname = "search recipe";

    public SearchRecipeView() {
        JLabel title = new JLabel("Search Recipe");
//        title.setAlignmentX(CENTER_ALIGNMENT);
        JTextField recipeName = new JTextField(30);
        JButton searchButton = new JButton("Search");

        this.add(title);
        this.add(recipeName);
        this.add(searchButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
