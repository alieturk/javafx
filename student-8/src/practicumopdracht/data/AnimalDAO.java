package practicumopdracht.data;

import practicumopdracht.models.Animal;
import practicumopdracht.models.Zoo;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is an Animal data access object that provides functionality to perform CRUD operations on a collection of Animal objects.
 * @author Huseyin Altunbas
 */
public abstract class AnimalDAO implements DAO<Animal>{

    protected List<Animal> animals;

    public AnimalDAO() {
        animals = new ArrayList<>();
    }

    public List<Animal> getAllFor(Zoo zoo) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getBelongsTo().equals(zoo)){
                result.add(animal);
            }
        }
        return result;
    }

    public List<Animal> getAll() {
        return animals;
    }

    @Override
    public void addOrUpdate(Animal animal) {
        if (!animals.contains(animal)) {
            animals.add(animal);
        }
    }

    @Override
    public void remove(Animal animal) {
        animals.remove(animal);
    }


    @Override
    public abstract boolean load();

    @Override
    public abstract boolean save();
}
