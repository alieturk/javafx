    package practicumopdracht.views;

    import javafx.geometry.Insets;
    import javafx.scene.Parent;
    import javafx.scene.control.*;
    import javafx.scene.layout.*;
    import practicumopdracht.models.Animal;
    import practicumopdracht.models.Zoo;

    /**
     * Detail View class that subclass of abstract view class
     * @author Huseyin Altunbas
     */
    public class AnimalView extends View{
        private TextArea typeInput;
        private TextField lengthInput;
        private TextField weightInput;
        private DatePicker birthdayInput;
        private ComboBox<Zoo> comboBox;
        private CheckBox isCarnivorousInput;
        private ListView<Animal> listView;
        private Button addButton;
        private Button newButton;
        private Button deleteButton;
        private Button switchButton;
        private RadioButton sortFromAToZ;
        private RadioButton sortFromZToA;
        private RadioButton sortByWeightASC;
        private RadioButton sortByWeightDES;
        private ToggleGroup toggleGroup;


        @Override
        protected Parent initializeView() {
            Label textAreaLabel = new Label("Type:");
            textAreaLabel.setPadding(new Insets(0, 35, 0, 0));
            typeInput = new TextArea();
            typeInput.setPrefWidth(400);

            Label textField1Label = new Label("Length:");
            textField1Label.setPadding(new Insets(0, 24, 0, 0));
            lengthInput = new TextField();
            lengthInput.setPrefWidth(400);

            Label textField2Label = new Label("Weight:");
            textField2Label.setPadding(new Insets(0, 26, 0, 0));
            weightInput = new TextField();
            weightInput.setPrefWidth(400);

            Label datePickerLabel = new Label("Birthday:");
            datePickerLabel.setPadding(new Insets(0, 20, 0, 0));
            birthdayInput = new DatePicker();
            birthdayInput.setPrefWidth(400);

            Label comboBoxLabel = new Label("Combo Box:");
            comboBoxLabel.setPadding(new Insets(0, 2, 0, 0));
            comboBox = new ComboBox<>();
            comboBox.setPrefWidth(400);

            Label checkBoxLabel = new Label("Carnivorous:");
            checkBoxLabel.setPadding(new Insets(0, 4, 0, 0));
            isCarnivorousInput = new CheckBox();

            addButton = new Button("Add");
            addButton.setMaxWidth(Double.MAX_VALUE);

            listView = new ListView<>();
            listView.setPrefWidth(400);

            toggleGroup = new ToggleGroup();
            sortFromAToZ = new RadioButton("A-Z");
            sortFromZToA = new RadioButton("Z-A");
            sortByWeightASC = new RadioButton("Weight ASC");
            sortByWeightDES = new RadioButton("Weight DES");
            sortFromAToZ.setToggleGroup(toggleGroup);
            sortFromZToA.setToggleGroup(toggleGroup);
            sortByWeightASC.setToggleGroup(toggleGroup);
            sortByWeightDES.setToggleGroup(toggleGroup);

            newButton = new Button("New");
            deleteButton = new Button("Delete");
            switchButton = new Button("Go back to the overview");

            GridPane buttonPane = new GridPane();
            buttonPane.setHgap(10);
            buttonPane.add(newButton, 0, 0);
            buttonPane.add(deleteButton, 1, 0);
            buttonPane.add(switchButton, 2, 0);

            VBox vbox = new VBox();
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(10);
            vbox.getChildren().addAll(
                    new HBox(textAreaLabel, typeInput),
                    new HBox(textField1Label, lengthInput),
                    new HBox(textField2Label, weightInput),
                    new HBox(datePickerLabel, birthdayInput),
                    new HBox(comboBoxLabel, comboBox),
                    new HBox(checkBoxLabel, isCarnivorousInput),
                    addButton,
                    listView,
                    new HBox(sortFromAToZ, sortFromZToA, sortByWeightASC, sortByWeightDES),
                    buttonPane
            );

            return vbox;
        }

        public TextArea getTypeInput() {
            return typeInput;
        }

        public TextField getLengthInput() {
            return lengthInput;
        }

        public TextField getWeightInput() {
            return weightInput;
        }

        public DatePicker getBirthdayInput() {
            return birthdayInput;
        }

        public ComboBox<Zoo> getComboBox() {
            return comboBox;
        }

        public CheckBox getIsCarnivorousInput() {
            return isCarnivorousInput;
        }

        public ListView<Animal> getListView() {
            return listView;
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

        public RadioButton getSortFromAToZ() {
            return sortFromAToZ;
        }

        public RadioButton getSortFromZToA() {
            return sortFromZToA;
        }

        public RadioButton getSortByWeightASC() {
            return sortByWeightASC;
        }

        public RadioButton getSortByWeightDES() {
            return sortByWeightDES;
        }
    }
