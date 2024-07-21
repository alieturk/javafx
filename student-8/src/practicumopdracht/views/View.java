package practicumopdracht.views;

import javafx.scene.Parent;

public abstract class View {

    public Parent root;

    public View(){
        root = initializeView();
    }

    protected abstract Parent initializeView();

    public Parent getRoot(){
        return root;
    }

}
