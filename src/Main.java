package src;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        Invoker invoker = new Invoker(database);

        invoker.service("create", new User(1, "Alice", "alice@example.com"));
        invoker.service("create", new User(2, "Bob", "bob@example.com"));
        invoker.service("delete", 1);

        System.out.println("Remaining users: " + database.getUsers().size());
    }
}
