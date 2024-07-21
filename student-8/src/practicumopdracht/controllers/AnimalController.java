package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import practicumopdracht.comparators.TypeAndLengthComparator;
import practicumopdracht.comparators.WeightComparator;
import practicumopdracht.data.AnimalDAO;
import practicumopdracht.data.ZooDAO;
import practicumopdracht.models.Animal;
import practicumopdracht.models.Zoo;
import practicumopdracht.views.AnimalView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.ArrayList;

import static practicumopdracht.MainApplication.*;

/**
 * The AnimalController class is responsible for controlling and communicating with the AnimalView and the AnimalDAO.
 * It has functionality to add, delete, sort and switch animals between zoos.
 * @author Huseyin Altunbas
 */

public class AnimalController extends Controller {
    private AnimalView view;
    private ArrayList<String> errorsAnimal = new ArrayList<>();
    private ObservableList<Animal> observableListAnimals;
    private Zoo selectedZoo;
    private Animal animal;
    private AnimalDAO animalDAO;
    private ZooDAO zooDAO;
    private ObservableList<Zoo> observableListZoos;
    private String replacer = "";

    public AnimalController(final Zoo SELECTED_ZOO) {
        view = new AnimalView();
        selectedZoo = SELECTED_ZOO;
        animalDAO = getAnimalDAO();
        zooDAO = getZooDAO();
        //Set listeners for buttons and the combobox
        view.getAddButton().setOnAction(event -> handleAddButton());
        view.getNewButton().setOnAction(event -> handleNewButton());
        view.getDeleteButton().setOnAction(event -> handleDeleteButton());
        view.getSwitchButton().setOnAction(event -> handleSwitchButton());
        view.getSortByWeightASC().setOnAction(event -> handleSortByWeightASC());
        view.getSortByWeightDES().setOnAction(event -> handleSortByWeightDES());
        view.getSortFromAToZ().setOnAction(event -> handleSortByNameASC());
        view.getSortFromZToA().setOnAction(event -> handleSortByNameDES());

        // Setting all items master objects in observableList
        observableListZoos = FXCollections.observableArrayList(zooDAO.getAll());
        showItemsInListview();
        //Setting observableList in the combobox
        view.getComboBox().setItems(observableListZoos);
        view.getComboBox().getSelectionModel().select(selectedZoo);
        view.getListView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setItem(newValue));

        view.getComboBox().getSelectionModel().selectedItemProperty().addListener((
                observableValue, oldBelongsTo, newBelongsTo) -> {
            if (newBelongsTo != null) {
                selectedZoo = newBelongsTo;
                showItemsInListview();
                view.getNewButton().setDisable(true);
                view.getDeleteButton().setDisable(true);
            }
        });
    }

    /**
     * Shows the lists of animals in the listview
     */
    private void showItemsInListview() {
        observableListAnimals = FXCollections.observableArrayList(animalDAO.getAllFor(selectedZoo));
        view.getListView().setItems(observableListAnimals);
    }
    /**
     * Setting the item in the input field
     * @param animal
     */
    private void setItem(Animal animal) {
        if (animal != null) {
            view.getTypeInput().setText(animal.getType());
            view.getBirthdayInput().setValue(LocalDate.parse(String.valueOf(animal.getBirthday())));
            view.getLengthInput().setText(String.valueOf(animal.getLength()));
            view.getWeightInput().setText(String.valueOf(animal.getWeight()));
            view.getIsCarnivorousInput().setSelected(animal.isCarnivorous());
            view.getNewButton().setDisable(false);
            view.getDeleteButton().setDisable(false);
            view.getSwitchButton().setDisable(false);
        } else {
            view.getNewButton().setDisable(true);
            view.getDeleteButton().setDisable(true);
            view.getSwitchButton().setDisable(true);
        }
    }
    /**
     * The method reads user input from text fields,
     * validates the input, and updates the accordingly or adds new master object.
     */
    private void handleAddButton() {
        errorsAnimal.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String typeInput = view.getTypeInput().getText();
        String lengthInput = String.valueOf((view.getLengthInput().getText()));
        String weigthInput = String.valueOf((view.getWeightInput().getText()));
        String birthDayInput = String.valueOf(view.getBirthdayInput().getValue());


        if (typeInput.trim().isEmpty()) {
            errorsAnimal.add("Type of input cannot be empty\n");
        } else if (!(typeInput.trim().matches("^[a-zA-Z]*$"))) {
            errorsAnimal.add("Type of input cannot contain numeric values\n");
        }
        if (lengthInput.trim().isEmpty()) {
            errorsAnimal.add("Length of input cannot be empty\n");
        } else if (lengthInput.trim().matches("^[a-zA-Z]*$")) {
            errorsAnimal.add("Length of input cannot contain string values\n");
        }
        if (weigthInput.trim().isEmpty()) {
            errorsAnimal.add("Weigth input cannot be empty\n");
        } else if (weigthInput.trim().matches("^[a-zA-Z]*$")) {
            errorsAnimal.add("Weigth input cannot contain string values\n");
        }
        if (birthDayInput.trim().isEmpty()) {
            errorsAnimal.add("Birthday  input cannot be empty\n");
        } else if (!(birthDayInput.trim().matches("\\d{4}-\\d{2}-\\d{2}"))) {
            errorsAnimal.add("Birthday input cannot contain numeric or string values values\n");
        }
        alert.setTitle("Adding button");
        alert.setHeaderText("Adding button is pressed");
        alert.setContentText(String.valueOf(errorsAnimal));

        if (errorsAnimal.size() > 0) {
            alert.showAndWait();
        }

        double length = Double.parseDouble(view.getLengthInput().getText());
        double weigth = Double.parseDouble(view.getWeightInput().getText());

        animal = view.getListView().getSelectionModel().getSelectedItem();
        if (errorsAnimal.isEmpty()) {
            int selectedIndex = view.getListView().getSelectionModel().getSelectedIndex();
            if (selectedIndex < 0) {

                Animal newAnimal = new Animal(view.getTypeInput().getText(), view.getBirthdayInput().getValue(), length, weigth,
                        view.getIsCarnivorousInput().isSelected());

                newAnimal.setBelongsTo(selectedZoo);

                animalDAO.addOrUpdate(newAnimal);

                alert.setTitle("New Animal");
                alert.setContentText("New animal is created");
               alert.showAndWait();
            } else {
                animal.setType(view.getTypeInput().getText());
                animal.setLength(Double.parseDouble(view.getLengthInput().getText()));
                animal.setWeight(Double.parseDouble(view.getWeightInput().getText()));
                animal.setBirthday(view.getBirthdayInput().getValue());
                animal.setCarnivorous(view.getIsCarnivorousInput().isSelected());
                alert.setTitle("Animal update");
                alert.setContentText("Animal has been updated");
            }
            showItemsInListview();
        }

    }
    /**
     * Clearing the input fields so that new item can be added
     */
    private void handleNewButton() {
        view.getListView().getSelectionModel().clearSelection();

        view.getTypeInput().setText(replacer);
        view.getLengthInput().setText(replacer);
        view.getWeightInput().setText(replacer);
        view.getBirthdayInput().setValue(null);
        view.getIsCarnivorousInput().setSelected(false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The button is pressed");
        alert.setHeaderText("You just pressed the New button");
        alert.showAndWait();
    }
    /**
     * Selected item in the list view will be deleted and input fields will be cleared.
     */
    private void handleDeleteButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Animal selectedAnimal = view.getListView().getSelectionModel().getSelectedItem();
        if (selectedAnimal != null) {
            alert.setTitle("The button is pressed");
            alert.setHeaderText("You just pressed the Delete button");
            alert.showAndWait();
            animalDAO.remove(selectedAnimal);

            showItemsInListview();

            view.getTypeInput().setText(replacer);
            view.getBirthdayInput().setValue(null);
            view.getIsCarnivorousInput().setSelected(false);
            view.getLengthInput().setText(replacer);
            view.getWeightInput().setText(replacer);
        }
    }

    private void handleSortByNameDES() {
        observableListAnimals.sort(new TypeAndLengthComparator(false));
    }

    private void handleSortByNameASC() {
        observableListAnimals.sort(new TypeAndLengthComparator(true));
    }

    private void handleSortByWeightDES() {
        observableListAnimals.sort(new WeightComparator(false));
    }

    private void handleSortByWeightASC() {
        observableListAnimals.sort(new WeightComparator(true));
    }

    private void handleSwitchButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The button is pressed");
        alert.setHeaderText("You just pressed the Switch button");
        alert.showAndWait();
        switchController(new ZooController(null));
    }

    @Override
    public View getView() {
        return view;
    }
}
