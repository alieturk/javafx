package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Animal;

import java.io.*;

/**
 * An implementation of AnimalDAO that uses object serialization to read/write data from/to a file.
 * @author Huseyin Altunbas
 */
public class ObjectAnimalDAO extends AnimalDAO {

    private static final String FILE_NAME = "animals.obj";

    @Override
    public boolean save() {
        File file = new File(FILE_NAME);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
             objectOutputStream.writeInt(animals.size());

            for (Animal animal : animals) {
                objectOutputStream.writeObject(animal);
                objectOutputStream.writeInt(MainApplication.getZooDAO().getIdFor(animal.getBelongsTo()));
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean load() {
        try (FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            int size = objectInputStream.readInt();

            for (int i = 0; i < size; i++) {
                Animal animal = (Animal) objectInputStream.readObject();
                int zooID = objectInputStream.readInt();
                animal.setBelongsTo(MainApplication.getZooDAO().getById(zooID));
                addOrUpdate(animal);
            }

            return true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found" + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error reading the file");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found");
            e.printStackTrace();
        }

        return false;
    }
}
