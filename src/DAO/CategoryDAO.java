package src.DAO;

import src.database.Database;
import src.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements CRUD<Category>{

    public static boolean validName(String name) {
        for (Category c : Database.categories){
            if (c.getName().equals(name)){
                return false;
            }
        }
        return name != null &&
                name.matches("^[A-Za-z\\s]{2,50}$");
    }
    public static boolean validDescription(String description) {
        return description != null &&
                description.matches("^[A-Za-z\\s]{2,250}$");
    }


    @Override
    public void create(Category entity) {
        Database.categories.add(entity);
    }

    @Override
    public Category read(Category object) {
            if(Database.categories.contains(object)){
                return object;
            }

        return null;
    }

    @Override
    public Category read(int id) {
        for(Category c : Database.categories){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public Category read(String string) {
        for(Category c : Database.categories){
            if(c.getName().equals(string)){
                return c;
            }
        }
        return null;
    }

    @Override
    public void update(Category entity) {
        for(Category c : Database.categories){
            if(c.getId() == entity.getId()){
                Database.categories.set(entity.getId(), entity);
            }
        }
    }

    @Override
    public void delete(Category entity) {
        Database.categories.remove(entity);
    }

    @Override
    public ArrayList<Category> getAll() {
        return Database.categories;
    }

}
