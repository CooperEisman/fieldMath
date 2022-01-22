import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Vector;


public class fieldConfig {
    private static File myFile = new File("src/config.txt");
    private Scanner fileReader;
    private Scanner stringReader = new Scanner("");
    private ArrayList<fieldItem> items = new ArrayList<fieldItem>();
    private ArrayList<String> names = new ArrayList<String>();

    private ArrayList<String> args; //Temporary for storage;
    private double[] dblArgs;

    public fieldConfig() {
        configureReader();
        load();
    }

    private void configureReader() {
        try {
            fileReader = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + myFile);
        }
    }

    private void load() {
        int len = 0;

        while(fileReader.hasNextLine()) {
            len++;
            fileReader.nextLine();
        }

        configureReader();

        while(fileReader.hasNextLine()) {
            String newLine = fileReader.nextLine();
            stringReader = new Scanner(newLine).useDelimiter(" ");
            args = new ArrayList<String>();

            while(stringReader.hasNext()) {
                args.add(stringReader.next());
            }

            names.add(args.get(0));

            if(args.get(1).equals("Vector")) {
                dblArgs = new double[args.size() - 2];

                for(int i = 2; i < args.size(); i++) {
                    dblArgs[i - 2] = Double.parseDouble(args.get(i));
                }

                items.add(new fieldVector(dblArgs));
            } else if(args.get(1).equals("Point")) {
                dblArgs = new double[args.size() - 2];

                for (int i = 2; i < args.size(); i++) {
                    dblArgs[i - 2] = Double.parseDouble(args.get(i));
                }

                items.add(new fieldPoint(dblArgs));
            }
        }
        fileReader.close();
    }
}
