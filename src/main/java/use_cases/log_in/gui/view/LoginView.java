package use_cases.log_in.gui.view;

import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPasswordField;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;
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
public class LoginView extends View implements ActionListener, PropertyChangeListener, ThemeColoredObject {
    private RoundTextField emailField;
    private JPasswordField passwordField;
    private RoundButton loginButton;
    private JLabel errorMessageLabel;
    private JLabel switchToSignUpLabel;
    private LoginController loginController;
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new LoginView with the specified controller, view model, and view manager model.
     *
     * @param loginController The controller to handle login actions.
     * @param loginViewModel The view model to manage login state.
     * @param viewManagerModel The view manager model to handle view changes.
     */
    public LoginView(LoginController loginController, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel.addPropertyChangeListener(this);
        setupUI();
    }

    /**
     * Sets up the user interface for the login view.
     */
    private void setupUI() {
        setPreferredSize(new Dimension(1000, 750)); // Set the size to match SwingGUI frame

        // Background Panel
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adding padding
        backgroundPanel.setBackground(new Color(238, 237, 227)); // Set background color
        setLayout(new BorderLayout()); // Use BorderLayout for the main layout
        add(backgroundPanel, BorderLayout.CENTER); // Add the background panel to the center

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Header Label
        JLabel headerLabel = new JLabel("Welcome to BitByteBistro");
        headerLabel.setFont(new Font(defaultFont, Font.BOLD, 30));
        headerLabel.setForeground(claudeBlack); // Match button color for consistency
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10)); // Padding around header
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(headerLabel, gbc);

        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font(defaultFont, Font.BOLD, 14));
        emailLabel.setForeground(claudeBlack);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        backgroundPanel.add(emailLabel, gbc);

        // Email Field
        emailField = new RoundTextField();
        emailField.setPreferredSize(new Dimension(100, 30));
        emailField.setMargin(new Insets(5,7,3,7));
        emailField.setFont(new Font(defaultFont, Font.PLAIN, 14));
        emailField.setColor(white, claudeWhiteEmph);
        emailField.setForeground(claudeBlack);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(emailField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font(defaultFont, Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        backgroundPanel.add(passwordLabel, gbc);

        // Password Field
        passwordField = new RoundPasswordField(20);
        passwordField.setFont(new Font(defaultFont, Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(100, 30));
        passwordField.setBackground(white);
        passwordField.setBorder(BorderFactory.createLineBorder(claudeWhiteEmph));
        passwordField.setForeground(claudeBlack);
        passwordField.setMargin(new Insets(5,7,3,7));

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(passwordField, gbc);




        // Login Button
        loginButton = new RoundButton("Login");
        loginButton.setFont(new Font(defaultFont, Font.BOLD, 15));
        loginButton.setBackground(claudeWhite); // Set a nice color for the button
        loginButton.setForeground(claudeBlack);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside button
        loginButton.addActionListener(this);
        loginButton.setHoverColor(claudeWhite, claudeWhite, claudeBlack, claudeBlackEmph);
        loginButton.setBorderColor(claudeWhite);
        loginButton.setPressedColor(claudewhiteBright);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        backgroundPanel.add(loginButton, gbc);

        // Error Message Label
        errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(errorRed);
        errorMessageLabel.setFont(new Font(defaultFont, Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(errorMessageLabel, gbc);

        // Switch to Sign Up Label
        switchToSignUpLabel = new JLabel("Don't have an account? Sign Up");
        switchToSignUpLabel.setFont(new Font(defaultFont, Font.ITALIC, 12));
        switchToSignUpLabel.setForeground(claudeBlackEmph);
        switchToSignUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        switchToSignUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewManagerModel.setActiveView("SignUpView");
                viewManagerModel.firePropertyChanged();
                errorMessageLabel.setText("");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(switchToSignUpLabel, gbc);
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
        }
    }

    /**
     * Displays an error message on the UI.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageLabel.revalidate();
        errorMessageLabel.repaint();
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
