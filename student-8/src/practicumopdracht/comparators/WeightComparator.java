package practicumopdracht.comparators;

import practicumopdracht.models.Animal;

import java.util.Comparator;

/**
 * Class that sort all Animal objects in ascending and descending order by their weights.
 * @author Huseyin Altunbas
 */
public class WeightComparator implements Comparator<Animal> {

    private boolean ascending;

    public WeightComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Animal o1, Animal o2) {
        if (ascending) {
            return Double.compare(o1.getWeight(), o2.getWeight());
        }
        return Double.compare(o2.getWeight(), o1.getWeight());
    }
}
