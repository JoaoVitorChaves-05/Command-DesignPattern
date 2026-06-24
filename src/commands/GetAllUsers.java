package commands;

import interfaces.ICommand;
import src.Database;
import src.User;

import java.util.HashMap;

public class GetAllUsers implements ICommand {

    private Database database;

    public GetAllUsers(Database database) {
        this.database = database;
    }

    @Override
    public Object execute(Object arg) {
        HashMap<Integer, User> users = database.getUsers();
        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return users;
        }
        System.out.println("All users:");
        for (User u : users.values()) {
            System.out.println("  id=" + u.getId() + ", name=" + u.getName() + ", email=" + u.getEmail());
        }
        return users;
    }
}

