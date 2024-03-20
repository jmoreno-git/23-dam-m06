package cat.proven.leisure.persist;

import cat.proven.dbpersist.DbConnector;
import cat.proven.dbpersist.DbDao;
import cat.proven.leisure.model.Activity;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jose Moreno
 */
public class ActivityDao extends DbDao<Activity>{
    /**
     * singleton pattern
     */
    private static ActivityDao instance;
    
    private ActivityDao(DbConnector dbConnector) {
        super(dbConnector);
        initQueries();
    }
    
    /**
     * gets an instance of this class (singleton implementation)
     * @param dbConnector DbConnector object with connection to database properties
     * @return TeamDao singleton instance 
     */
    public static ActivityDao getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new ActivityDao(dbConnector);
        }
        return instance;
    }
    
    @Override
    protected Activity fromResultSet(ResultSet rs) throws SQLException {
        Activity e;
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int capacity = rs.getInt("capacity");
        e = new Activity(id, name, capacity);
        return e; 
    }

    @Override
    protected final void initQueries() {
        queries.setProperty("sAll", "select * from activities");
        queries.setProperty("sWhereId", "select * from activities where id=?");
        queries.setProperty("sWhereName", "select * from activities where name=?");
        queries.setProperty("insert", "insert into activities values (0, ?, ?)");
        queries.setProperty("update", "update activities set name=?, capacity=? where id=?");
        queries.setProperty("delete", "delete from activities where id=?");
    }

    @Override
    public String getQuery(String queryName) {
        return queries.getProperty(queryName);
    }
    
}
