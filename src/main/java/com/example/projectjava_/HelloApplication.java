package com.example.projectjava_;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {


        // creation of a background image
        Image fondImage = new Image("fond1.png");
        ImageView fondImageView = new ImageView(fondImage);
        fondImageView.setX(0);
        fondImageView.setY(0);

        // creation of the pond ecosystem
        Ecosystem pond = new Ecosystem();



        // creation of a group to contain the imageView
        Group root = new Group();
        root.getChildren().add(fondImageView);
        //add imageView of the plants
        for (Plant plant : pond.getTabPlant()) {
            root.getChildren().add(plant.getImView());
        }
        //add imageView of the frogs
        for (Frog frog : pond.getTabFrog()) {
            root.getChildren().add(frog.getImView());
        }

        //add imageView of the flies
        for (Fly fly : pond.getTabFly()) {
            root.getChildren().add(fly.getImView());
        }
        // Create a button for quitting the application
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            pond.getTimer().cancel(); // Stop the timer
            primaryStage.close(); // Close the application window
        });



        // Create a VBox to hold the button and label at the bottom

       // vBox.setAlignment(Pos.BOTTOM_CENTER); // Align to the bottom center of the VBox
        quitButton.setTranslateY(730);
        // Add the VBox to the root group
        root.getChildren().add(quitButton);
        // Création de la scène
        Scene scene = new Scene(root, 1280, 760);

        // Configuration de la scène principale
        primaryStage.setTitle("Display of the pond ecosystem");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
