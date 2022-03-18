package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import exceptions.RepositoryException;
import domain.Car;
import domain.Rental;

public class RentalRepositoryFile extends AbstractRepository<Rental, Integer>{
    private CarRepositoryFile reqRepo;
    private String filename;

    public RentalRepositoryFile(String filename, CarRepositoryFile reqRepo){
        this.filename=filename;
        this.reqRepo = reqRepo;
        //readFromFile();
    }

    private void readFromFile()
    {
        try(BufferedReader reader=new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line=reader.readLine())!=null)
            {
                String[] el=line.split(";");
                if(el.length!=6)
                {
                    System.err.println("Not a valid number of atributes"+line);
                    continue;
                }
                try
                {
                    int Id=Integer.parseInt(el[0]);
                    Car a = reqRepo.findById(Integer.parseInt(el[1]));
                    Rental c=new Rental(Id,a,el[2],el[3]);
                    super.add(c);
                }
                catch(NumberFormatException n)
                {
                    System.err.println("The ID is not a valid number"+el[0]);
                }
            }
        }
        catch(IOException ex)
        {
            throw new RepositoryException("Error reading"+ex);
        }
    }

    private void writeToFile()
    {
        try(PrintWriter pw = new PrintWriter(filename))
        {
            for(Rental el : findAll())
            {
                String line = el.getID() + ";"  + el.getCar().toString() +  ";" + el.getPerson() +  ";" + el.getDate() ;
                pw.println(line);
            }
        }
        catch(IOException ex)
        {
            throw new RepositoryException("Error writing" + ex);
        }
    }

    @Override
    public void add(Rental obj) {
        try{
            super.add(obj);
            writeToFile();
        }
        catch(RuntimeException e){
            throw new RepositoryException("Object wasn�t added" + e + " " + obj);
        }
    }


    @Override
    public void delete(Rental obj){
        try{
            super.delete(obj);
            writeToFile();
        }
        catch(RuntimeException ex){
            throw new RepositoryException("Object was not deleted"+ex+" "+obj);
        }
    }

    @Override
    public void update(Rental obj, Integer id){
        try{
            super.update(obj, id);
            writeToFile();
        }
        catch(RuntimeException ex){
            throw new RepositoryException("Object was not updated" + ex + " " + obj);
        }
    }
}
