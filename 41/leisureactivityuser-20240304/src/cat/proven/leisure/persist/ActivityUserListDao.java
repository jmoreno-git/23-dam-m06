
package cat.proven.leisure.persist;

import cat.proven.dbpersist.DaoList;
import cat.proven.leisure.model.ActivityUser;
import java.util.Random;

/**
 *
 * @author José Moreno
 */
public class ActivityUserListDao extends DaoList<ActivityUser> {
    
    public ActivityUserListDao() {
        super(ActivityUser.class);
        loadData();
    }

    private void loadData() {
        Random rnd = new Random();
        for (int i=1; i<6; i++ ) {
            for (int j=1; j<rnd.nextInt(2, 15); j++) {
                insert(
                        new ActivityUser(
                                i, 
                                rnd.nextInt(1, 10)));
            }
        }
//        data = IntStream
//                .range(1, 6)
//                .mapToObj((value) -> {
//                    return new ActivityUser(
//                            value,
//                            rnd.nextInt(1, 15)
//                    );
//                })
//                .collect(Collectors.toList());
    }
    
}
