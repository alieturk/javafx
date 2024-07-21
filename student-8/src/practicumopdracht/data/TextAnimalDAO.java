package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Animal;
import practicumopdracht.models.Zoo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The TextAnimalDAO class is responsible for loading and saving Animal data to/from a text file.
 * @author Huseyin Altunbas
 */
public class TextAnimalDAO extends AnimalDAO {

    private ZooDAO zooDAO = MainApplication.getZooDAO();

    private static final String FILE = "animals.txt";



    @Override
    public boolean load() {
        File file = new File(FILE);
        try (Scanner scanner = new Scanner(file)) {
            int numberOfAnimal = scanner.nextInt();

            scanner.nextLine();

            for (int i = 0; i < numberOfAnimal; i++) {
                String type = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthday = LocalDate.parse(scanner.nextLine(), formatter);
                double length = scanner.nextDouble();
                double weight = scanner.nextDouble();
                boolean isCarnivorous = scanner.nextBoolean();
                scanner.nextLine();
                int zooId = scanner.nextInt();
                System.out.println(zooId);
                scanner.nextLine();
                Zoo zoo = zooDAO.getById(zooId);
                System.out.println(zoo);

                Animal animal = new Animal(type, birthday, length, weight, isCarnivorous);
                animal.setBelongsTo(zoo);
                addOrUpdate(animal);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage() + e.getCause());
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean save() {
        File file = new File(FILE);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(animals.size());

            for (Animal animal : animals) {
                printWriter.println(animal.getType());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                printWriter.println(animal.getBirthday().format(formatter));
                printWriter.println(animal.getLength());
                printWriter.println(animal.getWeight());
                printWriter.println(animal.isCarnivorous());
                int zoo = zooDAO.getIdFor(animal.getBelongsTo());
                printWriter.println(zoo);
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE);
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
        return false;
    }
}
