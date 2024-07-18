package frameworks.data_access;

import entity.User;

public interface UserRepository {
    void addUser(User user);
    User getUserByEmail(String email);
}
