package cat.proven.persist;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for persistence in a List.
 * Implements singleton pattern.
 * @author Jose Moreno
 * @param <T> type object to make persistent.
 */
public class DaoList<T> implements DaoInterface<T> {
    
    private static DaoList instance;
    private final List<T> data;
    private final Class<T> type;
    private static long lastId;
    
    public DaoList(Class<T> cls)  {
        data = new ArrayList<>(); 
        type= cls;
        lastId = 0;
    }   
    
    @Override
    public T find(T entity) {
        T entityFound;
        int index = data.indexOf(entity);
        if (index >= 0) {
            entityFound = data.get(index);
        }
        else {
            entityFound = null;
        }
        return entityFound;    
    }

    @Override
    public int insert(T entity) {
        int rowsAffected;
        boolean alreadyExists = data.contains(entity);
        if (alreadyExists) {
            rowsAffected = 0;
        }
        else {
            boolean success = data.add(entity);
            if (success) {
                rowsAffected = 1;
                lastId++;
            }
            else {
                rowsAffected = 0;
            }
        }
        return rowsAffected;    
    }

    @Override
    public int update(T oldEntity, T newEntity) {
        System.out.println("Old:"+oldEntity);
        System.out.println("New:"+newEntity);
        int rowsAffected;
        int index = data.indexOf(oldEntity); //ckeck existance.
        if (index >= 0) {
            if (!data.contains(newEntity)) { //avoid duplicates.
                data.set(index, newEntity);
                rowsAffected = 1;                
            } else {
                rowsAffected = 0;
            }
        }
        else {
            rowsAffected = 0;
        }
        return rowsAffected;    
    }

    @Override
    public int delete(T entity) {
        int rowsAffected;
        if (data.contains(entity)) { //ckeck existance.
            rowsAffected = data.remove(entity)?1:0;
        }
        else {
            rowsAffected = 0;
        }
        return rowsAffected;    
    }

    @Override
    public List<T> selectAll() {
        return (List<T>) data;
    }

    @Override
    public List<T> selectWhere(String key, Object value) {
        List<T> result;
        Class c = type;
        try {
            Field f = c.getDeclaredField(key);
            f.setAccessible(true);
            result = new ArrayList<>();
            for (T e: data) {
                if (f.get(e).equals(value)) {
                    result.add(e);
                }
            } 
            f.setAccessible(false);
        } catch (NoSuchFieldException | SecurityException ex) {
            result = null;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            result = null;
        }
        return result;
    }

    @Override
    public long getLastId() {
        return lastId;
    }
    
}