package practicumopdracht.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.comparators.NameComparator;
import practicumopdracht.data.AnimalDAO;
import practicumopdracht.data.ZooDAO;
import practicumopdracht.models.Zoo;
import practicumopdracht.views.View;
import practicumopdracht.views.ZooView;


import java.util.ArrayList;
import java.util.Optional;

import static practicumopdracht.MainApplication.*;

/**
 * Master Controller of this project. It helps with adding, retrieving and updating the data. It is a subclass of the abstract Contoller Class.
 * @author Huseyin Altunbas
 */

public class ZooController extends Controller {

    private ZooView view;
    private Zoo zoo;
    private ArrayList<String> errors = new ArrayList<>();
    private ZooDAO zooDAO;
    private AnimalDAO animalDAO;
    private ObservableList<Zoo> zooObservableList;
    private Zoo selectedZoo;

    public ZooController(final Zoo SELECTED_ZOO) {
        //Initializing the Zoo view, Zoo DAO and Animal DAO class
        view = new ZooView();
        zooDAO = getZooDAO();
        animalDAO = getAnimalDAO();

        //Selected Master Model item which we are going to send it to detail controller.
        selectedZoo = SELECTED_ZOO;
        showZooItemsInListview();

        //Disabling all button before selection of the master object in the list
        view.getNewButton().setDisable(true);
        view.getDeleteButton().setDisable(true);
        view.getSwitchButton().setDisable(true);
        //When selected item in listview changes setItem Method will be triggered
        this.view.getListView().getSelectionModel().selectedItemProperty().addListener(((object, oldValue, newValue) -> setItem(newValue)));
        // Setting event handler on buttons
        view.getAddButton().setOnAction(event -> handleAddButton());
        view.getNewButton().setOnAction(event -> handleNewButton());
        view.getDeleteButton().setOnAction(event -> handleDeleteButton());
        view.getSwitchButton().setOnAction(event -> handleSwitchButton());
        view.getSaveMenuItem().setOnAction(event -> handleSaveButton());
        view.getExitMenuItem().setOnAction(event -> handleExitButton());
        view.getLoadMenuItem().setOnAction(event -> handleLoadButton());
        view.getAscending().setOnAction(event -> handleSortAToZ());
        view.getDescending().setOnAction(event -> handleSortZToA());
    }

    /**
     * Getting all the master items and setting in the list view. Also sorting the list view
     */
    private void showZooItemsInListview() {
        zooObservableList = FXCollections.observableArrayList(getZooDAO().getAll());
        view.getListView().setItems(zooObservableList);
        zooObservableList.sort(new NameComparator(true));
    }

    /**
     * Loading all saved items from both DAO's
     */
    private void handleLoadButton() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Load Data");
        confirmAlert.setHeaderText("Load data from file");
        confirmAlert.setContentText("Are you sure you want to load data? This will overwrite the current data.");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            zooDAO.load();
            animalDAO.load();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved Data");
            alert.setContentText("Data is successfully loaded");
            alert.showAndWait();
            showZooItemsInListview();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Saved Data");
            alert.setContentText("Data could not be loaded");
            alert.showAndWait();
        }
    }

    /**
     * Exiting the platform. If the user wishes data's can be saved before exiting
     */
    private void handleExitButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save the data before you leave the platform?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            handleSaveButton();
            Platform.exit();
        } else if (alert.getResult() == ButtonType.NO) {
            Platform.exit();
        }

    }

    /**
     * Saving the data in the chose type of DAO
     */
    private void handleSaveButton() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Save Data");
        confirmAlert.setHeaderText("Save data to file");
        confirmAlert.setContentText("Are you sure you want to save data?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            zooDAO.save();
            animalDAO.save();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved Data");
            alert.setContentText("Data is successfully saved");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Saved Data");
            alert.setContentText("Data could not be saved");
            alert.showAndWait();
        }
    }


    /**
     * Setting the item in the input field
     * @param zoo
     */
    private void setItem(Zoo zoo) {
        if (zoo != null) {
            selectedZoo = zoo;
            this.view.getNameInput().setText((zoo.getName()));
            this.view.getLocationInput().setText(zoo.getLocation());
            this.view.getStreetInput().setText(zoo.getStreet());
            this.view.getHouseNumberInput().setText(Integer.toString(zoo.getHouseNumber()));

            view.getNewButton().setDisable(false);
            view.getDeleteButton().setDisable(false);
            view.getSwitchButton().setDisable(false);
        } else {
            selectedZoo = null;
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
        errors.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        String name = view.getNameInput().getText();
        String location = view.getLocationInput().getText();
        String street = view.getStreetInput().getText();
        String number = view.getHouseNumberInput().getText();

        if (name.trim().isEmpty()) {
            errors.add("Name of input cannot be empty\n");
        } else if (!(name.trim().matches("^[a-zA-Z]*$"))) {
            errors.add("Name of input cannot contain numeric values\n");
        }
        if (location.trim().isEmpty()) {
            errors.add("Location of input cannot be empty\n");
        } else if (!(location.trim().matches("^[a-zA-Z]*$"))) {
            errors.add("Location of input cannot contain numeric values\n");
        }
        if (street.trim().isEmpty()) {
            errors.add("Street input cannot be empty\n");
        } else if (!(street.trim().matches("^[a-zA-Z]*$"))) {
            errors.add("Street input cannot contain numeric values\n");
        }
        if (number.trim().isEmpty()) {
            errors.add("Street input cannot be empty\n");
        } else if (!(number.trim().matches("[0-9]+"))) {
            errors.add("Street input cannot contain numeric values\n");
        }

        alert.setTitle("Adding button");
        alert.setHeaderText("Adding button is pressed");
        alert.setContentText(String.valueOf(errors));

        if (errors.size() > 0) {
            alert.showAndWait();
        }

        zoo = view.getListView().getSelectionModel().getSelectedItem();
        int houseNumber = Integer.parseInt(view.getHouseNumberInput().getText());


        if (errors.isEmpty()) {
            int selectedIndex = view.getListView().getSelectionModel().getSelectedIndex();
            if (selectedIndex < 0) {
                Zoo newZoo = new Zoo(view.getNameInput().getText(), view.getLocationInput().getText(), view.getStreetInput().getText(), houseNumber);
                zooDAO.addOrUpdate(newZoo);

            } else {
                try {
                    zoo.setName(view.getNameInput().getText());
                    zoo.setLocation(view.getLocationInput().getText());
                    zoo.setStreet(view.getStreetInput().getText());
                    zoo.setHouseNumber(houseNumber);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            showZooItemsInListview();
        }
    }

    /**
     * Clearing the input fields so that new item can be added
     */
    private void handleNewButton() {
        view.getListView().getSelectionModel().clearSelection();

        view.getNameInput().setText("");
        view.getLocationInput().setText("");
        view.getStreetInput().setText("");
        view.getHouseNumberInput().setText("");

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
        selectedZoo = view.getListView().getSelectionModel().getSelectedItem();
        if (selectedZoo != null) {
            alert.setTitle("The button is pressed");
            alert.setHeaderText("You just pressed the Delete button");
            alert.showAndWait();

            zooDAO.remove(selectedZoo);
            showZooItemsInListview();
            view.getNameInput().setText("");
            view.getLocationInput().setText("");
            view.getStreetInput().setText("");
            view.getHouseNumberInput().setText("");
        }
    }
    private void handleSortZToA() {
        zooObservableList.sort(new NameComparator(false));
    }

    private void handleSortAToZ() {zooObservableList.sort(new NameComparator(true));}

    private void handleSwitchButton() {
        switchController(new AnimalController(selectedZoo));
    }

    @Override
    public View getView() {
        return view;
    }
}
