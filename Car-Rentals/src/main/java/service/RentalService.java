package service;

import domain.Car;
import domain.Rental;
import helpers.ConfigurationHelper;
import repository.CarRepositoryFile;
import repository.RentalRepository;
import repository.RentalRepositoryFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RentalService {
    private RentalRepository rental_repo;
    private RentalRepositoryFile rental_repo_file;


    public RentalService()
    {
        this.rental_repo = new RentalRepository();
        CarRepositoryFile crf = new CarRepositoryFile("F:\\l;lab6_HomeAssignmentLast\\src\\car_list.txt");
        try {
            this.rental_repo_file = new RentalRepositoryFile(ConfigurationHelper.getConfig("rental_list_filename"),crf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRentalService(Rental el){
            rental_repo.add(el);
            rental_repo_file.add(el);
    }

    public Iterable<Rental> findAllServiceRental(){
        return rental_repo_file.findAll();
    }


    public void delete(int id) {
        Rental c = new Rental();
        c.setID(id);
        rental_repo.delete(c);
        rental_repo_file.delete(c);


    }
    public void update(Rental r, int id){
        rental_repo.update(r,id);
        rental_repo_file.update(r,id);
    }

    public Rental findByID(int id){
        Rental c = new Rental();
        c = rental_repo.findById(id);
        return c;
    }

    public Collection<Rental> controllerGetAll() {
        return rental_repo.getAll();
    }

    public List<Car> getCarsRentedByGeorge(String pers) {
        Collection<Rental> rentalCollection = controllerGetAll();
        List<Car> car_list = rentalCollection.stream().filter(rental -> rental.getPerson().equals(pers)).map(rental -> rental.getCar()).collect(Collectors.toList());
        List<Car> result = new ArrayList<>();
        for (Car c : car_list) {
            result.add(c);
        }
        return result;
    }

    public List<Rental> getRentalsOfVolvo(String make) {
        Collection<Rental> rentalCollection = controllerGetAll();
        List<Rental> rental_list = rentalCollection.stream().filter(rental -> rental.getCar().getMake().equals(make)).collect(Collectors.toList());
        List<Rental> result = new ArrayList<>();
        for (Rental rental : rental_list) {
            result.add(rental);
        }
        return result;
    }

    public List<Car>getCarsRentedOnDate(String date) {
        Collection<Rental> rentalCollection = controllerGetAll();
        List<Car> car_list = rentalCollection.stream().filter(rental -> rental.getDate().equals(date)).map(rental -> rental.getCar()).collect(Collectors.toList());
        List<Car> result = new ArrayList<>();
        for (Car car : car_list) {
            result.add(car);
        }
        return result;
    }
}
