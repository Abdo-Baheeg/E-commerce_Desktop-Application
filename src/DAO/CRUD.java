package src.DAO;

import java.util.List;

public interface CRUD<T> {
    void create(T entity);       // Create operation
    T read(Object id);           // Read operation (use Object for flexibility)
    void update(T entity);       // Update operation
    void delete(Object id);      // Delete operation
    List<T> getAll();            // Retrieve all entities
}
