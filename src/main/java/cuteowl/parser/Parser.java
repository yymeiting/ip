package cuteowl.parser;

import cuteowl.command.*;
import cuteowl.exception.CuteOwlException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    static String TAB = "     ";
    static DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm") // optional: allow leading zeros
    };

    public static Command parse(String input) throws CuteOwlException {
        String[] tokens = input.trim().split(" ", 2);
        String commandType = tokens[0].toLowerCase();
        String commandDescription = tokens.length > 1 ? tokens[1].trim() : "";

        switch (commandType) {
            case "bye":
                return new ByeCommand();

            case "list":
                return new ListCommand();

            case "todo":
                if (commandDescription.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! The description of a todo cannot be empty.");
                    throw new CuteOwlException("OOPS!!! The description of a todo cannot be empty.");
                }

                return new TodoCommand(commandDescription);

            case "deadline":
                if (commandDescription.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! The description of deadline cannot be empty.");
                    throw new CuteOwlException("OOPS!!! The description of deadline cannot be empty.");
                }

                String[] args = commandDescription.split("/by");

                if (args.length < 2) {
                    System.out.println(TAB + "OOPS!!! Please enter your deadline in the format:\n" +
                            TAB + "deadline <description> /by <date>");
                    throw new CuteOwlException("OOPS!!! Please enter your deadline in the format:\n" +
                            TAB + "deadline <description> /by <date>");
                }

                String deadlineDescription = args[0].trim();
                String date = args[1].trim();

                LocalDateTime by = null;
                boolean parsed = false;

                for (DateTimeFormatter formatter : formatters) {
                    try {
                        by = LocalDateTime.parse(date, formatter);
                        parsed = true;
                        break;
                    } catch (DateTimeParseException ignored) {
                    }
                }

                if (!parsed) {
                    System.out.println(TAB + "OOPS!!! The date/time format is invalid.\n" +
                            TAB + "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
                    throw new CuteOwlException("OOPS!!! The date/time format is invalid.\n" +
                            "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
                }

                return new DeadlineCommand(deadlineDescription, by);

            case "event":
                if (commandDescription.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! The description of a event cannot be empty.");
                    throw new CuteOwlException("OOPS!!! The description of a event cannot be empty.");
                }

                String[] eventArgs = commandDescription.split("/from|/to");
                if (eventArgs.length < 3) {
                    System.out.println(TAB + "OOPS!!! Please enter your event in the format:\n" +
                            TAB + "event <description> /from <start> /to <end>");
                    throw new CuteOwlException("OOPS!!! Please enter your event in the format:\n" +
                            "event <description> /from <start> /to <end>");
                }

                String eventDescription = eventArgs[0].trim();
                String fromInput = eventArgs[1].trim();
                String toInput = eventArgs[2].trim();

                LocalDateTime from = null;
                LocalDateTime to = null;
                boolean eventParsed = false;

                for (DateTimeFormatter formatter : formatters) {
                    try {
                        from = LocalDateTime.parse(fromInput, formatter);
                        to = LocalDateTime.parse(toInput, formatter);
                        eventParsed = true;
                        break;
                    } catch (DateTimeParseException ignored) {
                    }
                }

                if (!eventParsed) {
                    System.out.println("OOPS!!! The date/time format is invalid.\n" +
                            "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
                    throw new CuteOwlException(TAB + "OOPS!!! The date/time format is invalid.\n" +
                            TAB + "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
                }
                return new EventCommand(eventDescription, from, to);

            case "delete":
                if (commandDescription.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! Please enter the index of the task you wish to delete.");
                    throw new CuteOwlException("OOPS!!! Please enter the index of the task you wish to delete.");
                }

                int deleteIndex;
                try {
                    deleteIndex = Integer.parseInt(commandDescription);
                } catch (NumberFormatException e) {
                    throw new CuteOwlException("OOPS!!! '" + commandDescription + "' is not a valid number.");
                }

                return new DeleteCommand(deleteIndex);

            case "mark":
                if (commandDescription.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! Please enter the index of the task you wish to mark.");
                    throw new CuteOwlException("OOPS!!! Please enter the index of the task you wish to mark.");
                }

                int markIndex;
                try {
                    markIndex = Integer.parseInt(commandDescription);
                } catch (NumberFormatException e) {
                    throw new CuteOwlException("OOPS!!! '" + commandDescription + "' is not a valid number.");
                }
                return new MarkCommand(markIndex);

            case "unmark":
                if (commandDescription.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! Please enter the index of the task you wish to unmark.");
                    throw new CuteOwlException("OOPS!!! Please enter the index of the task you wish to unmark.");
                }

                int unmarkIndex;
                try {
                    unmarkIndex = Integer.parseInt(commandDescription);
                } catch (NumberFormatException e) {
                    throw new CuteOwlException("OOPS!!! '" + commandDescription + "' is not a valid number.");
                }

                return new UnmarkCommand(unmarkIndex);

            case "find":
                if (commandDescription.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! Please enter the a keyword of the task you wish to find.");
                    throw new CuteOwlException("OOPS!!! Please enter the a keyword of the task you wish to find.");
                }
                return new FindCommand(commandDescription);

            case "note":
                if (commandDescription.isEmpty()) {
                    System.out.println(TAB + "OOPS!!! Please enter the what you wish to note.");
                    throw new CuteOwlException("OOPS!!! Please enter the what you wish to note.");
                }
                return new AddNoteCommand(commandDescription);

            case "notes":
                return new ListNotesCommand();


            default:
                System.out.println(TAB + "OOPS!!! I'm sorry, but I don't know what that means :-(");
                throw new CuteOwlException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}