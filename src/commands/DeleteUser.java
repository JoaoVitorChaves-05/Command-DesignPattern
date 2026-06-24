package commands;

import src.interfaces.ICommand;
import src.Database;

public class DeleteUser implements ICommand {

    private Database database;

    public DeleteUser(Database database) {
        this.database = database;
    }

    @Override
    public Object execute(Object arg) {
        Integer userId = (Integer) arg;
        database.deleteUser(userId);
        System.out.println("User deleted: id=" + userId);
        return userId;
    }

}

