package practicumopdracht.data;

import practicumopdracht.models.Zoo;

import java.io.*;

/**
 * The BinaryZooDAO class provides functionality to save and load zoo data from a binary file.
 * @author Huseyin Altunbas
 */
public class BinaryZooDAO extends ZooDAO{

    private static final String FILE_NAME = "zoo.dat";

    @Override
    public boolean save() {
        File file = new File(FILE_NAME);

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(zoos.size());

            for (Zoo zoo : zoos) {
                dataOutputStream.writeUTF(zoo.getName());
                dataOutputStream.writeUTF(zoo.getLocation());
                dataOutputStream.writeUTF(zoo.getStreet());
                dataOutputStream.writeInt(zoo.getHouseNumber());
            }
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (IOException ex) {
            System.err.println("There is something unexpected");
            ex.printStackTrace();
        }
        return false;
    }

    public boolean load() {
        File file = new File(FILE_NAME);

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int numberOfZoos = dataInputStream.readInt();

            for (int i = 0; i < numberOfZoos; i++) {
                String name = dataInputStream.readUTF();
                String location = dataInputStream.readUTF();
                String street = dataInputStream.readUTF();
                int houseNumber = dataInputStream.readInt();
                Zoo zoo = new Zoo(name, location, street, houseNumber);
                addOrUpdate(zoo);
            }
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException ex) {
            System.err.println("There is something unexpected happened");
            ex.printStackTrace();
        }

        return false;
    }
}
