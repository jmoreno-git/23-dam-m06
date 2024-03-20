
package cat.proven.warehouse.model.persist;

import cat.proven.utils.exceptions.OpResult;
import cat.proven.utils.exceptions.PersistException;
import cat.proven.warehouse.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO for product
 * @author ProvenSoft
 */
public class ProductDao {
    private final DbConnect dbConnect;

    public ProductDao() {
        this.dbConnect = new DbConnect();
    }
    
    /**
     * reads product data from resultset row
     * @param rs the resultset to read from
     * @return product or null in case of error
     * @throws PersistException in case of database error
     */
    private Product fromResultSet(ResultSet rs) throws PersistException {
        Product prod = null;
        //TODO
        try {
            long id = rs.getLong("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            long containerId = rs.getLong("container_id");
            Product container = (containerId == 0) ? null : new Product(containerId);
            prod = new Product(id, code, name, price, container);
        } catch (SQLException ex) {
            throw new PersistException("Sql error fromResultSet", OpResult.DB_RS2OBJ.getCode());
        }
        return prod;
        //END TODO
    }

    /**
     * selects all products from database
     * @return list of products
     * @throws PersistException in case of database error
     */
    public List<Product> selectAll() throws PersistException {
        List<Product> result = new ArrayList<>();
        try (Connection conn = dbConnect.getConnection()) {
            //TODO
            if (conn != null) {
                String query = "select * from products";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Product p = fromResultSet(rs);
                    if (p != null) {
                        result.add(p);
                    }
                }
            } else {
                throw new PersistException("No connection to database", OpResult.DB_NOCONN.getCode());
            }
            //END TODO
        } catch (SQLException ex) {
            throw new PersistException("Sql error selecting all products", OpResult.DB_SELERR.getCode());
        }
        return result;
    }
       
    /**
     * selects product from database with given code
     * @param code the code to search
     * @return product or null if not found
     * @throws PersistException in case of database error
     */
    public Product selectWhereCode(String code) throws PersistException {
        Product prod = null;
        //TODO
        try (Connection conn = dbConnect.getConnection()) {
            if (conn != null) {
                String query = "select * from products where code = ?";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, code);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    prod = fromResultSet(rs);
                }
            } else {
                throw new PersistException("No connection to database", OpResult.DB_NOCONN.getCode());
            }
        } catch (SQLException ex) {
            throw new PersistException("Sql error selecting product where code", OpResult.DB_SELERR.getCode());
        }        
        //END TODO
        return prod;
    }
    
    /**
     * inserts a new product to database
     * @param product the product to insert
     * @return number of products inserted
     * @throws PersistException in case of database error
     */
    public int insert(Product product) throws PersistException {
        int result = 0;
        //TODO
        try (Connection conn = dbConnect.getConnection()) {
            if (conn != null) {
                String query = "insert into products values (null, ?, ?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, product.getCode());
                st.setString(2, product.getName());
                st.setDouble(3, product.getPrice());
                Product container = product.getContainer();
                if (container != null) {
                    st.setLong(4, product.getContainer().getId());
                } else {
                    st.setNull(4, java.sql.Types.INTEGER);
                }
                result = st.executeUpdate();
            } else {
                throw new PersistException("No connection to database", OpResult.DB_NOCONN.getCode());
            }
        } catch (SQLException ex) {
            //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage());
            throw new PersistException("Sql error inserting product", OpResult.DB_INSERR.getCode());
        } 
        //END TODO
        return result;
    }   
    
    /**
     * selects a product given its container
     * @param product the container to search
     * @return list of products
     * @throws PersistException in case of database error
     */
    public List<Product> selectWhereContainer(Product product) throws PersistException {
        List<Product> result = new ArrayList<>();
        //TODO
        try (Connection conn = dbConnect.getConnection()) {
            if (conn != null) {
                String query = "select * from products where container_id = ?";
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, product.getId());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Product p = fromResultSet(rs);
                    if (p != null) {
                        result.add(p);
                    }
                }
            } else {
                throw new PersistException("No connection to database", OpResult.DB_NOCONN.getCode());
            }
        } catch (SQLException ex) {
            throw new PersistException("Sql error selecting product where code", OpResult.DB_SELERR.getCode());
        }        
        //END TODO
        return result;
    }
    
    //TODO
    /**
     * selects a product given its id
     * @param product the product to select
     * @return product found or null if not found
     * @throws PersistException in case of database error
     */
    public Product select(Product product) throws PersistException {
        Product prod = null;
        try (Connection conn = dbConnect.getConnection()) {
            if (conn != null) {
                String query = "select * from products where id = ?";
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, product.getId());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    prod = fromResultSet(rs);
                }
            } else {
                throw new PersistException("No connection to database", OpResult.DB_NOCONN.getCode());
            }
        } catch (SQLException ex) {
            throw new PersistException("Sql error selecting product where id", OpResult.DB_SELERR.getCode());
        }        
        return prod;
    }    
    //END TODO
    
}
