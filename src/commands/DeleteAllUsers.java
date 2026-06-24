package commands;

import interfaces.ICommand;
import src.Database;
import src.User;

import java.util.ArrayList;
import java.util.List;

public class DeleteAllUsers implements ICommand {

    private Database database;

    public DeleteAllUsers(Database database) {
        this.database = database;
    }

    @Override
    public Object execute(Object arg) {
        java.util.HashMap<Integer, User> users = database.getUsers();
        if (users == null || users.isEmpty()) {
            System.out.println("No users to delete.");
            return 0;
        }

        List<Integer> ids = new ArrayList<>(users.keySet());
        for (Integer id : ids) {
            database.deleteUser(id);
        }

        System.out.println("Deleted all users: " + ids.size());
        return ids.size();
    }
}

