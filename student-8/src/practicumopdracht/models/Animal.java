package practicumopdracht.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Serializable animal class
 * @author Huseyin Altunbas
 */
public class Animal implements Serializable {

    private String type;
    private LocalDate birthday;
    private double length;
    private double weight;
    private boolean isCarnivorous;
    private transient Zoo belongsTo;

    public Animal(String type, LocalDate birthday, double length, double weight, boolean isCarnivorous) {
        this.type = type;
        this.birthday = birthday;
        this.length = length;
        this.weight = weight;
        this.isCarnivorous = isCarnivorous;
    }

    public Zoo getBelongsTo() {
        return belongsTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isCarnivorous() {
        return isCarnivorous;
    }

    public void setCarnivorous(boolean carnivorous) {
        isCarnivorous = carnivorous;
    }

    public void setBelongsTo(Zoo belongsTo) {
        this.belongsTo = belongsTo;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", birthday=" + birthday +
                ", length=" + length +
                ", weight=" + weight +
                ", isCarnivorous=" + isCarnivorous +
                '}';
    }
}
