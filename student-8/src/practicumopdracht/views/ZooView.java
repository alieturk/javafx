package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import practicumopdracht.models.Zoo;

/**
 * Master View class that subclass of abstract view class
 * @author Huseyin Altunbas
 */
public class ZooView extends View{


    private TextArea nameInput;

    private TextField locationInput;

    private TextField streetInput;

    private TextField houseNumberInput;

    private Button addButton;

    private Button newButton;

    private Button deleteButton;

    private Button switchButton;

    private MenuItem loadMenuItem;

    private MenuItem saveMenuItem;

    private MenuItem exitMenuItem;

    private MenuItem descendingMenuItem;

    private MenuItem ascendingMenuItem;

    private ListView<Zoo> listView;

    @Override
    protected Parent initializeView() {
        loadMenuItem = new MenuItem("Load");

        saveMenuItem = new MenuItem("Save");

        exitMenuItem = new MenuItem("Exit");

        ascendingMenuItem = new MenuItem("ASC");

        descendingMenuItem = new MenuItem("DES");

        Label nameLabel = new Label("Name:");
        nameLabel.setPadding(new Insets(0, 49, 0, 0));
        nameInput = new TextArea();
        nameInput.setPrefWidth(400);

        Label locationLabel = new Label("Location:");
        locationLabel.setPadding(new Insets(0, 38, 0, 0));
        locationInput = new TextField();
        locationInput.setPrefWidth(nameInput.getPrefWidth());

        Label streetLabel = new Label("Street:");
        streetLabel.setPadding(new Insets(0, 51, 0, 0));
        streetInput = new TextField();
        streetInput.setPrefWidth(nameInput.getPrefWidth());

        Label houseNumberLabel = new Label("House number:");
        houseNumberLabel.setPadding(new Insets(0, 5, 0, 0));
        houseNumberInput = new TextField();
        houseNumberInput.setPrefWidth(nameInput.getPrefWidth());



        listView = new ListView<>();

        addButton = new Button("Add");
        addButton.setMaxWidth(Double.MAX_VALUE);

        GridPane buttonPane = new GridPane();
        buttonPane.setHgap(10);

        newButton = new Button("New");
        deleteButton = new Button("Delete");
        switchButton = new Button("Go back to the detail");

        Menu fileMenu = new Menu("Menu");
        fileMenu.getItems().addAll(loadMenuItem, saveMenuItem, exitMenuItem);

        Menu sortMenu = new Menu("Sorting");
        sortMenu.getItems().addAll(descendingMenuItem, ascendingMenuItem);

        MenuBar menuBar = new MenuBar(fileMenu, sortMenu);



        buttonPane.addRow(0, newButton, deleteButton, switchButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(
                borderPane,
                new HBox(nameLabel, nameInput),
                new HBox(locationLabel, locationInput),
                new HBox(streetLabel, streetInput),
                new HBox(houseNumberLabel, houseNumberInput),
                addButton,
                listView,
                buttonPane
        );

        return vBox;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getNewButton() {
        return newButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getSwitchButton() {
        return switchButton;
    }

    public TextArea getNameInput() {
        return nameInput;
    }

    public TextField getLocationInput() {
        return locationInput;
    }

    public TextField getStreetInput() {
        return streetInput;
    }

    public TextField getHouseNumberInput() {
        return houseNumberInput;
    }

    public ListView<Zoo> getListView() {
        return listView;
    }

    public MenuItem getLoadMenuItem() {
        return loadMenuItem;
    }

    public MenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public MenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public MenuItem getDescending() {return descendingMenuItem;}

    public MenuItem getAscending() {return ascendingMenuItem;}
}
