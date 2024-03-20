package cat.proven.leisure.model;

import cat.proven.leisure.persist.ActivityListDao;
import cat.proven.leisure.persist.ActivityUserListDao;
import cat.proven.leisure.persist.UserListDao;
import java.util.List;

/**
 *
 * @author ProvenSoft
 */
public class LeisureService {
    
    private ActivityListDao activityDao;
    private UserListDao userDao;
    private ActivityUserListDao activityUserDao;

    public LeisureService() {
        activityDao = new ActivityListDao();
        userDao = new UserListDao();
        activityUserDao = new ActivityUserListDao();
    }
    
    public List<Activity> getAllActivities() {
        ActivityListDao aDao = new ActivityListDao();
        return aDao.selectAll();
    }
    
    public List<User> getUsersByActivity(Activity activity) {
        List<User> result = activityUserDao.selectWhere("activityId", activity.getId())
                .stream()
                .map((t) -> {
                    return userDao.selectWhere("id", t.getUserId()).get(0);
                })
                .toList();
        return result;
    }
    
     public List<User> getUsersByAgeLimits(int lower, int upper) {
         List<User> result =
            new UserListDao().selectUsersByAgeLimits(
                    lower, upper);
         return result;
     }
}
