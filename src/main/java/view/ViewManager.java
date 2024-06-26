package view;

import interface_adapter.view_model.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private JPanel views;
    private CardLayout cardLayout;
    private ViewManagerModel viewManagerModel;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    public void addView(View view) {
        this.views.add((JPanel) view, view.getViewName());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view change")) {
            String newViewName = (String) evt.getNewValue();
            cardLayout.show(views, newViewName);
        }
    }
}
