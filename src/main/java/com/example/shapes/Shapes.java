package com.example.shapes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;



/**
 *
 * Shapes class - It accepts user input and generates a shape with the number
 * of sides entered. Sides must be between 3 and 7
 *
 * @author Darius Njihia Kihuha
 * @version 0.0.1
 */
public class Shapes extends Application {

    Shape currentNode = null;
    int currentSides = 3;

    /**
     * Validates the input enetered by the user
     *
     * @param input  - input value supplied by the user
     * @return boolean
     */
    public boolean isValid(int input) {
        return input >= 3 && input <= 7;
    }

    /**
     *
     * @param sides - number of sides the shape has
     * @return double[]
     */
    public double[] getCoords(int sides) {
        return switch (sides) {
            case 3 -> new double[]{100.0, 100.0, 100.0, 300.0, 300.0, 300.0};
            case 4 -> new double[]{100.0, 50.0, 300.0, 50.0, 300.0, 300.0, 100.0, 300.0};
            case 5 -> new double[]{100.0, 50.0, 200.0, 150.0, 150.0, 250.0, 50.0, 250.0, 0.0, 150.0};
            case 6 -> new double[]{50.0, 50.0, 150.0, 50.0, 200.0, 150.0, 150.0, 250.0, 50.0, 250.0, 0.0, 150.0};
            case 7 -> new double[]{25.0, 50.0, 100.0, 0.0, 175.0, 50.0, 200.0, 150.0, 150.0, 250.0, 50.0, 250.0, 0.0, 150.0};
            default -> new double[]{};
        };
    }

    public Shape generateShape(int sides) {
        Polygon newShape = new Polygon();
        for(double coord: getCoords(sides)) {
            newShape.getPoints().add(coord);
        }

        if (sides % 2 == 0) {
            newShape.setFill(Color.BLUE);
        } else {
            newShape.setFill(Color.RED);
        }

        return newShape;
    }

    /**
     * Helper method to append a new shape to the parent
     *
     * @param parent - the VBox where the shape is shown
     */
    public void appendNewShape(VBox parent) {
        Shape newShape = generateShape(this.currentSides);
        parent.getChildren().remove(currentNode);
        parent.getChildren().add(newShape);
        currentNode = newShape;
    }

    @Override
    public void start(Stage stage) {
        // CONTROLS AND LAYOUT
        Label msg = new Label("Please enter a number between 3 and 7 (inclusive)");
        Label errorMsg = new Label();
        errorMsg.setTextFill(Color.RED);

        TextField sidesInput = new TextField();
        sidesInput.setText(Integer.toString(currentSides));

        Button generateBtn = new Button("Generate Shape");
        Button addSideBtn = new Button("+ Add");
        Button subtractSideBtn = new Button("- Subtract");

        HBox inputWithButtons = new HBox(10);
        inputWithButtons.getChildren().addAll( subtractSideBtn, sidesInput, addSideBtn);


        VBox inputGroup = new VBox(20);
        inputGroup.setAlignment(Pos.CENTER);
        inputGroup.getChildren().addAll(inputWithButtons, generateBtn);

        VBox inputWithLabel = new VBox(10);
        inputWithLabel.getChildren().addAll(msg, inputGroup, errorMsg);


        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20, 10, 10, 20));
        root.getChildren().add(inputWithLabel);
        // END OF CONTROLS AND LAYOUT

        // EVENT HANDLERS
        generateBtn.setOnAction(
                actionEvent -> {
                    String inputVal;
                    inputVal = sidesInput.getText();
                    int inputAsInt = 0;
                    try {
                        inputAsInt = Integer.parseInt(inputVal);
                    } catch (Exception e) {
                        inputWithLabel.getChildren().remove(currentNode);
                        errorMsg.setText("Invalid input. Please refer to the instructions above");
                    }

                    if (isValid(inputAsInt)) {
                        // 1. Generate the new shape to be added
                        Shape newShape = generateShape(inputAsInt);


                        // 2. Reset the error message and remove the existing shape if exists
                        errorMsg.setText("");
                        if(currentNode != null) {
                            inputWithLabel.getChildren().remove(currentNode);
                        }

                        // 3. Add the shape as a node and update the currentNode
                        inputWithLabel.getChildren().add(newShape);
                        currentNode = newShape;
                    } else {
                        // If the input is invalid, remove the shape from view and show error message
                        inputWithLabel.getChildren().remove(currentNode);
                        errorMsg.setText("Invalid input. Please refer to the instructions above");
                    }

                }
        );

        sidesInput.textProperty().addListener((observable, oldValue, newValue) -> errorMsg.setText(""));

        addSideBtn.setOnAction(
                actionEvent -> {
                    if (currentSides >= 3 && currentSides < 7) this.currentSides += 1;
                    sidesInput.setText(Integer.toString(this.currentSides));
                    appendNewShape(inputWithLabel);
                }
        );

        subtractSideBtn.setOnAction(
                actionEvent -> {
                    if (currentSides > 3 && currentSides <= 7) this.currentSides -= 1;
                    sidesInput.setText(Integer.toString(this.currentSides));
                    appendNewShape(inputWithLabel);
                }
        );
        // END OF EVENT HANDLERS


        Scene scene = new Scene(root, 500, 500, Color.SKYBLUE);
        stage.setTitle("Shapes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}