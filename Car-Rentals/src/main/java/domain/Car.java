package domain;


import java.io.Serializable;

public class Car implements Identifiable<Integer>, Serializable {
    private Integer id;
    private String make;
    private int year;
    private int price;

    public Car (int ID,  String make, int year, int price){
        this.id=ID;
        this.make=make;
        this.year=year;
        this.price=price;
    }

    public Car(){
        this.id=0;
        this.make="";
        this.year=0;
        this.price=0;
    }

    @Override
    public String toString(){
        String s = "Car with id: " + this.id + ", make: "+this.make+", price: "+this.price+", year: "+ this.year+". \n";

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

    public String getMake() {return this.make;}

    public void setMake(String m) {this.make = m;}

    public int getYear() {return this.year;}

    public void setYear(int y) {this.year = y;}

    public int getPrice() {return this.price;}

    public void setPrice(int pr) {this.price = pr;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id.equals(car.id) && make.equals(car.make) && (price == car.price) && (year == car.year);
    }



}