package frameworks.data_access;

import entity.User;

public interface DataAccessInterface {
    boolean existsByEmail(String email);

    void addUser(User user);

    User getUserByEmail(String email);

    User getLoggedInUser();

    void setLoggedInUser(User user);
}
