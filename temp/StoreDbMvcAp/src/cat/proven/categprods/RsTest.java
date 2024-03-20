package cat.proven.categprods;

import javax.sql.rowset.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cat.proven.categprods.model.persist.DbConnect;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class RsTest {
    
    private CachedRowSet rowSet1;
    private CachedRowSet rowSet2;
    private JoinRowSet joinRowSet;
   
    public static void main(String[] args) throws ClassNotFoundException {
       RsTest main = new RsTest();
       main.start();
    }
 
    private void start() throws ClassNotFoundException {
        try {
            //load database driver
            DbConnect.loadDriver();
            //get a RowSet Factory
            RowSetFactory rsFactory = RowSetProvider.newFactory();
            //
            //create a CachedRowSet
            rowSet1 = rsFactory.createCachedRowSet();
            //configure connection properties for RowSet
            rowSet1.setUsername(DbConnect.USER);
            rowSet1.setPassword(DbConnect.PASSWORD);
            rowSet1.setUrl(DbConnect.BD_URL);
            String query1 = "select * from categories where id in (?, ?)";
            rowSet1.setCommand(query1);
            rowSet1.setInt(1, 2);
            rowSet1.setInt(2, 5);
            //execute query to populate RowSet
            rowSet1.execute();
            //display RowSet content
            System.out.println("RowSet1");
            showRowSet(rowSet1);
            //
            //create a CachedRowSet
            rowSet2 = rsFactory.createCachedRowSet();
            //configure connection properties for RowSet
            rowSet2.setUsername(DbConnect.USER);
            rowSet2.setPassword(DbConnect.PASSWORD);
            rowSet2.setUrl(DbConnect.BD_URL);
            String query2 = "select * from products";
            rowSet2.setCommand(query2);
            //execute query to populate RowSet
            rowSet2.execute();
            //display RowSet content
            System.out.println("RowSet2");
            showRowSet(rowSet2);
            //
            //create a JoinRowSet
            joinRowSet = rsFactory.createJoinRowSet();
            joinRowSet.setJoinType(JoinRowSet.INNER_JOIN);
            //configure connection properties for RowSet
            joinRowSet.setUsername(DbConnect.USER);
            joinRowSet.setPassword(DbConnect.PASSWORD);
            joinRowSet.setUrl(DbConnect.BD_URL);
            //add RowSets to JoinRowSet defining also math colum for join
            joinRowSet.addRowSet(rowSet1, "id");
            joinRowSet.addRowSet(rowSet2, "category_id");
            //display RowSet content
            showRowSet(joinRowSet);
            joinRowSet.first();
            //try to make changes in JoinRowSet
            changeRs(joinRowSet);
            commitToDatabase(joinRowSet);  //NOTE: this doesn't work: cannot stablish a connection to database!
        } catch (SQLException ex) {
            Logger.getLogger(RsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void showRowSet(CachedRowSet rs) throws SQLException {
        RowSetMetaData rsmd = (RowSetMetaData)rs.getMetaData();
        System.out.print("[");
        for (int i=1; i<=rsmd.getColumnCount(); i++) {
            System.out.format("(%s)", rsmd.getColumnName(i));
        }
        System.out.println("]");
        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            sb.append("[");
            for (int i=0; i<rsmd.getColumnCount(); i++) {
                sb.append("(").append(rs.getObject(i+1)).append(")");
            }
            sb.append("]\n");
        }
        System.out.println(sb.toString());
    }

    private void changeRs(JoinRowSet rs) throws SQLException {
        rs.updateString("name", "nova");
        rs.updateRow();
    }
    
   public boolean commitToDatabase(CachedRowSet rs) {
        boolean b;
        try ( Connection conn = getConnection(rs, false)) {
            // propagate changes and close connection
            rs.acceptChanges(conn);
            // reload data.
            rs.execute();
            b = true;
        } catch (SQLException se) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, se);
            b = false;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            b = false;
        }
        return b;
    } 

    public Connection getConnection(RowSet rs, boolean autocommit) throws SQLException {
        Connection conn = DriverManager.getConnection(rs.getUrl(), rs.getUsername(), rs.getPassword());
        conn.setAutoCommit(autocommit);
        return conn;
    }
   
}
