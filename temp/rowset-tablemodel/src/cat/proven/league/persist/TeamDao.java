package cat.proven.league.persist;


import cat.proven.league.model.Team;
import cat.proven.dbpersist.DbConnector;
import cat.proven.dbpersist.DbDao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * DAO for Team objects
 * @author Jose
 */
public class TeamDao extends DbDao<Team> {
    /**
     * singleton pattern
     */
    private static TeamDao instance;

    /**
     * constructor (you should make it private if singleton pattern is wanted)
     * @param dbConnector DbConnector object with connection to database properties
     */
    public TeamDao(DbConnector dbConnector) {
        super(dbConnector);
        initQueries();
    }

    @Override
    protected void initQueries() {
        queries.setProperty("sAll", "select * from teams");
        queries.setProperty("sWhereId", "select * from teams where id=?");
        queries.setProperty("sWhereName", "select * from teams where name=?");
        queries.setProperty("insert", "insert into teams values (0, ?, ?, ?, ?)");
        queries.setProperty("update", "update teams set name=?, coach=?, category=?, budget=? where id=?");
        queries.setProperty("delete", "delete from teams where id=?");
    }
    
    /**
     * gets an instance of this class (singleton implementation)
     * @param dbConnector DbConnector object with connection to database properties
     * @return TeamDao singleton instance 
     * @throws IOException 
     */
    public static TeamDao getInstance(DbConnector dbConnector) throws IOException {
        if (instance == null) {
            instance = new TeamDao(dbConnector);
        }
        return instance;
    }
    
    @Override
    protected Team fromResultSet(ResultSet rs) throws SQLException {
        Team e;
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String coach = rs.getString("coach");
        String category = rs.getString("category");
        double budget = rs.getDouble("budget");
        e = new Team(id, name, coach, category, budget);
        return e;        
    }
    
    public List<Team> selectAll() throws SQLException {
        return execQuery("sAll", Arrays.asList());
    }
    
    public Team select(Team team) throws SQLException {
        Team result = null;
        List<Team> data = execQuery("sWhereId", Arrays.asList(team.getId()));
        if (data != null) {
            if (data.size()==1) {
                result = data.get(0);
            }
        }
        return result;
    }
    
    public List<Team> selectWhereName(String name) throws SQLException {
        return execQuery("sWhereName", Arrays.asList(name));
    }
    
    public int insert(Team team) throws SQLException {
        return execUpdate(
            "insert", 
            Arrays.asList(
                    team.getName(), 
                    team.getCoach(), 
                    team.getCategory(), 
                    team.getBudget()
            ));
    }

    public int delete(Team team) throws SQLException {
        return execUpdate(
            "delete", 
            Arrays.asList(
                    team.getId()
            ));
    }
    
    public int update(Team current, Team team) throws SQLException {
        return execUpdate(
            "update", 
            Arrays.asList(
                    team.getName(), 
                    team.getCoach(), 
                    team.getCategory(), 
                    team.getBudget(),
                    current.getId()
            ));        
    }
}