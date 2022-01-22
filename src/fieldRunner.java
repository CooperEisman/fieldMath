import java.util.Arrays;
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
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private Scanner stringReader = new Scanner("");

    private ArrayList<String> args;
    private double[] tempArgs;

    public fieldRunner() {
        updateData();

        while(!shouldDie) {
            printMenu();
            command = console.nextLine().toLowerCase(Locale.ROOT);
            stringReader = new Scanner(command).useDelimiter(" ");
            args = new ArrayList<String>();

            while(stringReader.hasNext()) {
                args.add(stringReader.next());
            }

            if(args.get(0).equals("add")) {
                if(args.size() != 3) {
                    System.out.println(ANSI_RED + "Incorrect Usage. Operation Failed" + ANSI_RESET + "\n");
                } else {
                    if(itemNames.contains(args.get(2))) {
                        System.out.println(ANSI_RED + "Item '" + args.get(2) + "' already exists!" + ANSI_RESET + "\n");
                } else {
                    add(args.get(1), args.get(2));
                    System.out.println();
                }
            }
            } else if (args.get(0).equals("remove")) {
                if(args.size() != 2) {
                    System.out.println(ANSI_RED + "Incorrect Usage. Operation Failed" + ANSI_RESET + "\n");
                } else {
                    if(itemNames.contains(args.get(1))) {
                        edit(itemNames.indexOf(args.get(1)));
                    } else {
                        System.out.println(ANSI_RED + "Item '" + args.get(1) + "' does not exist!" + ANSI_RESET + "\n");
                        System.out.println();
                    }
                }
            } else if (args.get(0).equals("edit")) {
                if(args.size() != 2) {
                    System.out.println(ANSI_RED + "Incorrect Usage. Operation Failed" + ANSI_RESET + "\n");
                } else {
                    if(itemNames.contains(args.get(1))) {
                        edit(itemNames.indexOf(args.get(1)));
                    } else {
                        System.out.println(ANSI_RED + "Item '" + args.get(1) + "' does not exist!" + ANSI_RESET + "\n");
                        System.out.println();
                    }
                }
            } else if (args.get(0).equals("compute"))
            {
                compute();
            } else if (args.get(0).equals("view")) {
                if (args.size() > 2 || args.size() < 2) {
                    System.out.println(ANSI_RED + "Incorrect Usage. Operation Failed" + ANSI_RESET + "\n");
                } else {
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
                }
            } else if (args.get(0).equals("clear")) {
                    if(args.size() != 1) {
                        System.out.println(ANSI_RED + "Incorrect Usage. Operation Failed" + ANSI_RESET + "\n");
                    } else {
                        clear();
                    }
            } else {
                System.out.println(ANSI_RED + "Command does not exist." + ANSI_RESET + "\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("===========================");
        System.out.println("What would you like to do?: ");
        System.out.println(" - Add <Type> <Name>");
        System.out.println(" - View <Vectors/Points/All>");
        System.out.println(" - Remove <Item Name>");
        System.out.println(" - Edit <Item Name>");
        System.out.println(" - Clear");
        System.out.println(" - Compute");
        System.out.print("===========================\n\n\n -> ");
    }

    private void compute() {

    }

    private void add(String type, String name) {
        System.out.println("What is the order for " + type + " '" + name + "'?");
        int tempInt = console.nextInt();
        console.nextLine();

        fieldItem temp;

        if(type.equals("vector")) {
            temp = new fieldVector(tempInt);
        } else {
            temp = new fieldPoint(tempInt);
        }

        tempInt = items.size();
        config.addItem(temp,name);
        updateData();
        edit(tempInt);
    }

    private void edit(int itemIndex) {
        fieldItem item = items.get(itemIndex);
        String name = itemNames.get(itemIndex);

        System.out.println("\n");
        System.out.println("You are editing " + item.type() + " '" + name + "'");
        System.out.println("Current Value: " + item);
        System.out.println("Type 'cancel' at any point to stop this operation.\n");

        tempArgs = new double[item.getOrder()];

        for(int i = 0; i < item.getOrder(); i++) {
            System.out.print("Dimension #" + i + ": ");
            command = "" + console.nextDouble();
            console.nextLine();
            System.out.println();

            if(command.toLowerCase(Locale.ROOT).equals("cancel")) {
                System.out.println(ANSI_RED + "Operation Halted, No Changes Made" + ANSI_RESET + "\n");
                return;
            } else {
                tempArgs[i] = Double.parseDouble(command);
            }
        }

        fieldItem temp;
        if(item.type().equals("vector")) {
            temp = new fieldVector(tempArgs);
        } else {
            temp = new fieldPoint(tempArgs);
        }

        System.out.print(item.type() + " '" + name + "' will become: " + temp + " (yes/no) \n -> ");
        command = console.nextLine().toLowerCase(Locale.ROOT);


        if(command.equals("yes")) {
            config.addItem(temp,name);
            System.out.println(ANSI_BLUE + "Operation Successful." + ANSI_RESET + "\n");
        } else {
            System.out.println(ANSI_RED + "Operation Stopped. No Changes Made" + ANSI_RESET + "\n");
        }
    }

    private void remove(int itemIndex) {
        System.out.println("\n");
        System.out.print(ANSI_YELLOW + "Are you sure you want to delete '" + itemNames.get(itemIndex) + "' permanently? (yes/no)" + ANSI_RESET + "\n -> ");
        command = console.nextLine();

        if(command.equals("yes")) {
            config.removeItem(itemNames.get(itemIndex));
            System.out.println(ANSI_BLUE + "Operation Successful." + ANSI_RESET + "\n");
        } else {
            System.out.println(ANSI_RED + "Operation Stopped. No Changes Made" + ANSI_RESET + "\n");
        }
    }
    private void clear() {
        System.out.println("\n");
        System.out.print(ANSI_YELLOW + "Are you sure you want to clear ALL saved data? (yes/no)" + ANSI_RESET + "\n -> ");
        command = console.nextLine();

        if(command.equals("yes")) {
            config.clearDocument();
            System.out.println(ANSI_BLUE + "Operation Successful." + ANSI_RESET + "\n");
        } else {
            System.out.println(ANSI_RED + "Operation Stopped. No Changes Made" + ANSI_RESET + "\n");
        }
    }

    private void updateData() {
        itemNames = new ArrayList<String>();
        items = new ArrayList<fieldItem>();
        itemNames.addAll(Arrays.asList(config.getNames()));
        items.addAll(Arrays.asList(config.getItems()));
    }
}

