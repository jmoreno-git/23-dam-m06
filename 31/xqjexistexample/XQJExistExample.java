import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.xquery.*;
import net.xqj.exist.ExistXQDataSource;

/**
 * Example of XQJ API use
 */
public class XQJExistExample {

    public static void main(String[] args) {
        XQJExistExample xqjee = new XQJExistExample();
        xqjee.run();
    }

    public void run() {
        try {
            //example 1
            System.out.println("Example 1: Hello world.");
            System.out.println("---------------------------------------------");
            helloWorld();
            //example 2
            System.out.println("Example 2: XQExpression with xpath.");
            System.out.println("---------------------------------------------");
            String query = "data(doc('dam2/cdcatalog.xml')/catalog/cd/title)";
            execQuery(query);
            //example 3
            System.out.println("Example 3: XQExpression with XQuery.");
            System.out.println("---------------------------------------------");
            query = "for $x in doc('dam2/cdcatalog.xml')/catalog/cd "
                    + "where $x/year > 1990 "
                    + "order by $x/title "
                    + "return data($x/title)";
            execQuery(query);
            //example 4
            System.out.println("Example 4: XQPreparedExpression with XQuery.");
            System.out.println("---------------------------------------------");
            execPreparedQuery();
            //example 5
            System.out.println("Example 5: XQExpression to retrieve elements");
            System.out.println("---------------------------------------------");
            query = "for $x in doc('dam2/cdcatalog.xml')/catalog/cd "
                    + "where $x/year > 1997 "
                    + "order by $x/title "
                    + "return $x";
            execElementsQuery(query);
            
            //example 6
//            System.out.println("Example 6: Insert an element.");
//            System.out.println("---------------------------------------------");
//            execInsert();
            
            //example 7
//            System.out.println("Example 7: Remove an element.");
//            System.out.println("---------------------------------------------");
//            execRemove();
            
        } catch (XQException ex) {
            Logger.getLogger(XQJExistExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void helloWorld() throws XQException {

        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");

        XQConnection conn = xqs.getConnection();
        XQPreparedExpression xqpe
                = conn.prepareExpression("declare variable $x as xs:string external; $x");
        xqpe.bindString(new QName("x"), "Hello World!", null);
     

        XQResultSequence rs = xqpe.executeQuery();

        showResult(rs);
        conn.close();
    }

	/**
	 * shows a result sequence
	 */
    public void showResult(XQResultSequence rs) throws XQException {
        while (rs.next()) {
            System.out.println(rs.getItemAsString(null));
        }
    }

    private void execQuery(String query) throws XQException {
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        XQConnection conn = xqs.getConnection();

        XQExpression xqe = conn.createExpression();
        XQResultSequence rs = xqe.executeQuery(query);

        showResult(rs);
        conn.close();
    }

    public void execPreparedQuery() throws XQException {

        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        XQConnection conn = xqs.getConnection();

        String query = "declare variable $z as xs:integer external; "
                + "for $x in doc('dam2/cdcatalog.xml')/catalog/cd "
                + "where $x/year > $z "
                + "order by $x/title "
                + "return data($x/title)";

        XQPreparedExpression xqpe = conn.prepareExpression(query);
        xqpe.bindInt(new QName("z"), 1990, null);
        XQResultSequence rs = xqpe.executeQuery();

        showResult(rs);
        conn.close();
    }
    
    private void execElementsQuery(String query) throws XQException {
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        
        XQConnection conn = xqs.getConnection();
        XQExpression xqe = conn.createExpression();
        XQResultSequence rs = xqe.executeQuery(query);
      
        showXmlResult(rs);
        conn.close();
    }
 
    /**
     * shows a result sequence
     */
    public void showXmlResult(XQResultSequence rs) throws XQException {
        CdCatalogContentHandler handler = new CdCatalogContentHandler();
        rs.writeSequenceToSAX(handler);
        List<Cd> data = handler.getCdList();
        data.forEach(System.out::println);
    }    

    
    
    private void execInsert() throws XQException {
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        xqs.setProperty("user", "dam2usr");
        xqs.setProperty("password", "dam2psw");
        
        
        //XQConnection conn = xqs.getConnection("admin", "admin"); // user, password
        XQConnection conn = xqs.getConnection();
        
        String query = "update insert "+ 
                        "<cd> "+
                        "<title>New title</title> "+
                            "<artist>New artist</artist> "+
                            "<country>New country</country> "+
                            "<company>New company</company> "+
                            "<price>20.00</price> "+
                            "<year>2000</year> "+
                        "</cd> "+
                    "into /catalog";
        
        XQExpression xqe = conn.createExpression();
        
        xqe.executeCommand(query);
        
        conn.close();
        
    }
    
    private void execRemove() throws XQException {
        
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        xqs.setProperty("user", "dam2usr");
        xqs.setProperty("password", "dam2psw");
        
        //XQConnection conn = xqs.getConnection("admin", "admin"); // user, password
        XQConnection conn = xqs.getConnection();
        
        String query = "for $e in doc('dam2/cdcatalog.xml')/catalog/cd "
                       + "where $e/title = 'New title' "
                       + "return update delete $e";
        
        XQExpression xqe = conn.createExpression();
        
        xqe.executeCommand(query);
        
        conn.close();
    }
        
}
