package com.example.shapes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;



public class Shapes extends Application {

    Shape currentNode = null;

    public boolean isValid(int input) {
        if (input >= 3 && input <= 7 ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void start(Stage stage) {
        Label msg = new Label("Please enter a number between 3 and 7 (inclusive)");
        Label errorMsg = new Label();
        errorMsg.setTextFill(Color.RED);
        TextField sides = new TextField();
        Button generate = new Button("Generate Shape");

        HBox inputGroup = new HBox(10);
        inputGroup.getChildren().addAll(sides, generate);


        VBox inputWithLabel = new VBox(10);
        inputWithLabel.getChildren().addAll(msg, inputGroup, errorMsg);


        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20, 10, 10, 20));
        root.getChildren().add(inputWithLabel);


        // Shapes
        Polygon shape3 = new Polygon();
        shape3.getPoints().addAll(
                100.0, 100.0, 100.0, 300.0, 300.0 ,300.0
        );
        shape3.setFill(Color.RED);

        Polygon shape4 = new Polygon();
        shape4.getPoints().addAll(
                100.0, 50.0, 300.0, 50.0, 300.0 ,300.0, 100.0 ,300.0
        );
        shape4.setFill(Color.BLUE);

        Polygon shape5 = new Polygon();
        shape5.getPoints().addAll(
                100.0, 50.0, 200.0, 150.0, 150.0, 250.0, 50.0, 250.0, 0.0, 150.0
        );
        shape5.setFill(Color.RED);

        Polygon shape6 = new Polygon();
        shape6.getPoints().addAll(
                50.0, 50.0, 150.0, 50.0, 200.0, 150.0, 150.0, 250.0, 50.0, 250.0, 0.0, 150.0
        );
        shape6.setFill(Color.BLUE);

        Polygon shape7 = new Polygon();
        shape7.getPoints().addAll(
                25.0, 50.0, 100.0, 0.0, 175.0, 50.0, 200.0, 150.0, 150.0, 250.0, 50.0, 250.0, 0.0, 150.0
        );
        shape7.setFill(Color.RED);

        generate.setOnAction(
                actionEvent -> {
                    String inputVal = "";
                    inputVal = sides.getText();

                    try {
                        int inputAsInt = Integer.parseInt(inputVal);
                        if (isValid(inputAsInt)) {
                            // reset error message
                            errorMsg.setText("");
                            if(currentNode != null) {
                                inputWithLabel.getChildren().remove(currentNode);
                            }


                            switch (inputAsInt) {
                                case 3:
                                    inputWithLabel.getChildren().add(shape3);
                                    currentNode = shape3;
                                    break;
                                case 4:
                                    inputWithLabel.getChildren().add(shape4);
                                    currentNode = shape4;
                                    break;
                                case 5:
                                    inputWithLabel.getChildren().add(shape5);
                                    currentNode = shape5;
                                    break;
                                case 6:
                                    inputWithLabel.getChildren().add(shape6);
                                    currentNode = shape6;
                                    break;
                                case 7:
                                    inputWithLabel.getChildren().add(shape7);
                                    currentNode = shape7;
                                    break;
                                default:
                                    break;
                            }

                        } else {
                            inputWithLabel.getChildren().remove(currentNode);
                            errorMsg.setText("Invalid input. Please refer to the instructions above");
                        }
                    } catch (Exception e) {
                        inputWithLabel.getChildren().remove(currentNode);
                        errorMsg.setText("Invalid input. Please refer to the instructions above");
                    }

                }
        );

        sides.textProperty().addListener((observable, oldValue, newValue) -> {
            errorMsg.setText("");
        });


        Scene scene = new Scene(root, 500, 500, Color.SKYBLUE);

        stage.setTitle("Shapes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}