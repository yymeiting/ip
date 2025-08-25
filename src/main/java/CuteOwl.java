import java.util.Scanner;

public class CuteOwl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100]; // Assume that there will be no more than 100 tasks
        int t_count = 0; // Tasks counter
        String indentation = "    ";

        String intro_text = indentation + "____________________________________________________________\n" +
                indentation + " Hello! I'm CuteOwl :) \n" +
                indentation + " What can I do for you?\n" +
                indentation + "____________________________________________________________\n";
        System.out.println(intro_text);

        while (true) {
            String input = scanner.nextLine();

            // Exit program if user input = "bye"
            if (input.equalsIgnoreCase("bye")) {
                String exit_text = indentation + "____________________________________________________________\n" +
                        indentation + " Bye. Hope to see you again soon! \n" +
                        indentation + "____________________________________________________________\n";
                System.out.println(exit_text);
                break;
            }

            // List task if user input = "list"
            else if (input.equalsIgnoreCase("list")) {
                String list_tasks = "\n" + indentation + "____________________________________________________________\n";
                for (int  i = 0; i < t_count; i+=1 ) {
                    list_tasks = list_tasks + indentation + " " + (i + 1) + ". " + tasks[i] + "\n";
                }

                list_tasks = list_tasks + indentation + "____________________________________________________________\n";
                System.out.println(list_tasks);
            }

            // Mark task with "X" if user input = "mark number"
            else if (input.startsWith("mark ")) {
                int list_index = Integer.parseInt(input.substring(5)) - 1;
                if (list_index >= 0 && list_index < t_count) {
                    tasks[list_index].mark();
                    String mark_text = "\n" + indentation + "____________________________________________________________\n" +
                            indentation + " Nice! I've marked this task as done:\n" +
                            indentation + " " + tasks[list_index] + "\n" +
                            "____________________________________________________________\n";
                    System.out.println(mark_text);
                }
            }

            // Unmark task if user input = "unmark number"
            else if (input.startsWith("unmark ")) {
                int list_index = Integer.parseInt(input.substring(7)) - 1;
                if (list_index >= 0 && list_index < t_count) {
                    tasks[list_index].unmark();
                    String unmark_text = "\n" + indentation + "____________________________________________________________\n" +
                            indentation + " OK, I've marked this task as not done yet:\n" +
                            indentation + " " + tasks[list_index] + "\n" +
                            "____________________________________________________________\n";
                    System.out.println(unmark_text);
                }
            }

            // Otherwise add tasks
            else {
                tasks[t_count] = new Task(input);
                t_count += 1;

                String echo_text = "\n" + indentation + "____________________________________________________________\n" +
                        indentation + " added: " + input + "\n" +
                        indentation + "____________________________________________________________\n";
                System.out.println(echo_text);
            }
        }
        scanner.close();
    }
}