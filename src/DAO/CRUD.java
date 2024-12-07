package src.DAO;

import java.util.List;

public interface CRUD<T> {
    void  create(T entity);       // Create operation
    T read(T object);           // Read operation (use Object for flexibility)
    T read(int id);
    T read(String string);
    void update(T entity);       // Update operation
    void delete(T entity);      // Delete operation
    List<T> getAll();          // Retrieve all entities
}
