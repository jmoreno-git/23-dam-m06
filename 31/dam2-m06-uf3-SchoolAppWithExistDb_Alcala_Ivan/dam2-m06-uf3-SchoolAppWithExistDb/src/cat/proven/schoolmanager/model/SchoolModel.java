package cat.proven.schoolmanager.model;

import cat.proven.schoolmanager.model.persistance.SchoolDAOXml;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

/**
 *
 * @author ProvenSoft
 */
public class SchoolModel {

    private final SchoolDAOXml schoolDAO;

    public SchoolModel() {
        schoolDAO = new SchoolDAOXml();
    }

    public void execQuery(String query) throws Exception {
        XQConnection conn = schoolDAO.getConnection();

        XQPreparedExpression xqpe = conn.prepareExpression(query);
        xqpe.bindInt(new QName("z"), 1990, null);
        XQResultSequence rs = xqpe.executeQuery();

        showResult(rs);
        conn.close();
    }

    public void execAlter(String query) throws Exception {
        XQConnection conn = schoolDAO.getConnection();

        XQExpression xqe = conn.createExpression();

        xqe.executeCommand(query);
        conn.close();
    }

    public void showResult(XQResultSequence rs) throws Exception {

        while (rs.next()) {
            System.out.println(rs.getItemAsString(null));
            if (!rs.next()) {
                System.out.println("There is no data");
            } else {

            }
        }

    }

    public String inputStringScanner() {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine();
        return content;
    }

    public int inputIntScanner() {
        Scanner input = new Scanner(System.in);
        int content = input.nextInt();
        return content;
    }

}
