package repository;
import domain.Car;
import domain.Identifiable;
import exceptions.RepositoryException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public abstract class AbstractRepository <T extends Identifiable<ID>,ID> implements RepositoryInterface<T, ID> {
    protected Map<ID,T> elements;

    public AbstractRepository(){
        elements= new HashMap<>();
    }


    public void add(T el){
        if(elements.containsKey(el.getID()))
            throw new RuntimeException("Element already exists!!!");
        else
            elements.put(el.getID(),el);
    }


    public void delete(T el){
        if(elements.containsKey(el.getID()))
            elements.remove(el.getID());
        else
            throw new RuntimeException("Element doesn't exist!");
    }



    public void update(T el,ID id){
        if(elements.containsKey(id))
            elements.put(el.getID(),el);
        else
            throw new RuntimeException("Element doesn’t exist");
    }


    public T findById( ID id){
        if(elements.containsKey(id))
            return elements.get(id);
        else
            throw new RuntimeException("Element doesn't exist");
    }

    public Iterable<T> findAll(){
        return elements.values();
    }



    public Collection<T> getAll() {
        return elements.values();
    }


    }


