package teamtable;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.sql.ResultSetMetaData;
import javax.sql.RowSet;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Jose
 */
public class TeamTableRowsetModel extends DefaultTableModel {

    private RowSet rowset;
    private ResultSetMetaData metadata;

    private int numCols;
    private int numRows;
    private String[] columnNames;

    public TeamTableRowsetModel(RowSet rowset) throws SQLException {
        this.rowset = rowset;
        this.rowset.beforeFirst();
        //retrieve metadata
        this.metadata = rowset.getMetaData();
        //retrieve number of columns
        this.numCols = metadata.getColumnCount();
        //retrieve number of rows.
        this.rowset.beforeFirst();
        this.numRows = 0;
        while (this.rowset.next()) {
            this.numRows++;
        }
        this.rowset.beforeFirst();
        //retrieve column names
        this.columnNames = new String[this.numCols];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = metadata.getColumnName(i + 1);
        }
    }

    public void addEventHandlersToRowSet(RowSetListener listener) {
        this.rowset.addRowSetListener(listener);
    }

    public void close() {
        try {
            rowset.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Automatically close when we're garbage collected
     */
    @Override
    protected void finalize() {
        close();
    }

    @Override
    public int getRowCount() {
        return numRows;
    }

    @Override
    public int getColumnCount() {
        return numCols;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> clazz = Object.class;
        if (numRows > 0) {
            clazz = getValueAt(0, columnIndex).getClass();
        }
        return clazz;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;  //TODO change if necessary
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            this.rowset.absolute(rowIndex + 1);
            Object o = this.rowset.getObject(columnIndex + 1);
            if (o == null) {
                return null;
            } else {
                return o;
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            this.rowset.absolute(rowIndex + 1);
            this.rowset.updateObject(columnIndex+1, aValue);
            this.rowset.updateRow();
            //((CachedRowSet)this.rowset).acceptChanges();
            //((CachedRowSet)this.rowset).commit();
        } catch (SQLException ex) {
            Logger.getLogger(TeamTableRowsetModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        super.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        super.removeTableModelListener(l);
    }

}
