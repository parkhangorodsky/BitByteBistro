package use_cases.sign_up.gui.view;

import use_cases._common.gui_common.abstractions.View;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for handling user input related to the sign-up process.
 * This class takes user email and password input and passes it to the SignUpController.
 */
public class SignUpView extends View implements ActionListener, PropertyChangeListener {
    private JTextField emailField;
    private JTextField userIDField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JLabel errorMessageLabel;
    private SignUpController signUpController;
    private SignUpViewModel signUpViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new SignUpView with the specified controller and view model.
     *
     * @param signUpController The controller to handle the sign-up process.
     * @param signUpViewModel  The view model to update based on the sign-up result.
     * @param viewManagerModel The view manager model that is responsible for changing views
     */
    public SignUpView(SignUpController signUpController, SignUpViewModel signUpViewModel, ViewManagerModel viewManagerModel) {
        this.signUpController = signUpController;
        this.signUpViewModel = signUpViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signUpViewModel.addPropertyChangeListener(this);
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // User ID Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("User ID:"), gbc);

        // User ID Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userIDField = new JTextField(20);
        add(userIDField, gbc);

        // Email Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);

        // Email Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Password:"), gbc);

        // Password Field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        // Sign Up Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this);
        add(signUpButton, gbc);

        // Error Message Label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(Color.RED);
        add(errorMessageLabel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userID = userIDField.getText();
        String email = emailField.getText();
        char[] password = passwordField.getPassword();
        signUpController.signUp(userID, email, new String(password));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("errorMessage".equals(evt.getPropertyName())) {
            displayErrorMessage((String) evt.getNewValue());
        } else if ("successMessage".equals(evt.getPropertyName())) {
            displaySuccessMessage((String) evt.getNewValue());
        }
    }

    public void displayErrorMessage(String message) {
        errorMessageLabel.setText(message);
    }

    public void displaySuccessMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageLabel.setForeground(Color.GREEN);
    }

    @Override
    public String getViewName() {
        return "SignUpView";
    }
}
