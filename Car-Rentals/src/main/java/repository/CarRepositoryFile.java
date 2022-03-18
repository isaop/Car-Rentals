package repository;

import domain.Car;
import exceptions.RepositoryException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CarRepositoryFile extends AbstractRepository<Car,Integer> {
    private String filename;
    public CarRepositoryFile(String filename){
        this.filename=filename;
        //readFromFile();
    }

    private void readFromFile(){
        try(BufferedReader reader=new BufferedReader(new FileReader(filename))){
            String line;
            while((line=reader.readLine())!=null){
                String[] el=line.split(";");
                if(el.length!=4){
                    System.err.println("Not a valid number of atributes "+line);
                    continue;
                }
                try{
                    int Id=Integer.parseInt(el[0]);
                    int price = Integer.parseInt(el[2]);
                    int year = Integer.parseInt(el[3]);
                    Car c=new
                            Car(Id,el[1],price,year);
                    super.add(c);
                }
                catch(NumberFormatException n){
                    System.err.println("The ID is not a valid number "+el[0]);
                }
            }
        }
        catch(IOException ex){
            throw new RepositoryException("Error reading"+ex);
        }
    }

    @Override
    public void add(Car obj) {
        try{
            super.add(obj);
            writeToFile();
        }
        catch(RuntimeException e){
            throw new RepositoryException("Object wasn�t added" + e + " " + obj);
        }
    }

    private void writeToFile() {
        try(PrintWriter pw = new PrintWriter(filename)) {
            for(Car el : findAll()) {
                String line = el.getID() + ";" + el.getMake() + ";" + el.getPrice() + ";" + el.getYear() ;
                pw.println(line);
            }
        }
        catch(IOException ex) {
            throw new RepositoryException("Error writing" + ex);
        }
    }

    @Override
    public void delete(Car obj){
        try{
            super.delete(obj);
            writeToFile();
        }
        catch(RuntimeException ex){
            throw new RepositoryException("Object was not deleted"+ex+" "+obj);
        }
    }

    @Override
    public void update(Car obj, Integer id){
        try{
            super.update(obj, id);
            writeToFile();
        }
        catch(RuntimeException ex){
            throw new RepositoryException("Object was not updated" + ex + " " + obj);
        }
    }
}
