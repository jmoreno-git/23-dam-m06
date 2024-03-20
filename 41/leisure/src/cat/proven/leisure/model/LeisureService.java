package cat.proven.leisure.model;

import cat.proven.dbpersist.DbConnector;
import cat.proven.leisure.persist.ActivityDao;
import cat.proven.leisure.persist.ActivityUserDao;
import cat.proven.leisure.persist.UserDao;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Jose Moreno
 */
public class LeisureService {
    private static DbConnector dbConnector = null;
    private final String DBCONNFILE = "files/dbconn.properties";

    public LeisureService() {
        dbConnector = DbConnector.getInstance(DBCONNFILE);
    }
    
    public List<Activity> getAllActivities() {
        List<Activity> list = null;
        try {
            ActivityDao db = ActivityDao.getInstance(dbConnector);
            list = db.execQuery("sAll", Arrays.asList());
        } catch (SQLException ex) {
            Logger.getLogger(LeisureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<User> getUsersByActivity(Activity activity) {
        List<User> list = null;
        try {
            ActivityUserDao dbAU = ActivityUserDao.getInstance(dbConnector);
            List<ActivityUser> lau = dbAU.execQuery(
                    dbAU.getQuery("sWhereActivityId"), 
                    Arrays.asList(activity.getId()));
            UserDao dbU = UserDao.getInstance(dbConnector);
            list = lau.stream()
                    .filter((t) -> t.getActivityId()==activity.getId())
                    .map((t) -> {
                        User u = null;
                        try {
                            u = dbU.execQuery(
                                    "sId",
                                    Arrays.asList(t.getUserId())).get(0);
                        } catch (SQLException ex) {
                            u = null;
                        }
                        return u;
                    })
                    .collect(Collectors.toList());
        } catch (SQLException ex) {
            Logger.getLogger(LeisureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;       
    }
    
    public List<User> getUsersByAge(int lowerLimit, int upperLimit) {
        List<User> list = null;
        try {
            UserDao db = UserDao.getInstance(dbConnector);
            list = db.execQuery(
                    db.getQuery("sWhereAgeBetween"),
                    Arrays.asList(lowerLimit, upperLimit)
            );
        } catch (SQLException ex) {
            Logger.getLogger(LeisureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;       
    }
}
