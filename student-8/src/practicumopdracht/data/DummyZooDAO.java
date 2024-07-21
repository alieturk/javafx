package practicumopdracht.data;

import practicumopdracht.models.Zoo;

/**
 * Dummy Zoo DAO class
 * @author Huseyin Altunbas
 */
public class DummyZooDAO extends ZooDAO{
    @Override
    public boolean load() {
        addOrUpdate(new Zoo("Artis", "Amsterdam", "Plantage Kerklaan", 9));
        addOrUpdate(new Zoo("UtrechZoo", "Utrecht", "Plantage Kerklaan", 38));
        addOrUpdate(new Zoo("AmsterdamZoo", "Amsterdam", "Plantage Kerklaan", 72));

        return true;
    }

    @Override
    public boolean save() {
        return false;
    }
}
