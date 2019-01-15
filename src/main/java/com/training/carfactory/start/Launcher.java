package com.training.carfactory.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.training.carfactory.start.GlobalConstants.*;

public class Launcher extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(APP_TITLE);
        initRootLayout();

    }

    private void initRootLayout(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Launcher.class.getResource(APP_FXML_LOCATION));
        try {
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
