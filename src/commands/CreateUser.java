package src.commands;

import src.interfaces.ICommand;
import src.Database;
import src.User;

public class CreateUser implements ICommand {

    private Database database;

    public CreateUser(Database database) {
        this.database = database;
    }

    @Override
    public Object execute(Object arg) {
        User user = (User) arg;
        database.addUser(user);
        System.out.println("User created: " + user.getName());
        return user;
    }

}
