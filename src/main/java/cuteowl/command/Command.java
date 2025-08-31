package cuteowl.command;

import cuteowl.storage.Storage;
import cuteowl.ui.Ui;
import cuteowl.exception.CuteOwlException;
import cuteowl.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Command {
    static String TAB = "     ";
    private final String type;
    private final String[] args;
    private final String description;

    public Command(String type, String[] args) {
        this.type = type;
        this.args = args;
        this.description = null;
    }

    public Command(String type, String description) {
        this.type = type;
        this.args = null;
        this.description = description;
    }

    public Command(String type) {
        this.type = type;
        this.args = null;
        this.description = null;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        switch (type) {
            case "bye":
                ui.showExit();
                break;

            case "list":
                ui.showTaskList(tasks);
                break;

            case "todo":
                handleAddTodo(tasks, ui, storage);
                break;

            case "deadline":
                handleAddDeadline(tasks, ui, storage);
                break;

            case "event":
                handleAddEvent(tasks, ui, storage);
                break;

            case "mark":
                handleMark(tasks, ui, storage);
                break;

            case "unmark":
                handleUnmark(tasks, ui, storage);
                break;

            case "delete":
                handleDelete(tasks, ui, storage);
                break;

            default:
                throw new CuteOwlException("OOPS!!! Unknown command: " + type);
        }
    }

    public boolean isExit() {
        return type.equals("bye");
    }

    // cuteowl.command.Command Handlers

    private void handleAddTodo(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
    }

    private void handleAddDeadline(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        if (args.length < 2) {
            throw new CuteOwlException(TAB + "OOPS!!! Please enter your deadline in the format:\n" +
                    TAB + "deadline <description> /by <date>");
        }
        String description = args[0].trim();
        String byInput = args[1].trim();

        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm") // optional: allow leading zeros
        };

        LocalDateTime by = null;
        boolean parsed = false;

        for (DateTimeFormatter formatter : formatters) {
            try {
                by = LocalDateTime.parse(byInput, formatter);
                parsed = true;
                break;
            } catch (DateTimeParseException ignored) {
            }
        }

        if (!parsed) {
            throw new CuteOwlException(TAB + "OOPS!!! The date/time format is invalid.\n" +
                    TAB + "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
        }

        Task task = new Deadline(description, by);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
    }

    private void handleAddEvent(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        if (args.length < 3) {
            throw new CuteOwlException(TAB + "OOPS!!! Please enter your event in the format:\n" +
                    TAB + "event <description> /from <start> /to <end>");
        }

        String description = args[0].trim();
        String fromInput = args[1].trim();
        String toInput = args[1].trim();

        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm") // optional: allow leading zeros
        };

        LocalDateTime from = null;
        LocalDateTime to = null;
        boolean parsed = false;

        for (DateTimeFormatter formatter : formatters) {
            try {
                from = LocalDateTime.parse(fromInput, formatter);
                to = LocalDateTime.parse(toInput, formatter);
                parsed = true;
                break;
            } catch (DateTimeParseException ignored) {
            }
        }

        if (!parsed) {
            throw new CuteOwlException(TAB + "OOPS!!! The date/time format is invalid.\n" +
                    TAB + "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
        }

        Task task = new Event(description, from, to);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
    }

    private void handleMark(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        int index = Integer.parseInt(description);

        if (index > tasks.size()) {
            throw new CuteOwlException(TAB + "Please input a valid index.");
        }

        Task task = tasks.get(index - 1);
        task.mark();
        storage.save(tasks);
        ui.showMarkText(task);
    }

    private void handleUnmark(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        int index = Integer.parseInt(description);

        if (index > tasks.size()) {
            throw new CuteOwlException(TAB + "Please input a valid index.");
        }

        Task task = tasks.get(index - 1);
        task.unmark();
        storage.save(tasks);
        ui.showUnmarkText(task);
    }

    private void handleDelete(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        int index = Integer.parseInt(description);

        if (index > tasks.size()) {
            throw new CuteOwlException(TAB + "Please input a valid index.");
        }

        Task removed = tasks.delete(index - 1);
        storage.save(tasks);
        ui.showTaskDeleted(removed, tasks.size());
    }

}
