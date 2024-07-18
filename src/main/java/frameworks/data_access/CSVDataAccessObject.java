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

    public CSVDataAccessObject(String csvPath) {

        csvFile = new File(csvPath);
        headers.put("user_email", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creation_time", 3);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // TODO clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("user_email,username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String userEmail = String.valueOf(col[headers.get("user_email")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = new User(userEmail, username, password, ldt);
                    accounts.put(username, user);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void addUser(User user) {
        accounts.put(user.getUserEmail(), user);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = "%s,%s,%s,%s".formatted(
                        user.getUserEmail(), user.getUserName(), user.getUserPassword(), user.getCreatedAt());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByEmail(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public User getUserByEmail(String identifier) {
        return accounts.get(identifier);
    }
}
