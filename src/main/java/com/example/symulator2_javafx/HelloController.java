package com.example.symulator2_javafx;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import samochod.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.Objects;

public class HelloController implements Listener {
    @FXML private TextField modelSamochoduField;
    @FXML private TextField nrRejSamochoduField;
    @FXML private TextField wagaSamochoduField;
    @FXML private TextField maxPredkoscSamochoduField;

    @FXML private TextField nazwaSkrzyniField;
    @FXML private TextField cenaSkrzyniField;
    @FXML private TextField wagaSkrzyniField;
    @FXML protected TextField aktualnyBiegSkrzyniField;

    @FXML private TextField nazwaSilnikaField;
    @FXML private TextField cenaSilnikaField;
    @FXML private TextField wagaSilnikaField;
    @FXML private TextField maxObrotySilnikaField;

    @FXML private TextField nazwaSprzeglaField;
    @FXML private TextField cenaSprzeglaField;
    @FXML private TextField wagaSprzeglaField;
    @FXML private TextField stanSprzeglaField;
    @FXML private BorderPane mapa;
    @FXML public ComboBox<String> carComboBox;

    @FXML private ImageView imageField;

    private ObservableList<String> carList = FXCollections.observableArrayList();
    private ObservableList<Car> carObjects = FXCollections.observableArrayList();
    private Car lastcar;

    @FXML
     private void initialize() {
        carComboBox.setItems(carList);
//generating some default cars
        //car1
        Car car = new Car(
                "ABC1234",
                "toyota",
                180,
                new SkrzyniaBiegow("Manual", 40, 30000, 6, new Sprzeglo("Standard Clutch", 10, 500)),
                new Silnik("1.8L Petrol", 150, 5000, 6000)
        );
        carObjects.add(car);
        carList.add(car.getNrRej());
//car 2
//        car = new Car(
//                "DEF5678",
//                "mazda",
//                200,
//                new SkrzyniaBiegow("Automatic", 50, 4000, 8, new Sprzeglo("Performance Clutch", 12, 700)),
//                new Silnik("2.0L Turbo", 160, 6000, 7000)
//        );
//        carObjects.add(car);
//        carList.add(car.getNrRej());
////car 3
//        car = new Car(
//                "GHI9101",
//                "ford",
//                190,
//                new SkrzyniaBiegow("Manual", 45, 35000, 5, new Sprzeglo("Heavy Duty Clutch", 15, 800)),
//                new Silnik("2.0L Diesel", 170, 5500, 5000)
//        );
//        carObjects.add(car);
//        carList.add(car.getNrRej());
////car 4
//        car = new Car(
//                "JKL2345",
//                "bmw",
//                240,
//                new SkrzyniaBiegow("Automatic", 60, 45000, 7, new Sprzeglo("Sport Clutch", 11, 1000)),
//                new Silnik("2.5L Petrol", 180, 7000, 8000)
//        );
//        carObjects.add(car);
//        carList.add(car.getNrRej());
//car 5
        car = new Car(
                "MNO6789",
                "audi",
                230,
                new SkrzyniaBiegow("Automatic", 55, 50000, 6, new Sprzeglo("Luxury Clutch", 13, 1200)),
                new Silnik("3.0L V6", 200, 8000, 7500)
        );
        carObjects.add(car);
        carList.add(car.getNrRej());

        car.addListener(this);
        lastcar  = car;
        car = null;
        System.gc(); //runs garbage collection
//click mapa event handler
        mapa.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            Pozycja nowaPozycja = new Pozycja(x, y);
            try {
                lastcar.jedzDo(nowaPozycja);
                refreshImage();
            }catch (Exception e){
                showAlert("Er", "Select a car");
            }
        });

    }
    @FXML
    protected void onNacisnijSprzeglo() {
        try {
            getSelectedCarObject().skrzynia.sprzeglo.wcisnij();
            refresh();
        }catch (Exception e){}
    }

    public void onZwolnijSprzeglo(ActionEvent actionEvent) {
        try{
        getSelectedCarObject().skrzynia.sprzeglo.zwolnij();
        refresh();
        }catch (Exception e){}
    }

    public void onWlaczSilnik(ActionEvent actionEvent) {
        try{
        getSelectedCarObject().wlacz();
        refresh();
        }catch (Exception e){}
        }


    public void onWylaczSilnik(ActionEvent actionEvent) {
        try{
        getSelectedCarObject().wylacz();
        refresh();
        }catch (Exception e){}
    }

    public void onBiegPlus(ActionEvent actionEvent) {
        try{
        getSelectedCarObject().skrzynia.zwiekszBieg();
        refresh();
        }catch (Exception e){}
    }

    public void onBiegMinus(ActionEvent actionEvent) {
        try{
        getSelectedCarObject().skrzynia.zmniejszBieg();
        refresh();
        }catch (Exception e){}

    }

    public void onDodajGazu(ActionEvent actionEvent) {
        try{
        getSelectedCarObject().silnik.zwiekszObroty();
        refresh();
        }catch (Exception e){}
    }

    public void onOdejmijGazu(ActionEvent actionEvent) {
        try{
        getSelectedCarObject().silnik.zmniejszObroty();
        refresh();
        }catch (Exception e){}
    }

    public void onDodajAuto(ActionEvent actionEvent){
        try {
            // Load the second scene's FXML file
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("caradd.fxml"));

            // Create a new stage (window)
            CarAdditionController.setHelloController(this);
            Stage stag = new Stage();
            stag.setTitle("Second Scene");
            stag.setScene(new Scene(loader.load(), 162,1009)); // Set the size of the new window
            // Show the new window
            stag.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onUsun(ActionEvent actionEvent) throws Exception {
        // Get the selected registration number (nrRej) from the ComboBox
        String selectedNrRej = carComboBox.getSelectionModel().getSelectedItem();

        if (selectedNrRej == null) {
            showAlert("Selection Error", "Please select a car to delete.");
            return;
        }

        // Find the Car object with the matching nrRej
        Car carToRemove = getSelectedCarObject();

        if (carToRemove != null) {
            // Remove the Car object from carObjects
            showAlert("Success", "Car deleted successfully.");
            carObjects.remove(carToRemove);
            // Remove the nrRej from carList
            carList.remove(selectedNrRej);
            carComboBox.getSelectionModel().select(0);
            // Refresh the ComboBox
//            refresh();
        } else {
            showAlert("Error", "Car not found.");
        }
    }

    public void handleCarSelection(){
        try{
            lastcar.removeListener(this);
        }catch (Exception e){
        }

            refresh();
    }

    @Override
    public void update() {
        Platform.runLater(() -> {
        refreshImage();});
    }

//refresh
    private Car selectedCar;

    public void refresh() {
         try {
             selectedCar = getSelectedCarObject();

             if(selectedCar!= null && selectedCar!= lastcar){
                 lastcar = selectedCar;
                 lastcar.addListener(this);
             }

             if (selectedCar != null) {
                 // Ustaw pola dla samochodu
                 modelSamochoduField.setText(selectedCar.getModel());
                 nrRejSamochoduField.setText(selectedCar.getNrRej());
                 wagaSamochoduField.setText(String.valueOf(selectedCar.getWaga()));
                 maxPredkoscSamochoduField.setText(String.valueOf(selectedCar.getMaxVel()));

                 nazwaSkrzyniField.setText(selectedCar.skrzynia.getNazwa());
                 cenaSkrzyniField.setText(String.valueOf(selectedCar.skrzynia.getCena()));
                 wagaSkrzyniField.setText(String.valueOf(selectedCar.skrzynia.getWaga()));
                 aktualnyBiegSkrzyniField.setText(String.valueOf(selectedCar.skrzynia.getAktualnyBieg()));

                 nazwaSilnikaField.setText(selectedCar.silnik.getNazwa());
                 cenaSilnikaField.setText(String.valueOf(selectedCar.silnik.getCena()));
                 wagaSilnikaField.setText(String.valueOf(selectedCar.silnik.getWaga()));
                 maxObrotySilnikaField.setText(String.valueOf(selectedCar.silnik.getObroty()));

                 nazwaSprzeglaField.setText(selectedCar.skrzynia.sprzeglo.getNazwa());
                 cenaSprzeglaField.setText(String.valueOf(selectedCar.skrzynia.sprzeglo.getCena()));
                 wagaSprzeglaField.setText(String.valueOf(selectedCar.skrzynia.sprzeglo.getWaga()));
                 stanSprzeglaField.setText(String.valueOf(selectedCar.skrzynia.sprzeglo.getStanSprzegla()));

                 Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedCar.getModel() + ".jpg")));
                 imageField.setImage(image);
                 refreshImage();

             }

         } catch (Exception e) {
         }

    }


    public void refreshImage(){
        double x_cord = getSelectedCarObject().pozycja.getX();
        double y_cord = getSelectedCarObject().pozycja.getY();
        imageField.setLayoutX(Math.round(x_cord*100)/100.0);
        imageField.setLayoutY(Math.round(y_cord*100)/100.0);
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
//
//    private void setImage(){
//        double x_cord = Math.round(getSelectedCarObject().getAktPozycjaX());
//        double y_cord = Math.round(getSelectedCarObject().getAktPozycjaY());
//        if(x_cord <= 800 && y_cord<=1200 && x_cord >0 && y_cord >0) {
//            imageField.setLayoutX(Math.round(x_cord));
//            imageField.setLayoutY(Math.round(y_cord));
//        }
//    }

//geters
    public Car getSelectedCarObject() {
    try {
        for (Car car_search  : carObjects) {
            if (car_search.getNrRej().equals(carComboBox.getSelectionModel().getSelectedItem())) {
                return car_search;
            }
        }
    } catch (Exception e) {
    }
        return null;
}

    public ObservableList<String> getCarList() {
            return carList;
    }

    public ObservableList<Car> getCarObjects() {
        return carObjects;
    }


}