package cat.proven.leisure.persist;

import cat.proven.dbpersist.DbConnector;
import cat.proven.dbpersist.DbDao;
import cat.proven.leisure.model.ActivityUser;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jose Moreno
 */
public class ActivityUserDao extends DbDao<ActivityUser> {
    /**
     * singleton pattern
     */
    private static ActivityUserDao instance;
    
    private ActivityUserDao(DbConnector dbConnector) {
        super(dbConnector);
        initQueries();
    }
    
    /**
     * gets an instance of this class (singleton implementation)
     * @param dbConnector DbConnector object with connection to database properties
     * @return TeamDao singleton instance 
     */
    public static ActivityUserDao getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new ActivityUserDao(dbConnector);
        }
        return instance;
    }
    
    @Override
    protected ActivityUser fromResultSet(ResultSet rs) throws SQLException {
        ActivityUser e;
        long activityId = rs.getLong("activity_id");
        long userId = rs.getLong("user_id");
        e = new ActivityUser(activityId, userId);
        return e; 
    }

    @Override
    protected final void initQueries() {
        queries.setProperty("sAll", "select * from activitiesusers");
        queries.setProperty("sWhereActivityId", "select * from activitiesusers where activity_id=?");
        queries.setProperty("sWhereUserId", "select * from activitiesusers where user_id=?");
        queries.setProperty("insert", "insert into activitiesusers values (?, ?)");
        queries.setProperty("update", "update activitiesusers set activity_id=?, user_id=? where activity_id=? and user_id=?");
        queries.setProperty("delete", "delete from activitiesusers where activity_id=? and user_id=?");
    }

    @Override
    public String getQuery(String queryName) {
        return queries.getProperty(queryName);
    }
        
}
