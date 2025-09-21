package cuteowl.ui;

import cuteowl.note.Note;
import cuteowl.note.NoteList;
import cuteowl.task.Task;
import cuteowl.task.TaskList;

import java.util.Scanner;

/**
 * Handles all user interactions in CuteOwl chatbot application.
 * Responsible for displaying messages, reading user inputs.
 */
public class Ui {
    /** Indentation used for chatbot messages for formatting */
    private static final String CHAT_INDENTATION = "     ";

    /** Horizontal line used to visually separate chatbot output */
    private static final String DRAW_LINE = "    _______________________________________________________________________";

    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a Ui instance and initializes the input scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Display a horizontal line to separate chatbot output.
     */
    public void showLine() {
        System.out.println(DRAW_LINE);
    }

    /**
     * Displays the welcome message when the chatbot starts
     */
    public void showWelcome() {
        showLine();
        String introText = CHAT_INDENTATION + "Hello! I'm CuteOwl :)\n" +
                CHAT_INDENTATION + "What can I do for you?";
        System.out.println(introText);
        showLine();
    }

    /**
     * Display the exit message when chatbot ends.
     */
    public void showExit() {
        String exitText = CHAT_INDENTATION + "Bye. Hope to see you again soon!";
        System.out.println(exitText);
    }

    public String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Display error message when loading from file fails.
     */
    public void showLoadingError() {
        System.out.println("Error encountered while loading file");
    }

    /**
     * Display a custom error message.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        //System.out.println(message);
        return message;
    }

    /**
     * Reads the next line of user input
     *
     * @return The user's command as a string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks The TaskList containing all tasks.
     */
    public void showTaskList(TaskList tasks) {
        String listTasks = CHAT_INDENTATION + "Here are the tasks in your list:\n";
        for (int  i = 0; i < tasks.size() ; i+=1 ) {
            listTasks = listTasks + CHAT_INDENTATION + " " + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        System.out.print(listTasks);
    }

    public String showTaskListGUI(TaskList tasks) {
        String listTasks = "Here are the tasks in your list:\n";
        for (int  i = 0; i < tasks.size() ; i++ ) {
            listTasks = listTasks + " " + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return listTasks;
    }

    /**
     * Displays the current list of notes.
     *
     * @param notes The NoteList containing all notes.
     */
    public void showNotesList(NoteList notes) {
        String listNotes;
        if (notes.isEmpty()) {
            listNotes = CHAT_INDENTATION + "Sadly there are no notes now!!\n";
        } else {
            listNotes = CHAT_INDENTATION + "Here are your notes~ \n";
            for (int i = 0; i < notes.size(); i += 1) {
                listNotes = listNotes + CHAT_INDENTATION + "-- " + notes.get(i) + "\n";
            }
        }
        System.out.print(listNotes);
    }

    public String showNoteListGUI(NoteList notes) {
        String listNotes;
        if (notes.isEmpty()) {
            listNotes = "Sadly there are no notes now!!\n";
        } else {
            listNotes = "Here are your notes~ \n";
            for (int i = 0; i < notes.size(); i++ ) {
                listNotes = listNotes + "-- " + notes.get(i) + "\n";
            }
        }
        return listNotes;
    }

    /**
     * Displays a confirmation message when a task is added.
     *
     * @param task The task that was added.
     * @param size The updated number of task in the list.
     */
    public void showTaskAdded(Task task, int size) {
            String taskAdded =  CHAT_INDENTATION + "Got it. I've added this task:\n" +
                    CHAT_INDENTATION + "  " + task + "\n" +
                    CHAT_INDENTATION + "Now you have " + size + " tasks in the list.";
            System.out.println(taskAdded);
    }

    public String showTaskAddedGUI(Task task, int size) {
        return "Got it. I've added this task:\n" +
                "  " + task + "\n" +
                "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a confirmation message when a note is added.
     *
     * @param note The note that was added.
     */
    public void showNoteAdded(Note note) {
        String noteAdded = CHAT_INDENTATION + "You've noted down " + note;
        System.out.println(noteAdded);
    }

    public String showNoteAddedGUI(Note note) {
        return "You've noted down " + note;
    }

    /**
     * Display a message when task is marked as done.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        String taskMarked = CHAT_INDENTATION + "Nice! I've marked this task as done:\n" +
                CHAT_INDENTATION + task;
        System.out.println(taskMarked);
    }

    public String showTaskMarkedGUI(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        String unmarkText = CHAT_INDENTATION + "OK, I've marked this task as not done yet:\n" +
                CHAT_INDENTATION + task;
        System.out.println(unmarkText);
    }

    public String showTaskUnmarkGUI(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Display a confirmation message when task is deleted.
     *
     * @param task The task that was removed.
     * @param size The updated number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int size) {
        String taskRemoved = CHAT_INDENTATION + "Noted. I've removed this task:\n" +
                CHAT_INDENTATION + "  " + task + "\n" +
                CHAT_INDENTATION + "Now you have " + size + " tasks in the list.";
        System.out.println(taskRemoved);
    }

    public String showTaskDeletedGUI(Task task, int size) {
        return "Noted. I've removed this task:\n" +
                "  " + task + "\n" +
                "Now you have " + size + " tasks in the list.";
    }

}
