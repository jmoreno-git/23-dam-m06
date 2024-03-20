package cat.proven.leisure.persist;

import cat.proven.dbpersist.DbConnector;
import cat.proven.dbpersist.DbDao;
import cat.proven.leisure.model.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jose Moreno
 */
public class UserDao extends DbDao<User> {
    /**
     * singleton pattern
     */
    private static UserDao instance; 
    
    private UserDao(DbConnector dbConnector) {
        super(dbConnector);
        initQueries();
    }
    
    /**
     * gets an instance of this class (singleton implementation)
     * @param dbConnector DbConnector object with connection to database properties
     * @return TeamDao singleton instance 
     */
    public static UserDao getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new UserDao(dbConnector);
        }
        return instance;
    }    
    
    @Override
    protected User fromResultSet(ResultSet rs) throws SQLException {
        User e;
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        e = new User(id, name, age);
        return e;
    }

    @Override
    protected final void initQueries() {
        queries.setProperty("sAll", "select * from users");
        queries.setProperty("sWhereId", "select * from users where id=?");
        queries.setProperty("sWhereName", "select * from users where name=?");
        queries.setProperty("sWhereAgeBetween", "select * from users where age>=? and age<?");
        queries.setProperty("insert", "insert into users values (0, ?, ?)");
        queries.setProperty("update", "update users set name=?, age=? where id=?");
        queries.setProperty("delete", "delete from users where id=?");

    }

    @Override
    public String getQuery(String queryName) {
        return queries.getProperty(queryName);
    }
}
