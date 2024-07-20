package frameworks.data_access;

import entity.User;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of DataAccessInterface.
 * This implementation uses an in-memory storage (HashMap) to store and retrieve user data.
 */
public class SomeDataAccessImplementation implements DataAccessInterface {
    private final Map<String, User> userDatabase = new HashMap<>();

    @Override
    public boolean existsByEmail(String email) {
        return userDatabase.containsKey(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDatabase.get(email);
    }

    @Override
    public void addUser(User user) {
        userDatabase.put(user.getUserEmail(), user);
    }
}
