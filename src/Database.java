package src;

import java.util.HashMap;

public class Database {
    private HashMap<Integer, src.User> users;

    private static Database instance;

    private Database() {
        users = new HashMap<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addUser(src.User user) {
        users.put(user.getId(), user);
    }

    public void deleteUser(Integer userId) {
        users.remove(userId);
    }

    public HashMap<Integer, src.User> getUsers() {
        return users;
    }
}
