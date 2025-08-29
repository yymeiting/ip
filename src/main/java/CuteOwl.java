import java.util.Scanner;
import java.util.ArrayList;

public class CuteOwl {
    public static void main(String[] args) {
        String CHAT_INDENTATION = "    ";
        String DRAW_LINE = "    ____________________________________________________________\n";

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        ArrayList<Task> tasks = storage.load();
        int tasksCounter = 0;

        String intro_text = DRAW_LINE + CHAT_INDENTATION + " Hello! I'm CuteOwl :)\n" +
                CHAT_INDENTATION + " What can I do for you?\n" + DRAW_LINE;
        System.out.println(intro_text);


        while (true) {
            try {
                String input = scanner.nextLine();

                // Exit program if user input = "bye"
                if (input.equalsIgnoreCase("bye")) {
                    String exit_text = DRAW_LINE + CHAT_INDENTATION + " Bye. Hope to see you again soon!\n" + DRAW_LINE;
                    System.out.println(exit_text);
                    break;
                }

                // List task if user input = "list"
                else if (input.equalsIgnoreCase("list")) {
                    String list_tasks = DRAW_LINE + CHAT_INDENTATION + " Here are the tasks in your list:\n";
                    for (int  i = 0; i < tasksCounter; i+=1 ) {
                        list_tasks = list_tasks + CHAT_INDENTATION + " " + (i + 1) + ". " + tasks.get(i) + "\n";
                    }

                    list_tasks = list_tasks + DRAW_LINE;
                    System.out.println(list_tasks);
                }

                // Mark task with "X" if user input = "mark number"
                else if (input.startsWith("mark")) {
                    int list_index = Integer.parseInt(input.substring(5)) - 1;
                    if (list_index >= 0 && list_index < tasksCounter) {
                        tasks.get(list_index).mark();
                        String mark_text = DRAW_LINE + CHAT_INDENTATION + " Nice! I've marked this task as done:\n" +
                                CHAT_INDENTATION + " " + tasks.get(list_index) + "\n" + DRAW_LINE;
                        System.out.println(mark_text);

                        storage.save(tasks);
                    }


                }

                // Unmark task if user input = "unmark number"
                else if (input.startsWith("unmark")) {
                    int list_index = Integer.parseInt(input.substring(7)) - 1;
                    if (list_index >= 0 && list_index < tasksCounter) {
                        tasks.get(list_index).unmark();
                        String unmark_text = DRAW_LINE + CHAT_INDENTATION + " OK, I've marked this task as not done yet:\n" +
                                CHAT_INDENTATION + " " + tasks.get(list_index) + "\n" + DRAW_LINE;
                        System.out.println(unmark_text);

                        storage.save(tasks);
                    }
                }

                // todo task if user input = "todo"
                else if (input.startsWith("todo")) {
                    String description = input.length() > 5 ? input.substring(5).trim() : "";

                    if (description.isEmpty()) {
                        throw new CuteOwlException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    tasksCounter += 1;
                    storage.save(tasks);
                }

                // deadline task if user input = "deadline"
                else if (input.startsWith("deadline")) {
                    String description = input.length() > 9 ? input.substring(9).trim() : "";

                    if (description.isEmpty()) {
                        throw new CuteOwlException("OOPS!!! The description of a deadline cannot be empty.");
                    }

                    if (!description.contains("/by")) {
                        throw new CuteOwlException("OOPS!!! Please enter your deadline in the correct format\n" +
                                CHAT_INDENTATION + " (i.e. deadline <task> /by <date>). ");
                    }

                    String[] parts = input.substring(9).split("/by");
                    String event_desc = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(event_desc, by));
                    tasksCounter += 1;

                    storage.save(tasks);
                }

                // event task if user input = "event"
                else if (input.startsWith("event")) {
                    String description = input.length() > 6 ? input.substring(6).trim() : "";

                    if (description.isEmpty()) {
                        throw new CuteOwlException("OOPS!!! The description of a event cannot be empty.");
                    }

                    if (!description.contains("/from") &&  !description.contains("/to")) {
                        throw new CuteOwlException("OOPS!!! Please enter your event in the correct format\n" + CHAT_INDENTATION +
                                " (i.e. event <task> /from <date> /to <date>). ");
                    }

                    String[] parts = input.substring(6).split("/from|/to");
                    String event_desc = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks.add(new Event(event_desc, from, to));
                    tasksCounter += 1;

                    storage.save(tasks);
                }

                // delete task if user input = "delete"
                else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;

                    if (index < 0 || index >= tasksCounter) {
                        throw new CuteOwlException("OOPS!!! The index out of bounds.");
                    }

                    Task removedTask = tasks.remove(index);
                    tasksCounter -= 1;
                    String delete_text = DRAW_LINE + CHAT_INDENTATION + " Noted. I've removed this task:\n" +
                            CHAT_INDENTATION + "   " + removedTask + "\n" +
                            CHAT_INDENTATION + " Now you have " + tasks.size() + " tasks in the list.\n" + DRAW_LINE;
                        System.out.println(delete_text);

                    storage.save(tasks);
                }
                else {
                    throw new CuteOwlException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                // Print task after user input
                if (input.startsWith("event") || input.startsWith("deadline") || input.startsWith("todo")) {
                    String task_text =  DRAW_LINE + CHAT_INDENTATION + " Got it. I've added this task:\n" +
                            CHAT_INDENTATION + "   " + tasks.get(tasksCounter - 1) + "\n" +
                            CHAT_INDENTATION + " Now you have " + tasksCounter + " tasks in the list.\n" + DRAW_LINE;
                    System.out.println(task_text);

                    storage.save(tasks);
                }

            } catch (CuteOwlException e) {
                String error_text = DRAW_LINE + CHAT_INDENTATION + " " + e.getMessage() + "\n" + DRAW_LINE;
                System.out.println(error_text);
            } catch (Exception e) {
                String error_text = DRAW_LINE + CHAT_INDENTATION + " " + e.getMessage() + "\n" + DRAW_LINE;
                System.out.println(error_text);
            }

        }
        scanner.close();
    }
}