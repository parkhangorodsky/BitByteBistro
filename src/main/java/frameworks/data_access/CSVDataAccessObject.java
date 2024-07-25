package frameworks.data_access;

import entity.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CSVDataAccessObject implements DataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();
    private User loggedInUser;

    public CSVDataAccessObject(String csvPath) {
        csvFile = new File(csvPath);
        headers.put("user_email", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creation_time", 3);

        // Create the file and directory if they don't exist
        try {
            if (csvFile.getParentFile() != null) {
                csvFile.getParentFile().mkdirs(); // Create parent directories if necessary
            }
            if (csvFile.createNewFile()) {
                save(); // Write the header if the file is newly created
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize CSV file", e);
        }

        if (csvFile.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                assert header.equals("user_email,username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String userEmail = col[headers.get("user_email")];
                    String username = col[headers.get("username")];
                    String password = col[headers.get("password")];
                    String creationTimeText = col[headers.get("creation_time")];
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = new User(userEmail, username, password, ldt);
                    accounts.put(userEmail, user);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addUser(User user) {
        accounts.put(user.getUserEmail(), user);
        this.save();
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = "%s,%s,%s,%s".formatted(
                        user.getUserEmail(), user.getUserName(), user.getUserPassword(), user.getCreatedAt());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByEmail(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public User getUserByEmail(String identifier) {
        return accounts.get(identifier);
    }

    @Override
    public boolean authenticate(String userEmail, String userPassword) {
        User user = getUserByEmail(userEmail);
        if (user != null && user.getUserPassword().equals(userPassword)) {
            setLoggedInUser(user);
            return true;
        }
        return false;
    }

    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    @Override
    public void logout(User user) {
        if (loggedInUser != null && loggedInUser.equals(user)) {
            loggedInUser = null;
        }
    }
}
