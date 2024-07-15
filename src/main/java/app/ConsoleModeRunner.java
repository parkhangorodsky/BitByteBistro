package app;

import frameworks.data_access.CSVDataAccessObject;
import frameworks.data_access.DataAccessInterface;
import use_cases.log_in.interface_adapter.controller.LoginController;
import use_cases.sign_up.interface_adapter.controller.SignUpController;
import use_cases.log_in.interface_adapter.presenter.LoginPresenter;
import use_cases.sign_up.interface_adapter.presenter.SignUpPresenter;
import use_cases.sign_up.interface_adapter.view_model.SignUpViewModel;
import use_cases.log_in.use_case.interactor.LoginInteractor;
import use_cases.sign_up.use_case.interactor.SignUpInteractor;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleModeRunner {

    public void run(){

        DataAccessInterface csvDAO = new CSVDataAccessObject("src/main/resources/user/users.csv");

        SignUpViewModel signUpViewModel = new SignUpViewModel();
        SignUpPresenter signUpPresenter = new SignUpPresenter(signUpViewModel);
        SignUpInteractor signUpInteractor = new SignUpInteractor(signUpPresenter, csvDAO);
        SignUpController signUpController = new SignUpController(signUpInteractor);

        LoginPresenter loginPresenter = new LoginPresenter();
        LoginInteractor loginInteractor = new LoginInteractor(loginPresenter, csvDAO);
        LoginController loginController = new LoginController(loginInteractor);

        // Console input for testing
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 'signup' to sign up or 'login' to log in:");
        String action = scanner.nextLine();

        if (action.equalsIgnoreCase("signup")) {
            System.out.print("Enter User ID: ");
            String userID = scanner.nextLine();

            System.out.print("Enter User Email: ");
            String userEmail = scanner.nextLine();

            System.out.print("Enter User Password: ");
            String userPassword = scanner.nextLine();

            signUpController.signUp(userID, userEmail, userPassword);

            if (signUpViewModel.getSuccessMessage() != null) {
                System.out.println(signUpViewModel.getSuccessMessage());
            } else if (signUpViewModel.getErrorMessage() != null) {
                System.out.println(signUpViewModel.getErrorMessage());
            } else {
                System.out.println("An unexpected error occurred.");
            }
        } else if (action.equalsIgnoreCase("login")) {
            System.out.print("Enter User Email: ");
            String userEmail = scanner.nextLine();

            System.out.print("Enter User Password: ");
            String userPassword = scanner.nextLine();

            // Attempt to log in
            loginController.login(userEmail, userPassword);

            if (loginPresenter.getSuccessMessage() != null) {
                System.out.println(loginPresenter.getSuccessMessage());
            } else if (loginPresenter.getErrorMessage() != null) {
                System.out.println(loginPresenter.getErrorMessage());
            } else {
                System.out.println("An unexpected error occurred.");
            }
        }

        scanner.close();
    }
}
