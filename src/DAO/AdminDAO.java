package src.DAO;

import src.entities.Admin;

import java.util.List;

import static src.database.Database.admins;

public class AdminDAO implements CRUD <Admin> {

    @Override
    public void create(Admin entity) {
        admins.add(entity);
    }

    @Override
    public Admin read(Object id) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(id)){
            return admin;}
        }
        return null;
    }

    @Override
    public Admin readByID(int id) {
        for (Admin admin : admins) {
            if (admin.getID() == (id)){
                return admin;
            }
        }
        return null;
    }

    public Admin read(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public void update(Admin entity) {
        for (Admin admin : admins) {
            if (admin.getID() == entity.getID()) {
                admins.set(admin.getID()-1, entity);
            }
        }
    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public List<Admin> getAll() {
        return List.of();
    }
}
