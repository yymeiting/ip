package cuteowl.ui;

import cuteowl.task.Task;
import cuteowl.task.TaskList;

import java.util.Scanner;

/**
 * Handles all user interactions in CuteOwl chatbot application.
 * Responsible for displaying messages, reading user inputs.
 */
public class Ui {
    /** Indentation used for chatbot messages for formatting */
    String CHAT_INDENTATION = "     ";

    /** Horizontal line used to visually separate chatbot output */
    String DRAW_LINE = "    _______________________________________________________________________";

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
        String intro_text = CHAT_INDENTATION + "Hello! I'm CuteOwl :)\n" +
                CHAT_INDENTATION + "What can I do for you?";
        System.out.println(intro_text);
        showLine();
    }

    /**
     * Display the exit message when chatbot ends.
     */
    public void showExit() {
        String exit_text = CHAT_INDENTATION + "Bye. Hope to see you again soon!";
        System.out.println(exit_text);
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
    public void showError(String message) {
        System.out.println(message);
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
     * @param taskList The TaskList containing all tasks.
     */
    public void showTaskList(TaskList taskList) {
        String listTasks = CHAT_INDENTATION + "Here are the tasks in your list:\n";
        for (int  i = 0; i < taskList.size() ; i+=1 ) {
            listTasks = listTasks + CHAT_INDENTATION + " " + (i + 1) + ". " + taskList.get(i) + "\n";
        }
        System.out.print(listTasks);
    }

    /**
     * Displays a confirmation message when a task is added.
     *
     * @param task The task that was added.
     * @param size The updated number of task in the list.
     */
    public void showTaskAdded(Task task, int size) {
            String task_text =  CHAT_INDENTATION + "Got it. I've added this task:\n" +
                    CHAT_INDENTATION + "  " + task + "\n" +
                    CHAT_INDENTATION + "Now you have " + size + " tasks in the list.";
            System.out.println(task_text);
    }

    /**
     * Display a message when task is marked as done.
     *
     * @param task The task that was marked.
     */
    public void showMarkText(Task task) {
        String markText = CHAT_INDENTATION + "Nice! I've marked this task as done:\n" +
                CHAT_INDENTATION + task;
        System.out.println(markText);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkText(Task task) {
        String unmarkText = CHAT_INDENTATION + "OK, I've marked this task as not done yet:\n" +
                CHAT_INDENTATION + task;
        System.out.println(unmarkText);
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

}
