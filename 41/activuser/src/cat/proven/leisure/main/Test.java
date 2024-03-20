
package cat.proven.leisure.main;

import cat.proven.leisure.model.Activity;
import cat.proven.leisure.model.LeisureService;
import cat.proven.leisure.persist.ActivityListDao;
import cat.proven.leisure.persist.ActivityUserListDao;
import cat.proven.leisure.persist.UserListDao;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Test {

    public static void main(String[] args) {
//        ActivityListDao ald = new ActivityListDao();
//        List<Activity> allAct = ald.selectAll();
//        allAct.forEach(System.out::println);
//        //
//        (new UserListDao())
//                .selectAll()
//                .forEach(System.out::println);
//        //
//        (new ActivityUserListDao())
//                .selectAll()
//                .forEach(System.out::println);
//        //
        LeisureService dataService = new LeisureService();
        dataService
            .getUsersByActivity(new Activity(1))
            .forEach(System.out::println);
        
    }
    
}
