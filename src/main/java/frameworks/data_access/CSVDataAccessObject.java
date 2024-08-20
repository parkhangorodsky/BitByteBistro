package frameworks.data_access;

import entity.Fridge;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A data access object that handles user data storage and retrieval in a CSV file.
 * This implementation manages user accounts, including their creation, updating, and deletion,
 * using a CSV file as the data source. It also handles file operations such as reading and writing
 * the CSV file to persist user data.
 */
public class CSVDataAccessObject implements UserDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();

    /**
     * Constructs a {@code CSVDataAccessObject} with the specified CSV file path.
     * Initializes the CSV file by creating it if it doesn't exist and reading its content.
     *
     * @param csvPath The path to the CSV file used for storing user data.
     */
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

    /**
     * Saves the current state of the in-memory user map to the CSV file.
     * This method writes all user data, including headers and user details, to the CSV file.
     */
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
    public void addUser(User user) {
        accounts.put(user.getUserEmail(), user);
        this.save();
    }

    @Override
    public void deleteUser(User user) {}


    @Override
    public boolean existsByEmail(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public User getUserByEmail(String identifier) {
        return accounts.get(identifier);
    }

    @Override
    public void addRecipe(User user, Recipe recipe) {}

    @Override
    public void updateRecentlyViewedRecipes(User user) {}

    @Override
    public void addShoppingList(User user, ShoppingList shoppingList) {}

    @Override
    public void addRecipeToShoppingList(User user, ShoppingList shoppingList, Recipe recipe) {}

    @Override
    public void updateFridge(User user, Fridge fridge) {}

    @Override
    public void updateUser(User user) {}

    @Override
    public void updateUserPreference(User user, String fieldName, Object value) {}

}
