import java.util.Scanner;

public class CuteOwl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100]; // Assume that there will be no more than 100 tasks
        int t_count = 0; // Tasks counter
        String indentation = "    ";

        String intro_text = indentation + "____________________________________________________________\n" +
                indentation + " Hello! I'm CuteOwl :)\n" +
                indentation + " What can I do for you?\n" +
                indentation + "____________________________________________________________\n";
        System.out.println(intro_text);

        while (true) {
            try {
                String input = scanner.nextLine();

                // Exit program if user input = "bye"
                if (input.equalsIgnoreCase("bye")) {
                    String exit_text = indentation + "____________________________________________________________\n" +
                            indentation + " Bye. Hope to see you again soon!\n" +
                            indentation + "____________________________________________________________\n";
                    System.out.println(exit_text);
                    break;
                }

                // List task if user input = "list"
                else if (input.equalsIgnoreCase("list")) {
                    String list_tasks = indentation + "____________________________________________________________\n" +
                            indentation + " Here are the tasks in your list:\n";
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
                        String mark_text = indentation + "____________________________________________________________\n" +
                                indentation + " Nice! I've marked this task as done:\n" +
                                indentation + " " + tasks[list_index] + "\n" +
                                indentation + "____________________________________________________________\n";
                        System.out.println(mark_text);
                    }
                }

                // Unmark task if user input = "unmark number"
                else if (input.startsWith("unmark ")) {
                    int list_index = Integer.parseInt(input.substring(7)) - 1;
                    if (list_index >= 0 && list_index < t_count) {
                        tasks[list_index].unmark();
                        String unmark_text = indentation + "____________________________________________________________\n" +
                                indentation + " OK, I've marked this task as not done yet:\n" +
                                indentation + " " + tasks[list_index] + "\n" +
                                indentation + "____________________________________________________________\n";
                        System.out.println(unmark_text);
                    }
                }

                // todo task if user input = "todo"
                else if (input.startsWith("todo ")) {
                    String description = input.length() > 5 ? input.substring(5).trim() : "";

                    if (description.isEmpty()) {
                        throw new CuteOwlException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks[t_count] = new Todo(description);
                    t_count += 1;
                }

                // deadline task if user input = "deadline"
                else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split("/by");
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks[t_count] = new Deadline(description, by);
                    t_count += 1;
                }

                // deadline task if user input = "event"
                else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split("/from|/to");
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks[t_count] = new Event(description, from, to);
                    t_count += 1;
                } else {
                    throw new CuteOwlException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                // Print task after user input
                if (input.startsWith("event ") || input.startsWith("deadline ") || input.startsWith("todo ")) {
                    String task_text =  indentation + "____________________________________________________________\n" +
                            indentation + " Got it. I've added this task:\n" +
                            indentation + "   " + tasks[t_count - 1] + "\n" +
                            indentation + " Now you have " + t_count + " tasks in the list.\n" +
                            indentation + "____________________________________________________________\n";
                    System.out.println(task_text);
                }

            } catch (CuteOwlException e) {
                String error_text = indentation + "____________________________________________________________\n" +
                        indentation + " " + e.getMessage() + "\n" +
                        indentation + "____________________________________________________________\n";
                System.out.println(error_text);
            } catch (Exception e) {
                String error_text = indentation + "____________________________________________________________\n" +
                        indentation + " " + e.getMessage() + "\n" +
                        indentation + "____________________________________________________________\n";
                System.out.println(error_text);
            }

        }
        scanner.close();
    }
}