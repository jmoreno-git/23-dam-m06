package cat.proven.leisure.persist;

import cat.proven.dbpersist.DaoList;
import cat.proven.leisure.model.ActivityUser;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        data = IntStream
                .range(1, 6)
                .mapToObj((value) -> {
                    return new ActivityUser(
                            value,
                            rnd.nextInt(1, 15)
                    );
                })
                .collect(Collectors.toList());
    }
    
}
