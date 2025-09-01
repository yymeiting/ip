package cuteowl.ui;

import cuteowl.task.Task;
import cuteowl.task.TaskList;

import java.util.Scanner;

public class Ui {
    String CHAT_INDENTATION = "     ";
    String DRAW_LINE = "    _______________________________________________________________________";
    private Scanner scanner = new Scanner(System.in);

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(DRAW_LINE);
    }

    public void showWelcome() {
        showLine();
        String introText = CHAT_INDENTATION + "Hello! I'm CuteOwl :)\n" +
                CHAT_INDENTATION + "What can I do for you?";
        System.out.println(introText);
        showLine();
    }

    public void showExit() {
        String exitText = CHAT_INDENTATION + "Bye. Hope to see you again soon!";
        System.out.println(exitText);
    }

    public void showLoadingError() {
        System.out.println("LOAD ERROR");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskList(TaskList taskList) {
        String listTasks = CHAT_INDENTATION + "Here are the tasks in your list:\n";
        for (int  i = 0; i < taskList.size() ; i+=1 ) {
            listTasks = listTasks + CHAT_INDENTATION + " " + (i + 1) + ". " + taskList.get(i) + "\n";
        }
        System.out.print(listTasks);
    }

    public void showTaskAdded(Task task, int size) {
            String taskAdded =  CHAT_INDENTATION + "Got it. I've added this task:\n" +
                    CHAT_INDENTATION + "  " + task + "\n" +
                    CHAT_INDENTATION + "Now you have " + size + " tasks in the list.";
            System.out.println(taskAdded);
    }

    public void showMarkText(Task task) {
        String markText = CHAT_INDENTATION + "Nice! I've marked this task as done:\n" +
                CHAT_INDENTATION + task;
        System.out.println(markText);
    }

    public void showUnmarkText(Task task) {
        String unmarkText = CHAT_INDENTATION + "OK, I've marked this task as not done yet:\n" +
                CHAT_INDENTATION + task;
        System.out.println(unmarkText);
    }

    public void showTaskDeleted(Task task, int size) {
        String taskRemoved = CHAT_INDENTATION + "Noted. I've removed this task:\n" +
                CHAT_INDENTATION + "  " + task + "\n" +
                CHAT_INDENTATION + "Now you have " + size + " tasks in the list.";
        System.out.println(taskRemoved);
    }

}
