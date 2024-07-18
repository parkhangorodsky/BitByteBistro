package frameworks.data_access;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getUserEmail(), user);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.get(email);
    }
}
