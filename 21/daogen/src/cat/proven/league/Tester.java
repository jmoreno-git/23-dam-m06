package cat.proven.league;

import cat.proven.league.persist.LeagueDbHandler;
import cat.proven.league.model.Team;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * test of generic dao implementation including transactions
 * @author Jose
 */
public class Tester {

    public static void main(String[] args) {
        Tester instance = new Tester();
        instance.run();
    }
    
    private void run() {
        try {
            int count;
            Team t, u;
            //get a database access controller object
            LeagueDbHandler database = new LeagueDbHandler();
            //find all from database
            database.searchTeamAll().forEach(System.out::println);
            //find by name from database
            database.searchTeamByName("team04").forEach(System.out::println);
            //find one by id
            System.out.println(database.findTeamById(1));
//            //store new
//            t = new Team(0, "team20", "coach20", "cat20", 2020.0);
//            count = database.store(t);
//            System.out.println("Store result: "+count);
//            //modify one
//            t = new Team(9);
//            u = new Team(0, "team99", "coach99", "cat99", 2099.0);
//            count = database.modify(t, u);
//            System.out.println("Modify result: "+count);
//            //delete one
//            t = new Team(8);
//            count = database.remove(t);
//            System.out.println("Remove result: "+count);
            //transaction store and modify
            t = new Team(0, "team22", "coach22", "cat22", 2022.0);
            u = new Team(0, "team42", "coach32", "cat32", 2032.0);
            boolean success = database.transactionStoreAndModifyTeam(t, u);
            System.out.println("Transaction: "+success);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, "SQLState: {0}. Message: {1}", new Object[]{ex.getSQLState(), ex.getMessage()});
        }        
    }
    
}