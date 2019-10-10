package ua.nure.kn.ovsiienko.db;

import java.util.Collection;

public interface DAO<T> {
    T create(T entity) throws DatabaseException;
    
    void update(T entity) throws DatabaseException;
    
    void delete(T entity) throws DatabaseException;
    
    T find(Long id) throws DatabaseException;
    
    Collection<T> findAll() throws DatabaseException;
}
