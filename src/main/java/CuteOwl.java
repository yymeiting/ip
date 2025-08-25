import java.util.Scanner;
import java.util.ArrayList;

public class CuteOwl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int t_count = 0; // Tasks counter
        String indentation = "    ";
        String line = "    ____________________________________________________________\n";

        String intro_text = line + indentation + " Hello! I'm CuteOwl :)\n" +
                indentation + " What can I do for you?\n" + line;
        System.out.println(intro_text);

        while (true) {
            try {
                String input = scanner.nextLine();

                // Exit program if user input = "bye"
                if (input.equalsIgnoreCase("bye")) {
                    String exit_text = line + indentation + " Bye. Hope to see you again soon!\n" + line;
                    System.out.println(exit_text);
                    break;
                }

                // List task if user input = "list"
                else if (input.equalsIgnoreCase("list")) {
                    String list_tasks = line + indentation + " Here are the tasks in your list:\n";
                    for (int  i = 0; i < t_count; i+=1 ) {
                        list_tasks = list_tasks + indentation + " " + (i + 1) + ". " + tasks.get(i) + "\n";
                    }

                    list_tasks = list_tasks + line;
                    System.out.println(list_tasks);
                }

                // Mark task with "X" if user input = "mark number"
                else if (input.startsWith("mark")) {
                    int list_index = Integer.parseInt(input.substring(5)) - 1;
                    if (list_index >= 0 && list_index < t_count) {
                        tasks.get(list_index).mark();
                        String mark_text = line + indentation + " Nice! I've marked this task as done:\n" +
                                indentation + " " + tasks.get(list_index) + "\n" + line;
                        System.out.println(mark_text);
                    }
                }

                // Unmark task if user input = "unmark number"
                else if (input.startsWith("unmark")) {
                    int list_index = Integer.parseInt(input.substring(7)) - 1;
                    if (list_index >= 0 && list_index < t_count) {
                        tasks.get(list_index).unmark();
                        String unmark_text = line + indentation + " OK, I've marked this task as not done yet:\n" +
                                indentation + " " + tasks.get(list_index) + "\n" + line;
                        System.out.println(unmark_text);
                    }
                }

                // todo task if user input = "todo"
                else if (input.startsWith("todo")) {
                    String description = input.length() > 5 ? input.substring(5).trim() : "";

                    if (description.isEmpty()) {
                        throw new CuteOwlException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    t_count += 1;
                }

                // deadline task if user input = "deadline"
                else if (input.startsWith("deadline")) {
                    String description = input.length() > 9 ? input.substring(9).trim() : "";

                    if (description.isEmpty()) {
                        throw new CuteOwlException("OOPS!!! The description of a deadline cannot be empty.");
                    }

                    if (!description.contains("/by")) {
                        throw new CuteOwlException("OOPS!!! Please enter your deadline in the correct format\n" +
                                indentation + " (i.e. deadline <task> /by <date>). ");
                    }

                    String[] parts = input.substring(9).split("/by");
                    String event_desc = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(event_desc, by));
                    t_count += 1;
                }

                // event task if user input = "event"
                else if (input.startsWith("event")) {
                    String description = input.length() > 6 ? input.substring(6).trim() : "";

                    if (description.isEmpty()) {
                        throw new CuteOwlException("OOPS!!! The description of a event cannot be empty.");
                    }

                    if (!description.contains("/from") &&  !description.contains("/to")) {
                        throw new CuteOwlException("OOPS!!! Please enter your event in the correct format\n" + indentation +
                                " (i.e. event <task> /from <date> /to <date>). ");
                    }

                    String[] parts = input.substring(6).split("/from|/to");
                    String event_desc = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks.add(new Event(event_desc, from, to));
                    t_count += 1;
                }

                // delete task if user input = "delete"
                else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;

                    if (index < 0 || index >= t_count) {
                        throw new CuteOwlException("OOPS!!! The index out of bounds.");
                    }

                    Task removedTask = tasks.remove(index);
                    t_count -= 1;
                    String delete_text = line + indentation + " Noted. I've removed this task:\n" +
                            indentation + "   " + removedTask + "\n" +
                            indentation + " Now you have " + tasks.size() + " tasks in the list.\n" + line;
                        System.out.println(delete_text);
                }

                else {
                    throw new CuteOwlException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                // Print task after user input
                if (input.startsWith("event") || input.startsWith("deadline") || input.startsWith("todo")) {
                    String task_text =  line + indentation + " Got it. I've added this task:\n" +
                            indentation + "   " + tasks.get(t_count - 1) + "\n" +
                            indentation + " Now you have " + t_count + " tasks in the list.\n" + line;
                    System.out.println(task_text);
                }

            } catch (CuteOwlException e) {
                String error_text = line + indentation + " " + e.getMessage() + "\n" + line;
                System.out.println(error_text);
            } catch (Exception e) {
                String error_text = line + indentation + " " + e.getMessage() + "\n" + line;
                System.out.println(error_text);
            }

        }
        scanner.close();
    }
}