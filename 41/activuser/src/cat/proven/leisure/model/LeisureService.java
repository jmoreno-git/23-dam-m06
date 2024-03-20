package cat.proven.leisure.model;

import cat.proven.leisure.persist.ActivityListDao;
import cat.proven.leisure.persist.ActivityUserListDao;
import cat.proven.leisure.persist.UserListDao;
import java.util.List;

/**
 *
 * @author Jose
 */
public class LeisureService {
    
    private final ActivityListDao activityDao;
    private final UserListDao userDao;
    private final ActivityUserListDao activityUserDao;

    public LeisureService() {
        activityDao = new ActivityListDao();
        userDao = new UserListDao();
        activityUserDao = new ActivityUserListDao();
    }
    
    public List<Activity> getAllActivities() {
        return activityDao.selectAll();
    }
    
    public List<User> getUsersByActivity(Activity activity) {
        List<User> result = activityUserDao.
                selectWhere("activityId", activity.getId())
                .stream()
                .map((t) -> {
                    return userDao.selectWhere("id", t.getUserId()).get(0);
                })
                .toList();
        return result;
//        List<User> result = new ArrayList<>();
//        List<ActivityUser> auList = activityUserDao.selectWhere(
//              "activityId", activity.getId());
//        for (ActivityUser au : auList) {
//            User u = userDao.selectWhere("id", au.getUserId()).get(0);
//            if (u!=null) {
//                result.add(u);
//            }
//        }
//        return result;
    }
}
