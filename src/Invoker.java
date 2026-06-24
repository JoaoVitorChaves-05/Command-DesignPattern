import interfaces.ICommand;
import commands.CreateUser;
import commands.DeleteUser;
import java.util.HashMap;

public class Invoker {
    private Database database;
    private HashMap<String, ICommand> commands = new HashMap<>();

    public Invoker(Database database) {
        this.database = database;
        initCommands();
    }

    private void initCommands() {
        commands.put("create", new CreateUser(database));
        commands.put("delete", new DeleteUser(database));
    }

    public Object service(String cmd, Object data) {
        ICommand command = commands.get(cmd);
        return command.execute(data);
    }
}

