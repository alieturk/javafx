package practicumopdracht.data;

import practicumopdracht.models.Zoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * The TextZooDAO class is responsible for loading and saving Zoo data to/from a text file.
 * @author Huseyin Altunbas
 */
public class TextZooDAO extends ZooDAO {


    private static final String FILE = "zoos.txt";

    @Override
    public boolean save() {
        File file = new File(FILE);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(zoos.size());
            for (Zoo zoo : zoos) {
                printWriter.println(zoo.getName());
                printWriter.println(zoo.getLocation());
                printWriter.println(zoo.getStreet());
                printWriter.println(zoo.getHouseNumber());
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + FILE);
        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILE);

        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid data format in file 1: " + FILE);
                return false;
            }

            int numberOfZoos = scanner.nextInt();

            for (int i = 0; i < numberOfZoos; i++) {
                scanner.nextLine(); // consume newline character

                if (!scanner.hasNextLine()) {
                    System.out.println("Invalid data format in file " + (i+2) + ": " + FILE);
                    return false;
                }
                String name = scanner.nextLine();

                if (!scanner.hasNextLine()) {
                    System.out.println("Invalid data format in file " + (i+2) + ": " + FILE);
                    return false;
                }
                String location = scanner.nextLine();

                if (!scanner.hasNextLine()) {
                    System.out.println("Invalid data format in file " + (i+2) + ": " + FILE);
                    return false;
                }
                String street = scanner.nextLine();

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid data format in file " + (i+2) + ": " + FILE);
                    return false;
                }
                int houseNumber = scanner.nextInt();

                Zoo zoo = new Zoo(name, location, street, houseNumber);
                addOrUpdate(zoo);
            }

            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE);
        } catch (Exception ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
            ex.printStackTrace();
        }

        return false;
    }
}

