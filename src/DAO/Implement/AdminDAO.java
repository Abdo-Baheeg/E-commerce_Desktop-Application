package src.DAO.Implement;

import src.DAO.CRUD;
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
        return null;
    }

    @Override
    public void update(Admin entity) {

    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public List<Admin> getAll() {
        return List.of();
    }
}
