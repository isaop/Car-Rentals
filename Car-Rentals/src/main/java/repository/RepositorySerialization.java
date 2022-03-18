package repository;
import domain.Car;
import exceptions.RepositoryException;

import java.io.*;
import java.util.Map;


public class RepositorySerialization extends AbstractRepository<Car, Integer> {
    private String filename;

    public RepositorySerialization(String filename) {
        this.filename = filename;
        readFromFile();
    }

    private void writeToFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(elements);
        } catch (IOException r) {
            throw new RepositoryException("message " + r);
        }
    }

    private void readFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            elements = (Map<Integer, Car>) in.readObject();
        } catch (IOException | ClassNotFoundException err) {
            if (!(err instanceof EOFException)) {
                throw new RepositoryException("Error reading from file: " + err);


            }
        }

    }

    @Override
    public void add(Car obj) {
        try {
            super.add(obj);
            writeToFile();
        } catch (RuntimeException e) {
            throw new RepositoryException("Object wasn't added" + e + " " + obj);
        }
    }

    @Override
    public void delete(Car obj) {
        try {
            super.delete(obj);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RepositoryException("Object was not deleted" + ex + " " + obj);
        }
    }

    @Override
    public void update(Car obj, Integer id) {
        try {
            super.update(obj, id);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RepositoryException("Object was not updated" + ex + " " + obj);
        }
    }
}
