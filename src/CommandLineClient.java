import java.util.Scanner;

import src.Database;
import src.User;
import commands.GetUser;
import commands.GetAllUsers;
import commands.DeleteAllUsers;

public class CommandLineClient {

    public static void main(String[] args) {
        Database db = Database.getInstance();
        Invoker invoker = null;
        try {
            invoker = new Invoker(db);
        } catch (Throwable t) {
            // If Invoker cannot be constructed due to package mismatches in other files,
            // we still continue and call commands directly.
            System.out.println("Warning: Invoker unavailable, will call commands directly if needed. Reason: " + t.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Command-line interface ready. Available commands: create, delete, get, all, delete_all, exit");
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+", 2);
            String cmd = parts[0].toLowerCase();

            try {
                if (cmd.equals("create")) {
                    if (parts.length < 2) { System.out.println("Usage: create <id> <name> <email>"); continue; }
                    String[] params = parts[1].split("\\s+", 3);
                    if (params.length < 3) { System.out.println("Usage: create <id> <name> <email>"); continue; }
                    Integer id = Integer.parseInt(params[0]);
                    String name = params[1];
                    String email = params[2];
                    User user = new User(id, name, email);
                    boolean handled = false;
                    if (invoker != null) {
                        try { invoker.service("create", user); handled = true; } catch (Throwable t) { System.out.println("Invoker failed for create, falling back: " + t.getMessage()); }
                    }
                    if (!handled) { db.addUser(user); System.out.println("User created (direct): " + name); }
                    continue;
                }

                if (cmd.equals("delete")) {
                    if (parts.length < 2) { System.out.println("Usage: delete <id> or delete all"); continue; }
                    String arg = parts[1].trim();
                    if (arg.equalsIgnoreCase("all")) {
                        boolean handled = false;
                        if (invoker != null) {
                            try { invoker.service("delete_all", null); handled = true; } catch (Throwable t) { }
                        }
                        if (!handled) new DeleteAllUsers(db).execute(null);
                        continue;
                    }
                    Integer id = Integer.parseInt(arg);
                    boolean handled = false;
                    if (invoker != null) {
                        try { invoker.service("delete", id); handled = true; } catch (Throwable t) { System.out.println("Invoker failed for delete, falling back: " + t.getMessage()); }
                    }
                    if (!handled) { db.deleteUser(id); System.out.println("User deleted (direct): id=" + id); }
                    continue;
                }

                if (cmd.equals("get")) {
                    if (parts.length < 2) { System.out.println("Usage: get <id> or get<id>"); continue; }
                    String arg = parts[1].trim();
                    Integer id = null;
                    try { id = Integer.parseInt(arg); } catch (NumberFormatException nfe) {
                        String digits = arg.replaceAll("\\D+", "");
                        if (!digits.isEmpty()) id = Integer.parseInt(digits);
                        else { System.out.println("Invalid id: " + arg); continue; }
                    }
                    boolean handled = false;
                    if (invoker != null) {
                        try { invoker.service("get", id); handled = true; } catch (Throwable t) { }
                    }
                    if (!handled) new GetUser(db).execute(id);
                    continue;
                }

                if (cmd.equals("all")) {
                    boolean handled = false;
                    if (invoker != null) {
                        try { invoker.service("all", null); handled = true; } catch (Throwable t) { }
                    }
                    if (!handled) new GetAllUsers(db).execute(null);
                    continue;
                }

                if (cmd.equals("delete_all") || cmd.equals("deleteall") || cmd.equals("delete-all") || cmd.equals("deleteallusers")) {
                    boolean handled = false;
                    if (invoker != null) {
                        try { invoker.service("delete_all", null); handled = true; } catch (Throwable t) { }
                    }
                    if (!handled) new DeleteAllUsers(db).execute(null);
                    continue;
                }

                if (cmd.equals("exit") || cmd.equals("quit")) {
                    System.out.println("Exiting."); scanner.close(); return;
                }

                System.out.println("Unknown or unsupported command: " + cmd);
                System.out.println("Supported: create, delete <id>, get <id>, all, delete_all, exit");

            } catch (NumberFormatException nfe) {
                System.out.println("Number format error: " + nfe.getMessage());
            } catch (Exception e) {
                System.out.println("Error handling command: " + e.getMessage());
            }
        }

        scanner.close();
    }
}

