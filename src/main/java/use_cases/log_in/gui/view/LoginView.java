package use_cases.log_in.gui.view;

import use_cases._common.gui_common.abstractions.View;
import use_cases.log_in.interface_adapter.controller.LoginController;
import use_cases.log_in.interface_adapter.view_model.LoginViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import frameworks.gui.SwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * LoginView is responsible for displaying the login interface and handling user interactions.
 * It extends the View class and implements ActionListener and PropertyChangeListener to respond to UI actions and property changes.
 */
public class LoginView extends View implements ActionListener, PropertyChangeListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorMessageLabel;
    private JLabel switchToSignUpLabel;
    private LoginController loginController;
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private SwingGUI swingGUI; // Add reference to SwingGUI
    /**
     * Constructs a new LoginView with the specified controller, view model, view manager model, and GUI.
     *
     * @param loginController The controller to handle login actions.
     * @param loginViewModel The view model to manage login state.
     * @param viewManagerModel The view manager model to handle view changes.
     * @param swingGUI The GUI to initialize other views.
     */
    public LoginView(LoginController loginController, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, SwingGUI swingGUI) {
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.swingGUI = swingGUI; // Initialize SwingGUI reference
        this.loginViewModel.addPropertyChangeListener(this);
        setupUI();
    }

    /**
     * Sets up the user interface for the login view.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1000, 750)); // Set the size to match SwingGUI frame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Email Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Email:"), gbc);

        // Email Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Password:"), gbc);

        // Password Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(loginButton, gbc);

        // Error Message Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(Color.RED);
        add(errorMessageLabel, gbc);

        // Switch to Sign Up Label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        switchToSignUpLabel = new JLabel("Don't have an account? Sign Up");
        switchToSignUpLabel.setForeground(Color.BLUE);
        switchToSignUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        switchToSignUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewManagerModel.setActiveView("SignUpView");
                viewManagerModel.firePropertyChanged();
            }
        });
        add(switchToSignUpLabel, gbc);
    }

    /**
     * Handles the action events for the login button.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        char[] password = passwordField.getPassword();
        loginController.login(email, new String(password));
    }

    /**
     * Handles property change events from the view model.
     *
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("errorMessage".equals(evt.getPropertyName())) {
            displayErrorMessage((String) evt.getNewValue());
        } else if ("loggedInUser".equals(evt.getPropertyName())) {
            // Initialize other views when the user logs in
            swingGUI.initializeOtherViews();

            // Navigate to search recipe view on successful login
            viewManagerModel.setActiveView("search recipe");
            viewManagerModel.firePropertyChanged();
        }
    }

    /**
     * Displays an error message on the UI.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        errorMessageLabel.setText(message);
    }

    /**
     * Returns the name of this view.
     *
     * @return The name of this view.
     */
    @Override
    public String getViewName() {
        return "LoginView";
    }
}
