package domain;
import java.io.Serializable;
import java.util.Objects;

public class Rental implements Identifiable<Integer> , Serializable {
    private int id;
    private Car car;
    private String date;
    private String person;


    public Rental (int ID,  Car c, String d,String pers){
        this.id=ID;
        this.car=c;
        this.date=d;
        this.person = pers;
    }

    public Rental(){
        this.id=0;
        this.date="";
        this.car=null;
        this.person = "";
    }

    @Override
    public String toString(){
        String s = "Rental with id: " + this.id + ", date: "+this.date+", car: "+this.car + ", rented by: " + this.person +". \n";
        return s;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer Id) {
        id = Id;
    }

    public String getDate() {return this.date;}

    public void setDate(String m) {this.date = m;}

    public String getPerson() {return this.person;}

    public void setPerson(String p) {this.person = p;}

    public Car getCar() {return this.car;}

    public void setCar(Car c) {this.car = c;}

    public String getMakeOfRentedCar()
    {
        return this.car.getMake();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental that = (Rental) o;
        return Objects.equals(id, that.id) && Objects.equals(car, that.car) && Objects.equals(date, that.date)&& Objects.equals(person, that.person);
    }


}
