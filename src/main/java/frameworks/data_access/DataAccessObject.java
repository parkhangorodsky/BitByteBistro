package frameworks.data_access;

import entity.User;

public interface DataAccessObject {
    boolean existsByEmail(String identifier);
    void save(User user);
}
