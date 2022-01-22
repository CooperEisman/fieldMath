import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;


public class fieldConfig {
    private static File myFile = new File("src/config.txt");
    private Scanner fileReader;
    private Scanner stringReader = new Scanner("");

    FileWriter fileWriter;

    private ArrayList<fieldItem> items = new ArrayList<fieldItem>();
    private ArrayList<String> names = new ArrayList<String>();

    private ArrayList<String> args; //Temporary for storage;
    private double[] dblArgs; //Temporary for Storage

    //Constructor
    public fieldConfig() {
        configureReader();
        load();
    }

    //Configures the file reader
    private void configureReader() {
        try {
            fileReader = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + myFile);
        }
    }

    //Configures the file Writer
    private void configureWriter() {
        try {
            fileWriter = new FileWriter(myFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //Clears the configuration document
    public void clearDocument() {
        configureWriter();

        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //Loads Data from File
    private void load() {

        items = new ArrayList<fieldItem>();
        names = new ArrayList<String>();

        configureReader();
        int len = 0;

        while(fileReader.hasNextLine()) {
            len++;
            fileReader.nextLine();
        }

        configureReader();

        while(fileReader.hasNextLine()) {
            String newLine = fileReader.nextLine().toLowerCase(Locale.ROOT);
            stringReader = new Scanner(newLine).useDelimiter(" ");
            args = new ArrayList<String>();

            while(stringReader.hasNext()) {
                args.add(stringReader.next());
            }

            names.add(args.get(1));

            if(args.get(0).equals("vector")) {
                dblArgs = new double[args.size() - 2];

                for(int i = 2; i < args.size(); i++) {
                    dblArgs[i - 2] = Double.parseDouble(args.get(i));
                }

                items.add(new fieldVector(dblArgs));
            } else if(args.get(0).equals("point")) {
                dblArgs = new double[args.size() - 2];

                for (int i = 2; i < args.size(); i++) {
                    dblArgs[i - 2] = Double.parseDouble(args.get(i));
                }

                items.add(new fieldPoint(dblArgs));
            }
        }
        fileReader.close();
    }

    //Saves Data to File
    private void save() {
        clearDocument();
        configureWriter();

        try {
            for(int i = 0; i < items.size(); i++) {
                fileWriter.write(items.get(i).type() + " " + names.get(i) + items.get(i).args() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //Returns all items in an Array
    public fieldItem[] getItems() {
        load();

        fieldItem[] out = new fieldItem[items.size()];

        for (int i = 0; i < items.size(); i++) {
            out[i] = items.get(i);
        }

        return out;
    }

    //Returns all names in an Array
    public String[] getNames() {
        load();

        String[] out = new String[names.size()];

        for (int i = 0; i < items.size(); i++) {
                out[i] = names.get(i);
        }

        return out;
    }

    //Adds a value, returns true if value is instead overridden
    public boolean addItem(fieldItem item, String name) {
        load();
        name = name.toLowerCase(Locale.ROOT);
        if (names.contains(name)) {
            items.set(names.indexOf(name),item);
            save();
            return true;
        }

        names.add(name);
        items.add(item);
        save();
        return false;
    }

    //Removes a value, returns true if value is removed successfully
    public boolean removeItem(String name) {
        name = name.toLowerCase(Locale.ROOT);
        load();
        if (names.contains(name)) {
            items.remove(names.indexOf(name));
            names.remove(names.indexOf(name));
            save();
            return true;
        }
        return false;
    }

    public String toString() {
        String out = "";
        for(int i = 0;i < items.size(); i++) {
            out += items.get(i).type() + " '" + names.get(i) + "': " + items.get(i) + "\n";
        }
        return out;
    }

    public String toString(String filter) {

        String out = "";
        for(int i = 0;i < items.size(); i++) {
            if(filter.equals(items.get(i).type())) {
                out += items.get(i).type() + " '" + names.get(i) + "': " + items.get(i) + "\n";
            }
        }
        return out;
    }
}
