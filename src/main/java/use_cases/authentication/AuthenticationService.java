package use_cases.authentication;

import entity.User;

public interface AuthenticationService {
    User getLoggedInUser();
}
