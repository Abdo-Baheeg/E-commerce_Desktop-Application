package src.DAO;

import src.database.Database;
import src.entities.Admin;
import src.entities.Customer;

import java.util.List;

import static src.database.Database.admins;
import static src.database.Database.customers;

public class AdminDAO implements CRUD <Admin> {

    // validators : ............//
    public static boolean validName(String name) {
        return name != null &&
                name.matches("^[A-Za-z\\s]{2,50}$");
    }
    public static boolean validEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null &&
                email.matches(regex) &&
                email.length() <= 100;
    }
    public static boolean validPassword(String password) {
        return password != null &&
                password.length() >= 6 &&
                password.length() <= 20 &&
                password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    }
    public static boolean validPhone(String phone) {
        return phone != null &&
                phone.matches("^\\d{10}$");
    }
    public static boolean validAddress(String address) {
        return address != null &&
                address.length() >= 10 &&
                address.length() <= 200 &&
                address.matches("^[A-Za-z0-9\\s,.-]+$");
    }
    public static boolean validUsername(String username) {
        boolean exist= false;
        for(Admin admin: admins) {
            if (admin.getUsername().equals(username)) {
                exist = true;
                break;
            }
        }
        return username != null &&
                username.matches("^[A-Za-z0-9_]{4,20}$") &&
                !username.matches("^\\d+$")&& !exist; // Prevent all-numeric usernames
    }

    @Override
    public void create(Admin entity) {
        admins.add(entity);
    }

    @Override
    public Admin read(Admin a) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(a.getUsername())){
            return admin;}
        }
        return null;
    }

    @Override
    public Admin read(int id) {
        for (Admin admin : admins) {
            if (admin.getID() == id){
                return admin;
            }
        }
        return null;
    }

    @Override
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
                admins.set(admins.indexOf(admin), entity);
            }
        }
    }

    @Override
    public void delete(Admin a) {
        admins.remove(a);
    }
    public boolean delete(int id) {
        for (Admin admin : admins) {
            if (admin.getID() == id) {
                admins.remove(admin);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Admin> getAll() {
        return admins;
    }

    public boolean updateName(Admin entity, String name) {
        if(validName(name)) {
            entity.setName(name);
            return true;
        }
        else
            return false;
    }
    public boolean updateEmail(Admin entity, String email) {
        if(validEmail(email)) {
            entity.setEmail(email);
            return true;
        }
        else
            return false;
    }
    public boolean updatePassword(Admin entity, String password) {
        if(validPassword(password)) {
            entity.setPassword(password);
            return true;
        }
        else
            return false;
    }
    public boolean updatePhone(Admin entity, String phone) {
        if(validPhone(phone)) {
            entity.setPhone(phone);
            return true;
        }
        else
            return false;
    }
    public boolean updateAddress(Admin entity, String address) {
        if(validAddress(address)) {
            entity.setAddress(address);
            return true;
        }
        else
            return false;
    }
    public boolean updateUsername(Admin entity, String username) {
        if(validUsername(username)) {
            entity.setUsername(username);
            return true;
        }
        else
            return false;
    }


}
