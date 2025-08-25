import java.util.Scanner;

public class CuteOwl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
            }
            String echo_text = "\n" + indentation + "____________________________________________________________\n" +
                    indentation + " " + input + "\n" +
                    indentation + "____________________________________________________________\n";
            System.out.println(echo_text);
        }

        scanner.close();
    }
}