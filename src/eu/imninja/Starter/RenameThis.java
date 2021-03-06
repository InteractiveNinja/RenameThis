package eu.imninja.Starter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RenameThis extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("eu/imninja/GUI/Design/main.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("eu/imninja/Images/icon.png").toExternalForm()));
        primaryStage.setTitle("Rename This!");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
