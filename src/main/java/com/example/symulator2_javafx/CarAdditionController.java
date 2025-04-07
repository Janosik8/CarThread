package com.example.symulator2_javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import samochod.Car;
import samochod.Sprzeglo;
import samochod.SkrzyniaBiegow;
import samochod.Silnik;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CarAdditionController {
    @FXML private TextField modelSamochoduField;
    @FXML private TextField nrRejSamochoduField;
    @FXML private TextField wagaSamochoduField;
    @FXML private TextField maxPredkoscSamochoduField;

    @FXML private TextField nazwaSkrzyniField;
    @FXML private TextField cenaSkrzyniField;
    @FXML private TextField wagaSkrzyniField;
    @FXML private TextField aktualnyBiegSkrzyniField;

    @FXML private TextField nazwaSilnikaField;
    @FXML private TextField cenaSilnikaField;
    @FXML private TextField wagaSilnikaField;
    @FXML private TextField maxObrotySilnikaField;

    @FXML private TextField nazwaSprzeglaField;
    @FXML private TextField cenaSprzeglaField;
    @FXML private TextField wagaSprzeglaField;
    @FXML private TextField stanSprzeglaField;

    @FXML private Image imageField;

    private static ObservableList<String> carList = FXCollections.observableArrayList();
    private static ObservableList<Car> carObjects = FXCollections.observableArrayList();
    private static HelloController helCon;

       public static void setHelloController(HelloController helloController) {
          carList = helloController.getCarList();
          carObjects = helloController.getCarObjects();
          helCon = helloController;
    }

    public void onDodaj(ActionEvent actionEvent) {

        // Method to set HelloController reference

        try {
            String nrRej = nrRejSamochoduField.getText();

            // Check if nrRej is unique
            boolean isDuplicate = carObjects.stream()
                    .anyMatch(car -> car.getNrRej().equals(nrRej));
            if (isDuplicate) {
                showAlert("Duplicate Entry", "A car with this registration number already exists.");
                return; // Stop execution if duplicate found
            }

            //create unique car (unique nrRej)
            Car newCar = new Car(nrRejSamochoduField.getText(), modelSamochoduField.getText(),
                    Integer.parseInt(maxPredkoscSamochoduField.getText()),
                    new SkrzyniaBiegow(nazwaSkrzyniField.getText(),
                            Integer.parseInt(wagaSkrzyniField.getText()),Integer.parseInt(cenaSkrzyniField.getText()), Integer.parseInt(aktualnyBiegSkrzyniField.getText()),
                            new Sprzeglo(nazwaSprzeglaField.getText(), Integer.parseInt(wagaSprzeglaField.getText()), Integer.parseInt(cenaSprzeglaField.getText()))),
                    new Silnik(nazwaSilnikaField.getText(),
                            Integer.parseInt(wagaSilnikaField.getText()),
                            Integer.parseInt(cenaSilnikaField.getText()),
                            Integer.parseInt(maxObrotySilnikaField.getText())));

            //add car to carObjects list
            carObjects.add(newCar);
            carList.add(newCar.getNrRej());
         //   newCar.start();
            helCon.carComboBox.setValue(newCar.getNrRej());
            helCon.refresh();
            showAlert("Car addition", "Car added successfully");
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter a valid numbers and text");
        }
    }

    public void onZamknij(ActionEvent actionEvent){
        // Get the stage (window) associated with the button
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();  // Close the stage
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}