import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class fieldRunner {
    private ArrayList<String> itemNames = new ArrayList<String>();
    private ArrayList<fieldItem> items = new ArrayList<fieldItem>();
    private fieldConfig config = new fieldConfig();
    private boolean shouldDie = false;
    private Scanner console = new Scanner(System.in);
    private String command;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Scanner stringReader = new Scanner("");
    private ArrayList<String> args;

    public fieldRunner() {
        command = "";

        while(!shouldDie) {
            printMenu();
            command = console.nextLine().toLowerCase(Locale.ROOT);
            stringReader = new Scanner(command).useDelimiter(" ");
            args = new ArrayList<String>();

            while(stringReader.hasNext()) {
                args.add(stringReader.next());
            }

            if(command.startsWith("add")) {

            } else if (command.startsWith("remove")) {

            } else if (command.startsWith("edit")) {

            } else if (command.startsWith("compute")) {

            } else if (command.startsWith("view")) {
                if(args.size() > 2|| args.size() < 2) {
                    System.out.println(ANSI_RED + "Incorrect Usage. Operation Failed" + ANSI_RESET + "\n");
                }
                if (args.get(1).equals("vectors")) {
                    System.out.println(config.toString("vector"));
                } else if (args.get(1).equals("points")) {
                    System.out.println(config.toString("point"));
                } else if (args.get(1).equals("all")) {
                    System.out.println(config.toString());
                } else {
                    System.out.println(ANSI_RED + "Incorrect Argument Usage. Operation Failed" + ANSI_RESET + "\n");
                    System.out.println();
                }

            } else {
                System.out.println(ANSI_RED + "Command does not exist." + ANSI_RESET + "\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("===========================");
        System.out.println("What would you like to do?: ");
        System.out.println(" - Add <Vector/Point>");
        System.out.println(" - View <Vectors/Points/All>");
        System.out.println(" - Remove <Item Name>");
        System.out.println(" - Edit <Item Name>");
        System.out.println(" - Compute");
        System.out.print("===========================\n\n\n -> ");
    }
}

