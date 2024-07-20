package frameworks.data_access;

import entity.User;

public interface DataAccessInterface {
    boolean existsByEmail(String identifier);
    User getUserByEmail(String identifier);
    void addUser(User user);
}
