package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.ZooController;
import practicumopdracht.data.*;


public class MainApplication extends Application {
    private static Stage stage;
    private static Scene scene;
    private static ZooDAO zooDAO;
    private static AnimalDAO animalDAO;
    private static String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    @Override

    public void start(Stage stage) {

        stage.setTitle(TITLE);
        stage.setWidth(640);
        stage.setHeight(480);
        MainApplication.stage = stage;
//      zooDAO = new DummyZooDAO();
//      animalDAO = new DummyAnimalDAO();
//        zooDAO = new TextZooDAO();
        zooDAO = new BinaryZooDAO();
//        animalDAO = new TextAnimalDAO();
        animalDAO = new ObjectAnimalDAO();

        switchController(new ZooController(null));
    }
    public static void switchController(Controller controller) {
        scene = new Scene(controller.getView().getRoot());
        stage.setScene(scene);
        stage.show();
    }

    public static ZooDAO getZooDAO() {
        return zooDAO;
    }
    public static AnimalDAO getAnimalDAO() {
        return animalDAO;
    }

}