import java.util.Scanner;
import java.util.ArrayList;

public class fieldRunner {
    private ArrayList<String> itemNames = new ArrayList<String>();
    private ArrayList<fieldItem> items = new ArrayList<fieldItem>();
    private fieldConfig config = new fieldConfig();
    private boolean shouldDie = false;
    private Scanner console = new Scanner(System.in);
    private String command;

    public fieldRunner() {
        command = "";

        while(!shouldDie) {
            printMenu();
            command = console.nextLine();
        }
    }

    public void printMenu() {
        System.out.println("===========================");
        System.out.println("What would you like to do?: ");
        System.out.println(" - Add <Vector/Point>");
        System.out.println(" - Remove <Item Name>");
        System.out.println(" - Edit <Item Name>");
        System.out.println(" - Compute");
        System.out.println("===========================");
    }


}

