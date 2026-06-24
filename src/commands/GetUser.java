package commands;

import interfaces.ICommand;
import src.Database;
import src.User;

public class GetUser implements ICommand {

    private Database database;

    public GetUser(Database database) {
        this.database = database;
    }

    @Override
    public Object execute(Object arg) {
        Integer userId = null;

        if (arg == null) {
            System.out.println("GetUser: no id provided");
            return null;
        }

        if (arg instanceof Integer) {
            userId = (Integer) arg;
        } else if (arg instanceof String) {
            String s = ((String) arg).trim();
            if (s.toLowerCase().startsWith("get")) {
                String suffix = s.substring(3).trim();
                try {
                    userId = Integer.parseInt(suffix);
                } catch (NumberFormatException e) {
                    System.out.println("GetUser: invalid id in 'get<id>' string: " + suffix);
                    return null;
                }
            } else {
                try {
                    userId = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    System.out.println("GetUser: argument is not an integer or 'get<id>' pattern: " + s);
                    return null;
                }
            }
        } else {
            System.out.println("GetUser: unsupported argument type: " + arg.getClass().getName());
            return null;
        }

        // Database currently exposes getUsers() returning a map
        User user = database.getUsers().get(userId);
        if (user == null) {
            System.out.println("User not found: id=" + userId);
            return null;
        }

        System.out.println("User found: id=" + user.getId() + ", name=" + user.getName() + ", email=" + user.getEmail());
        return user;
    }
}

