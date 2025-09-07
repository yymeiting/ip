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

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        switch (type) {
            case "bye":
                ui.showExit();
                return ui.exitMessage();

            case "list":
                ui.showTaskList(tasks);
                return ui.showTaskListGUI(tasks);

            case "todo":
                return handleAddTodo(tasks, ui, storage);

            case "deadline":
                return handleAddDeadline(tasks, ui, storage);

            case "event":
                return handleAddEvent(tasks, ui, storage);

            case "mark":
                return handleMark(tasks, ui, storage);

            case "unmark":
                return handleUnmark(tasks, ui, storage);

            case "delete":
                return handleDelete(tasks, ui, storage);

            case "find":
                return handleFind(tasks, ui, storage);

            default:
                System.out.println(TAB + "OOPS!!! Unknown command: " + type);
                throw new CuteOwlException("OOPS!!! Unknown command: " + type);
        }
    }

    public boolean isExit() {
        return type.equals("bye");
    }

    // cuteowl.command.Command Handlers

    private String handleAddTodo(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
        return ui.showTaskAddedGUI(task, tasks.size());
    }

    private String handleAddDeadline(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        if (args.length < 2) {
            System.out.println(TAB + "OOPS!!! Please enter your deadline in the format:\n" +
                    TAB + "deadline <description> /by <date>");
            throw new CuteOwlException("OOPS!!! Please enter your deadline in the format:\n" +
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
            System.out.println(TAB + "OOPS!!! The date/time format is invalid.\n" +
                    TAB + "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
            throw new CuteOwlException("OOPS!!! The date/time format is invalid.\n" +
                    "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
        }

        Task task = new Deadline(description, by);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
        return ui.showTaskAddedGUI(task, tasks.size());
    }

    private String handleAddEvent(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        if (args.length < 3) {
            System.out.println(TAB + "OOPS!!! Please enter your event in the format:\n" +
                    TAB + "event <description> /from <start> /to <end>");
            throw new CuteOwlException("OOPS!!! Please enter your event in the format:\n" +
                    "event <description> /from <start> /to <end>");
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
            System.out.println("OOPS!!! The date/time format is invalid.\n" +
                    "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
            throw new CuteOwlException(TAB + "OOPS!!! The date/time format is invalid.\n" +
                    TAB + "Please use: d/M/yyyy HHmm (e.g., 2/9/2025 1800)");
        }

        Task task = new Event(description, from, to);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
        return ui.showTaskAddedGUI(task, tasks.size());
    }

    private String handleMark(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        int index = Integer.parseInt(description);

        if (index > tasks.size()) {
            System.out.println(TAB + "Please input a valid index.");
            throw new CuteOwlException("Please input a valid index.");
        }

        Task task = tasks.get(index - 1);
        task.mark();
        storage.save(tasks);
        ui.showMarkText(task);
        return ui.showMarkTextGUI(task);
    }

    private String handleUnmark(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        int index = Integer.parseInt(description);

        if (index > tasks.size()) {
            System.out.println("Please input a valid index.");
            throw new CuteOwlException(TAB + "Please input a valid index.");
        }

        Task task = tasks.get(index - 1);
        task.unmark();
        storage.save(tasks);
        ui.showUnmarkText(task);
        return ui.showUnmarkTextGUI(task);
    }

    private String handleDelete(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        int index = Integer.parseInt(description);

        if (index > tasks.size()) {
            System.out.println(TAB + "Please input a valid index.");
            throw new CuteOwlException("Please input a valid index.");
        }

        Task removed = tasks.delete(index - 1);
        storage.save(tasks);
        ui.showTaskDeleted(removed, tasks.size());
        return ui.showTaskDeletedGUI(removed, tasks.size());
    }

    private String handleFind(TaskList tasks, Ui ui, Storage storage) throws CuteOwlException {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks.getAll()) {
            if (task.getDescription().contains(description)) {
                matchingTasks.add(task);
            }
        }
        ui.showTaskList(matchingTasks);
        return ui.showTaskListGUI(matchingTasks);
    }

}
