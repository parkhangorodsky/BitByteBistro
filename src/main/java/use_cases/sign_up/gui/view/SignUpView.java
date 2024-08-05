package use_cases.sign_up.gui.view;

import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases._common.gui_common.view_components.round_component.RoundTextField;
import use_cases._common.gui_common.view_components.round_component.RoundPasswordField;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for handling user input related to the sign-up process.
 * This class takes user email and password input and passes it to the SignUpController.
 */
public class SignUpView extends View implements ActionListener, PropertyChangeListener, ThemeColoredObject {
    private RoundTextField emailField;
    private RoundTextField userIDField;
    private RoundPasswordField passwordField;
    private RoundButton signUpButton;
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
        setPreferredSize(new Dimension(1000, 750)); // Set the size to match SwingGUI frame

        // Background Panel
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adding padding
        backgroundPanel.setBackground(claudeWhite); // Set background color
        setLayout(new BorderLayout()); // Use BorderLayout for the main layout
        add(backgroundPanel, BorderLayout.CENTER); // Add the background panel to the center

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Header Label
        JLabel headerLabel = new JLabel("New to BitByteBistro? Join here!");
        headerLabel.setFont(new Font(defaultFont, Font.BOLD, 30));
        headerLabel.setForeground(claudeBlack); // Match button color for consistency
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10)); // Padding around header
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(headerLabel, gbc);

        // User ID Label
        JLabel userIDLabel = new JLabel("User ID:");
        userIDLabel.setFont(new Font(defaultFont, Font.BOLD, 14));
        userIDLabel.setForeground(claudeBlack);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        backgroundPanel.add(userIDLabel, gbc);

        // User ID Field
        userIDField = new RoundTextField();
        userIDField.setPreferredSize(new Dimension(100, 30));
        userIDField.setMargin(new Insets(5, 7, 3, 7));
        userIDField.setFont(new Font(defaultFont, Font.PLAIN, 14));
        userIDField.setColor(white, claudeWhiteEmph);
        userIDField.setForeground(claudeBlack);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(userIDField, gbc);

        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font(defaultFont, Font.BOLD, 14));
        emailLabel.setForeground(claudeBlack);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        backgroundPanel.add(emailLabel, gbc);

        // Email Field
        emailField = new RoundTextField();
        emailField.setPreferredSize(new Dimension(100, 30));
        emailField.setMargin(new Insets(5, 7, 3, 7));
        emailField.setFont(new Font(defaultFont, Font.PLAIN, 14));
        emailField.setColor(white, claudeWhiteEmph);
        emailField.setForeground(claudeBlack);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(emailField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font(defaultFont, Font.BOLD, 14));
        passwordLabel.setForeground(claudeBlack);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        backgroundPanel.add(passwordLabel, gbc);

        // Password Field
        passwordField = new RoundPasswordField(20);
        passwordField.setPreferredSize(new Dimension(100, 30));
        passwordField.setFont(new Font(defaultFont, Font.PLAIN, 14));
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(claudeWhiteEmph));
        passwordField.setForeground(claudeBlack);
        passwordField.setMargin(new Insets(5, 7, 3, 7));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(passwordField, gbc);

        // Sign Up Button
        signUpButton = new RoundButton("Sign Up");
        signUpButton.setFont(new Font(defaultFont, Font.BOLD, 15));
        signUpButton.setForeground(claudeBlack);
        signUpButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside button
        signUpButton.addActionListener(this);
        signUpButton.setHoverColor(claudeWhite, claudeWhite, claudeBlack, claudeBlackEmph);
        signUpButton.setBorderColor(claudeWhite);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        backgroundPanel.add(signUpButton, gbc);

        // Error Message Label
        errorMessageLabel = new JLabel();
        errorMessageLabel.setFont(new Font(defaultFont, Font.PLAIN, 12));
        errorMessageLabel.setForeground(errorRed);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(errorMessageLabel, gbc);

        // Login Label
        JLabel loginLabel = new JLabel("Already have an account? Login here");
        loginLabel.setFont(new Font(defaultFont, Font.ITALIC, 12));
        loginLabel.setForeground(claudeBlackEmph);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewManagerModel.setActiveView("LoginView");
                viewManagerModel.firePropertyChanged();
                errorMessageLabel.setText("");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(loginLabel, gbc);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Clear the text fields
        String userID = userIDField.getText();
        String email = emailField.getText();
        char[] password = passwordField.getPassword();
        userIDField.setText("");
        emailField.setText("");
        passwordField.setText("");
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
        errorMessageLabel.setForeground(errorRed);
    }

    public void displaySuccessMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageLabel.setForeground(successGreen);
    }

    @Override
    public String getViewName() {
        return "SignUpView";
    }
}
