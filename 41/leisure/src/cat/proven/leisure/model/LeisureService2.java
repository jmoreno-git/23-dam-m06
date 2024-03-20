
package cat.proven.leisure.model;

import cat.proven.leisure.persist.ActivityDao;
import cat.proven.leisure.persist.ActivityDao2;
import cat.proven.leisure.persist.ActivityListDao;
import cat.proven.leisure.persist.DbConnect;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class LeisureService2 {
    
    public LeisureService2() {
    }    
    
    public List<Activity> getAllActivities() {
        List<Activity> list = null;
        list = new ActivityListDao().selectAll();
//        try {
            //ActivityDao2 db = new ActivityDao2();
            //list = db.execQuery("sAll", Arrays.asList());
//        } catch (SQLException ex) {
//            Logger.getLogger(LeisureService.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return list;
    }    
}
