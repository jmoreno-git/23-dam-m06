package cat.proven.schoolmanager.model.persistance;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author ProvenSoft
 */
public class SchoolDAOXml {

    public SchoolDAOXml() {
    }

    /**
     * Sets a connection to data source
     *
     * @return the connection
     * @throws XQException if connection is refused
     */
    public XQConnection getConnection() throws XQException {
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        xqs.setProperty("user", "dam2usr");
        xqs.setProperty("password", "dam2psw");
        return xqs.getConnection();
    }

}
