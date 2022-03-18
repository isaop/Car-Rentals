package com.example.lab6_homeassignmentlast;

import domain.Car;
import domain.Rental;
import exceptions.RepositoryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import repository.RepositoryInterface;
import service.CarService;
import service.RentalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {



    private CarService car_service;
    private RentalService rental_service;
    private RepositoryInterface repofile;


    @FXML
    private TextField brand_text;
    @FXML
    private TextField price_text;
    @FXML
    private TextField year_text;
    @FXML
    private TextField id_text;
    @FXML
    private TextField datepicker;
    @FXML
    private TextField rPerson_text;
    @FXML
    private TextField rID_text;
    @FXML
    private TextField rCar_text;
    @FXML
    private TextArea output_text;
    @FXML
    private TextArea output_rental;
    @FXML
    private TextField deleteCarID_text;
    @FXML
    private TextField deleteRentalID_text;
    @FXML
    private TextField findID_text;
    @FXML
    private TextField findRentalID_text;
    @FXML
    private TextField yearFilter_text;
    @FXML
    private TextField bString_text;
    @FXML
    private TextField givenPerson_text;
    @FXML
    private TextField makeFilter_text;
    @FXML
    private TextField dateFilter_text;



    public HelloController()
    {
        try{
        this.car_service = new CarService();

        this.rental_service = new RentalService();}
        catch(IOException e){
            output_text.setText("Error");
        }


        Car car1 = new Car(1, "BMW", 2015, 11000);
        Car car2 = new Car(2, "Opel", 2013, 6000);
        Car car3 = new Car(3, "VW", 2015, 8000);
        Car car4 = new Car(4, "Volvo", 2018, 20000);
        Rental rental1 = new Rental(1, car1, "12.11.2021", "George");
        Rental rental2 = new Rental(2, car2, "12.11.2021", "Andrei");
        Rental rental3 = new Rental(3, car3, "12.11.2021", "George");
        Rental rental4 = new Rental(4, car3, "13.11.2021", "Mara");
        Rental rental5 = new Rental(5, car4, "14.12.2021", "George");
        Rental rental6 = new Rental(6, car4, "16.12.2022", "Oana");


        car_service.add(car1);
        car_service.add(car2);
        car_service.add(car3);
        car_service.add(car4);
        rental_service.addRentalService(rental1);
        rental_service.addRentalService(rental2);
        rental_service.addRentalService(rental3);
        rental_service.addRentalService(rental4);
        rental_service.addRentalService(rental5);
        rental_service.addRentalService(rental6);


    }

    @FXML
    public void onAddCarClick(ActionEvent event){
        try {
            int year = Integer.parseInt(year_text.getText());
            int price = Integer.parseInt(price_text.getText());
            String brand = brand_text.getText();
            int ID = Integer.parseInt(id_text.getText());

            Car newCar = new Car(ID, brand, year, price);
            car_service.add(newCar);
        }
        catch(RuntimeException e){
            output_text.setText("Failed to add data!");
        }
    }

    public void onClearFieldsClick(ActionEvent actionEvent) {
        brand_text.setText("");
        id_text.setText("");
        year_text.setText("");
        price_text.setText("");
    }


    public void onAddRentalClick(ActionEvent actionEvent) {
        try {
            String date = datepicker.getText();
            String person = rPerson_text.getText();
            int ID = Integer.parseInt(rID_text.getText());

            int carID = Integer.parseInt(rCar_text.getText());
            Car wantedCar = new Car();
            for (Car car : car_service.findAllService()) {
                if (car.getID() == carID) {
                    wantedCar = car;
                    break;
                }
            }
            Rental newRental = new Rental(ID, wantedCar, date, person);

            rental_service.addRentalService(newRental);
        }
        catch(RuntimeException e){
            output_rental.setText("Failed to add data!");
        }

    }

    public void onDeleteCarClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(deleteCarID_text.getText());

            car_service.delete(id);
        }
        catch(RuntimeException e)
        {
            output_text.setText("Failed to delete data!");
        }

    }

    public void onDeleteRentalClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(deleteRentalID_text.getText());
            rental_service.delete(id);
        }
        catch(RuntimeException e){
            output_rental.setText("Failed to delete data!");
        }


    }


    public void onUpdateCarClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(id_text.getText());
            int new_year = Integer.parseInt(year_text.getText());
            int new_price = Integer.parseInt(price_text.getText());
            String new_brand = brand_text.getText();
            Car newCar = new Car(id, new_brand, new_year, new_price);
            car_service.update(newCar, id);
        }
        catch(RuntimeException e){
            output_text.setText("Failed to update car!");
        }
    }

    public void onUpdateRentalClick(ActionEvent actionEvent) {
        try {
            String date = datepicker.getText();
            String person = rPerson_text.getText();
            int ID = Integer.parseInt(rID_text.getText());
            int carID = Integer.parseInt(rCar_text.getText());
            Car wantedCar = new Car();
            for (Car car : car_service.findAllService()) {
                if (car.getID() == carID) {
                    wantedCar = car;
                    break;
                }
            }
            Rental newRental = new Rental(ID, wantedCar, date, person);
            rental_service.update(newRental, ID);
        }
        catch(RuntimeException e)
        {
            output_rental.setText("Failed to update rental!");
        }
    }

    public void onShowCarsClick(ActionEvent actionEvent) {
        String out = car_service.findAllService().toString();
        output_text.setText(out);
    }

    public void onShowRentalsClick(ActionEvent actionEvent) {
        String out = rental_service.findAllServiceRental().toString();
        output_rental.setText(out);
    }


    public void onFindCarByIDClick(ActionEvent actionEvent) {
        try{
        int ID = Integer.parseInt(findID_text.getText());
        Car c = new Car();
        c = car_service.findByID(ID);
        String out = c.toString();
        output_text.setText(out);}
        catch (RuntimeException e)
        {
            output_text.setText("Car not found!");
        }

    }

    public void onFindRentalByIDClick(ActionEvent actionEvent) {
        try{
        int ID = Integer.parseInt(findRentalID_text.getText());
        Rental r = new Rental();
        r = rental_service.findByID(ID);
        String out = r.toString();
        output_rental.setText(out);}
        catch(RuntimeException e){
            output_rental.setText("Rental not found!");
        }
    }

    public void onStartWithBClick(ActionEvent actionEvent) {


        String letter = bString_text.getText();
        List<Integer> out = new ArrayList<>();
        out = car_service.getYearOfCarsStartingWithB(letter);
        String s = out.toString();

        output_text.setText(s);


    }

    public void getNrCarsWithGivenYearClick(ActionEvent actionEvent) {
        String strYear = yearFilter_text.getText();
        int year = Integer.parseInt(strYear);

        int number = car_service.getNumberOfCarsWithYear2015(year);
        output_text.setText(String.valueOf(number));

    }

    public void onGetRentedByPersonClick(ActionEvent actionEvent) {
        String person = givenPerson_text.getText();

        List<Car> cars = new ArrayList<>();
        cars = rental_service.getCarsRentedByGeorge(person);
       // String s = cars.toString();

        List<String> stringsList = new ArrayList<String>(cars.size());
        for (Car car : cars) {
            stringsList.add(car.toString());

        }
        output_rental.setText(String.valueOf(stringsList));

    }


    public void onGetRentalsOfCar(ActionEvent actionEvent) {
            String make = makeFilter_text.getText();
            List<Rental> ren = new ArrayList<>();
            ren =  rental_service.getRentalsOfVolvo(make);
            String str = ren.toString();

            output_rental.setText(str);

    }

    public void onGetCarsOnDateClick(ActionEvent actionEvent) {
        String date = dateFilter_text.getText();
        List<Car> ren = new ArrayList<>();
        ren =  rental_service.getCarsRentedOnDate(date);
        String str = ren.toString();

        output_rental.setText(str);
    }
}
