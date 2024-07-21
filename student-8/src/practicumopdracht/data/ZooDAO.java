package practicumopdracht.data;

import practicumopdracht.models.Zoo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Zoo data access object that provides functionality to perform CRUD operations on a collection of Zoo objects.
 * @author Huseyin Altunbas
 */
public abstract class ZooDAO implements DAO<Zoo>{

    protected List<Zoo> zoos = new ArrayList<>();

    public Zoo getById(int id) {
        try {
            return zoos.get(id);
        } catch (IndexOutOfBoundsException error) {
            System.err.println("ID doesn't exist");
            return null;
        } catch (Exception error) {
            System.err.println("Something went wrong");
            return null;
        }
    }

    public int getIdFor(Zoo zoo){return zoos.indexOf(zoo);}

    @Override
    public List<Zoo> getAll() {
        return zoos;
    }

    @Override
    public void addOrUpdate(Zoo zoo) {
        if (!zoos.contains(zoo)) {
            zoos.add(zoo);
        }
    }

    @Override
    public void remove(Zoo zoo) {
        zoos.remove(zoo);
    }
    @Override
    public abstract boolean load();
    @Override
    public abstract boolean save();
}
