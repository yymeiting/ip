import java.util.Scanner;

public class CuteOwl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100]; // Assume that there will be no more than 100 tasks
        int t_count = 0;
        String indentation = "    ";

        String intro_text = indentation + "____________________________________________________________\n" +
                indentation + " Hello! I'm CuteOwl :) \n" +
                indentation + " What can I do for you?\n" +
                indentation + "____________________________________________________________\n";
        System.out.println(intro_text);

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                String exit_text = indentation + "____________________________________________________________\n" +
                        indentation + " Bye. Hope to see you again soon! \n" +
                        indentation + "____________________________________________________________\n";
                System.out.println(exit_text);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                String list_tasks = "\n" + indentation + "____________________________________________________________\n";
                for (int  i = 0; i < t_count; i+=1 ) {
                    list_tasks = list_tasks + indentation + " " + (i + 1) + ". " + tasks[i] + "\n";
                }
                //        indentation + " added: " + input + "\n" +
                list_tasks = list_tasks + indentation + "____________________________________________________________\n";
                System.out.println(list_tasks);
            } else {
                tasks[t_count] = input;
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