package cat.proven.leisure.persist;

import cat.proven.dbpersist.DaoList;
import cat.proven.leisure.model.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author José Moreno
 */
public class UserListDao extends DaoList<User> {
    
    public UserListDao() {
        super(User.class);
        loadData();
    }
    
    private void loadData() {
        data = IntStream
                .range(1, 20)
                .mapToObj((value) -> {
                    return new User(
                            value,
                            String.format("Usr%02d", value),
                            20 + value
                    );
                })
                .collect(Collectors.toList());
    }  
    
    public List<User> selectUsersByAgeLimits(
            int low, int up
    ) {
        return data.stream()
                .filter((t) -> {
                    return (t.getAge()>low && t.getAge()<up);
                })
                .toList();
    }
}
