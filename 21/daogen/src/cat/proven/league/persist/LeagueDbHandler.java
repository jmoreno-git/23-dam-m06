package cat.proven.league.persist;

import cat.proven.league.model.Team;
import cat.proven.dbpersist.DbConnector;
import java.io.IOException;
import java.util.List;

import java.sql.Connection;
import java.sql.Savepoint;
import java.sql.SQLException;

/**
 * Handler class for database access
 *
 * @author Jose
 */
public class LeagueDbHandler {

    private final DbConnector dbConnector;
    private final String DBCONNFILE = "files/dbconn.properties";

    public LeagueDbHandler() throws IOException {
        dbConnector = new DbConnector();
        dbConnector.setPropsFile(DBCONNFILE);
    }

    public List<Team> searchTeamAll() throws SQLException {
        TeamDao teamDao = new TeamDao(dbConnector);
        List<Team> result = teamDao.selectAll();
        return result;
    }

    public List<Team> searchTeamByName(String name) throws SQLException {
        TeamDao teamDao = new TeamDao(dbConnector);
        List<Team> result = teamDao.selectWhereName(name);
        return result;
    }

    public Team findTeamById(long id) throws SQLException {
        TeamDao teamDao = new TeamDao(dbConnector);
        Team toSearch = new Team(id);
        Team t = teamDao.select(toSearch);
        return t;
    }

    public int store(Team team) throws SQLException {
        TeamDao teamDao = new TeamDao(dbConnector);
        return teamDao.insert(team);
    }

    public int modify(Team previous, Team team) throws SQLException {
        TeamDao teamDao = new TeamDao(dbConnector);
        return teamDao.update(previous, team);
    }

    public int remove(Team team) throws SQLException {
        TeamDao teamDao = new TeamDao(dbConnector);
        return teamDao.delete(team);
    }

    public boolean transactionStoreAndModifyTeam(Team t, Team u) throws SQLException {
        boolean success = false;
        Connection conn = null;
        Savepoint savePoint = null;
        try {
            int count; //counter returned by update queries (not really used in this example)
            //get a connection
            conn = dbConnector.getConnection();
            //save autocommit initial state
            boolean isAutoCommit = conn.getAutoCommit();
            //disable autocommit
            conn.setAutoCommit(false);
            //save initial point on transaction
            savePoint = conn.setSavepoint();
            //store team
            TeamDao teamDao = new TeamDao(dbConnector);
            teamDao.setConnection(conn);
            count = teamDao.insert(t);
            //get generated primary key
            long key = (long) teamDao.getLastGeneratedKeys().get(0);
            t.setId(key);
            //modify team
            count = teamDao.update(t, u);
            //perform commit
            conn.commit();
            //restore initial autocommit state
            conn.setAutoCommit(isAutoCommit);
            success = true;
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    //roolback to saved point
                    conn.rollback(savePoint);
                    success = false;
                    throw ex;
                }
            } catch (SQLException e) {
                success = false;
                throw e;
            }
        }
        return success;
    }

}
