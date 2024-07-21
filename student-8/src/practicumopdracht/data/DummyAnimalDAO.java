package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Animal;

import java.time.LocalDate;

/**
 * Dummy DAO class for Animal class
 * @author Huseyin Altunbas
 */
public class DummyAnimalDAO extends AnimalDAO{

    @Override
    public boolean load() {
        Animal animal = new Animal("Tiger", LocalDate.of(2001 ,2, 3), 3.4, 4.3,true);
        Animal animal2 = new Animal("Zebra", LocalDate.of(2001 ,2, 3), 3.4, 4.3,true);
        Animal animal3 = new Animal("Lion", LocalDate.of(2001 ,2, 3), 3.4, 4.3,true);
        Animal animal4 = new Animal("Monkey", LocalDate.of(2001 ,2, 3), 3.4, 4.3,true);
        animal.setBelongsTo(MainApplication.getZooDAO().getById(0));
        animal2.setBelongsTo(MainApplication.getZooDAO().getById(0));
        animal3.setBelongsTo(MainApplication.getZooDAO().getById(1));
        animal4.setBelongsTo(MainApplication.getZooDAO().getById(2));


        addOrUpdate(animal);
        addOrUpdate(animal2);
        addOrUpdate(animal3);
        addOrUpdate(animal4);

        return true;
    }

    @Override
    public boolean save() {
        return false;
    }
}
