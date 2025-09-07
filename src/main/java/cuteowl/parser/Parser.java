package cuteowl.parser;

import cuteowl.command.Command;
import cuteowl.exception.CuteOwlException;

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
                    System.out.println(TAB + "OOPS!!! The description of a todo cannot be empty.");
                    throw new CuteOwlException("OOPS!!! The description of a todo cannot be empty.");
                }

                return new Command("todo", arguments);

            case "deadline":
                if (arguments.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! The description of deadline cannot be empty.");
                    throw new CuteOwlException("OOPS!!! The description of deadline cannot be empty.");
                }

                String[] args = arguments.split("/by");
                return new Command("deadline", args);

            case "event":
                if (arguments.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! The description of a event cannot be empty.");
                    throw new CuteOwlException("OOPS!!! The description of a event cannot be empty.");
                }

                String[] eventArgs = arguments.split("/from|/to");
                return new Command("event", eventArgs);

            case "delete":
                if (arguments.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! Please enter the index of the task you wish to delete.");
                    throw new CuteOwlException("OOPS!!! Please enter the index of the task you wish to delete.");
                }

                return new Command("delete", arguments);

            case "mark":
                if (arguments.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! Please enter the index of the task you wish to mark.");
                    throw new CuteOwlException("OOPS!!! Please enter the index of the task you wish to mark.");
                }

                return new Command("mark", arguments);

            case "unmark":
                if (arguments.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! Please enter the index of the task you wish to unmark.");
                    throw new CuteOwlException("OOPS!!! Please enter the index of the task you wish to unmark.");
                }

                return new Command("unmark", arguments);

            case "find":
                if (arguments.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! Please enter the a keyword of the task you wish to find.");
                    throw new CuteOwlException("OOPS!!! Please enter the a keyword of the task you wish to find.");
                }
                return new Command("find", arguments);


            default:
                System.out.println(TAB + "OOPS!!! I'm sorry, but I don't know what that means :-(");
                throw new CuteOwlException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}