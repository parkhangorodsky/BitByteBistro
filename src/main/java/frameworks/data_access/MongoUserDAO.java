package frameworks.data_access;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.User;

import java.util.logging.Filter;

public class MongoUserDAO implements UserDataAccessInterface{

    private final MongoCollection<User> userCollection;

    public MongoUserDAO(MongoDatabase database) {
        this.userCollection = database.getCollection("users", User.class);
    }

    @Override
    public void addUser(User user) {
        userCollection.insertOne(user);
    }

    @Override
    public void updateUser(User user) {
    }

    @Override
    public void deleteUser(User user) {
        userCollection.deleteOne(Filters.eq("userEmail", user.getUserEmail()));
    }

    @Override
    public User getUserByEmail(String email) {
        return userCollection.find(Filters.eq("userEmail", email)).first();
    }

    @Override
    public boolean existsByEmail(String email) {
        return getUserByEmail(email) != null;
    }

}
