package cat.proven.leisure.persist;

import cat.proven.leisure.model.Activity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author jose
 */
public class ActivityDao2 {

    private final Properties queries;
    
    public ActivityDao2() {
        this.queries = new Properties();
        initQueries();
    }

    protected Activity fromResultSet(ResultSet rs) throws SQLException {
        Activity e;
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int capacity = rs.getInt("capacity");
        e = new Activity(id, name, capacity);
        return e;
    }

    private void initQueries() {
        queries.setProperty("sAll", "select * from activities");
        queries.setProperty("sWhereId", "select * from activities where id=?");
        queries.setProperty("sWhereName", "select * from activities where name=?");
        queries.setProperty("insert", "insert into activities values (0, ?, ?)");
        queries.setProperty("update", "update activities set name=?, capacity=? where id=?");
        queries.setProperty("delete", "delete from activities where id=?");
    }
    
    public List<Activity> execQuery(String queryName, List<Object> values) throws SQLException {
        List<Activity> result = new ArrayList<>();
        Connection conn = DbConnect.getConnection();
        if (conn != null) {
            String query = queries.getProperty(queryName);
            PreparedStatement st = conn.prepareStatement(query);
            for (int i = 0; i < values.size(); i++) {
                st.setObject(i + 1, values.get(i));
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Activity obj = fromResultSet(rs);
                if (obj != null) {
                    result.add(obj);
                }
            }
        }
        return result;
    }    
    
}
