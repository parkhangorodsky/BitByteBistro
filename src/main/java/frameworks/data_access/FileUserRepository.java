package frameworks.data_access;

import entity.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUserRepository implements UserRepository {
    private static final String FILE_PATH = "users.txt";
    private final Map<String, User> users;

    public FileUserRepository() {
        users = new HashMap<>();
        loadUsers();
    }

    @Override
    public void addUser(User user) {
        users.put(user.getUserEmail(), user);
        saveUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        return users.get(email);
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String userID = parts[0];
                    String userEmail = parts[1];
                    String userPassword = parts[2];
                    users.put(userEmail, new User(userID, userEmail, userPassword));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users.values()) {
                writer.write(user.getUserID() + "," + user.getUserEmail() + "," + user.getUserPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}
