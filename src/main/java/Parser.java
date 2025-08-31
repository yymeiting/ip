import java.awt.desktop.SystemEventListener;

public class Parser {
    static String TAB = "     ";

    public static Command parse(String input) throws CuteOwlException {
        String[] tokens = input.trim().split(" ", 2);
        String commandType = tokens[0].toLowerCase();
        String arguments = tokens.length > 1 ? tokens[1].trim() : "";

        switch (commandType) {
            case "bye":
                return new Command("bye");

            case "list":
                return new Command("list");

            case "todo":
                if (arguments.isEmpty()) {
                    throw new CuteOwlException(TAB + "OOPS!!! The description of a todo cannot be empty.");
                }

                return new Command("todo", arguments);

            case "deadline":
                if (arguments.isEmpty()) {
                    throw new CuteOwlException(TAB + "OOPS!!! The description of deadline cannot be empty.");
                }

                String[] args = arguments.split("/by");
                return new Command("deadline", args);

            case "event":
                if (arguments.isEmpty()) {
                    throw new CuteOwlException(TAB + "OOPS!!! The description of a event cannot be empty.");
                }

                String[] eventArgs = arguments.split("/from|/to");
                return new Command("event", eventArgs);

            case "delete":
                if (arguments.isEmpty()) {
                    throw new CuteOwlException(TAB + "OOPS!!! Please enter the index of the task you wish to delete.");
                }

                return new Command("delete", arguments);

            case "mark":
                if (arguments.isEmpty()) {
                    throw new CuteOwlException(TAB + "OOPS!!! Please enter the index of the task you wish to mark.");
                }

                return new Command("mark", arguments);

            case "unmark":
                if (arguments.isEmpty()) {
                    throw new CuteOwlException(TAB + "OOPS!!! Please enter the index of the task you wish to unmark.");
                }

                return new Command("unmark", arguments);

            default:
                throw new CuteOwlException(TAB + "OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseIndex(String arg) throws CuteOwlException {
        try {
            return Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new CuteOwlException("OOPS!!! Please provide a valid task number.");
        }
    }
}