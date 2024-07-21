package practicumopdracht.comparators;

import practicumopdracht.models.Zoo;

import java.util.Comparator;

/**
 * Class that sort all Zoo objects in ascending and descending order by their names
 * @author Huseyin Altunbas
 */
public class NameComparator implements Comparator<Zoo> {

    private boolean ascending;

    public NameComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Zoo o1, Zoo o2) {
        if (ascending) {
            return o1.getName().compareTo(o2.getName());
        } else {
            return o2.getName().compareTo(o1.getName());
        }
    }
}

