package cat.proven.dbpersist;

import java.util.List;

/**
 * Interface to implement DAO pattern.
 * @author Jose Moreno
 * @param <T> the type of object to make persistent.
 */
public interface DaoInterface<T> {

    /**
     * searches entity in the data
     * @param entity to search
     * @return entity found or null if not found or in case of error.
     */
    T find(T entity);

    /**
     * retrieves all data
     * @return all data o null in case of error.
     */
    List<T> selectAll();

    /**
     * adds a new element to the data repository
     * @param entity to be inserted
     * @return number of entries affected by the operation
     */
    int insert(T entity);

    /**
     * removes the given entity from the data repository
     * @param entity to be removed
     * @return number of entries affected by the operation
     */
    int delete(T entity);

    /**
     * modifies oldEntity to newEntity values
     * @param oldEntity entity to be updated
     * @param newEntity new values for the given entity
     * @return number of entries affected by the operation
     */
    int update(T oldEntity, T newEntity);

    /**
     * finds objects with the given attribute in the data store.
     * @param key attribute to search
     * @param value value to search
     * @return a list of entries or null in case of error.
     */
    List<T> selectWhere(String key, Object value);
    
    /**
     * gets last id value.
     * @return last id inserted.
     */
    long getLastId();
    
}