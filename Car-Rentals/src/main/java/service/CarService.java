package service;

import domain.Car;
import helpers.ConfigurationHelper;
import repository.CarRepository;
import repository.CarRepositoryFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    private CarRepository car_repo;
    private CarRepositoryFile car_repo_file;

    public CarService() throws IOException {
        this.car_repo = new CarRepository();
        this.car_repo_file = new CarRepositoryFile(ConfigurationHelper.getConfig("cars_list_filename"));
    }

    public void add(Car el){
        car_repo.add(el);
        car_repo_file.add(el);
    }

    public Iterable<Car> findAllService(){
        return car_repo.findAll();
    }


    public void delete(int id) {
        Car c = new Car();
        c.setID(id);
        car_repo.delete(c);
        car_repo_file.delete(c);
    }


    public void update(Car car,int id){
        car_repo.update(car,id);
        car_repo_file.update(car,id);
    }

    public Car findByID(int id){
        Car c = new Car();
        c = car_repo_file.findById(id);
        return c;
    }

    public Collection<Car> controllerGetAll() {
        return car_repo_file.getAll();
    }
    public List<Integer> getYearOfCarsStartingWithB(String b) {
        Collection<Car> carCollection = controllerGetAll();
        List<Integer> carYear_list = carCollection.stream().filter(car -> car.getMake().startsWith(b)).map(car -> car.getYear()).collect(Collectors.toList());
        List<Integer> result = new ArrayList<>();
        for (Integer year : carYear_list) {
            result.add(year);
        }
        return result;
    }


    public int getNumberOfCarsWithYear2015(int year){
        Collection<Car>carCollection=controllerGetAll();
        int returnCount;
        returnCount = (int) carCollection.stream().filter(car -> car.getYear() == year).count();
        return returnCount;
    }
}

