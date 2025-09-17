package cuteowl;

import cuteowl.command.Command;
import cuteowl.exception.CuteOwlException;
import cuteowl.note.NoteList;
import cuteowl.parser.Parser;
import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

/**
 * The main class for CuteOwl chatbot application.
 * This class initializes core components and handles main execution loop.
 */

public class CuteOwl {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private NoteList notes;

    /**
     * Constructs a CuteOwl instance with the specified file path for persistent storage.
     * Initializes UI, storage and loads tasks from file.
     * If loading fails, ui will show loading error and starts with an empty task list.
     *
     * @param filePath Path to the file used for loading and saving tasks.
     */
    public CuteOwl(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        notes = new NoteList();
    }

    /**
     * Starts the main loop of the chatbot.
     * Continuously reads user commands, parses and execute them until an exit command is received.
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage, notes);
                isExit = c.isExit();
            } catch (CuteOwlException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point of the application.
     * Creates a CuteOwl instance and starts the chatbot.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new CuteOwl("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage, notes);
        } catch (CuteOwlException e) {
            return ui.showError(e.getMessage()); // You might need to add this method
        }
    }


}