package practicumopdracht.comparators;

import practicumopdracht.models.Animal;

import java.util.Comparator;
/**
 * Class that sort all Animal objects in ascending and descending order by their types. If types are same then it will be sorted by its lengths
 * @author Huseyin Altunbas
 */
public class TypeAndLengthComparator implements Comparator<Animal> {
    private boolean ascending;

    public TypeAndLengthComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Animal o1, Animal o2) {
        int typeCompare = o1.getType().compareTo(o2.getType());

        if (typeCompare == 0){
            int lengthCompare = Double.compare(o1.getLength(), o2.getLength());
            return ascending ? lengthCompare : -lengthCompare;
        }
          return ascending ? typeCompare : -typeCompare;

    }
}
