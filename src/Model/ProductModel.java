
package Model;

import Cache.cProduct;
import DBContext.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductModel extends Model {
    public static final String TABLE = "Product";
    private cProduct product; 

    public cProduct setProduct() {
        return product;
    }

    public void setProduct (cProduct product) {
        this.product = product;
    }
    public boolean updateStockNull(int stockID) {
        try {
            String queryStr = "UPDATE " + ProductModel.TABLE + " SET stock_id = null WHERE "
                    + "stock_id = " + stockID;
            Statement smt = db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean updateCategoryNull(int category_id) {
        try {
            String queryStr = "UPDATE " + ProductModel.TABLE + " SET category_id = null WHERE "
                    + "category_id = " + category_id;
            Statement smt = db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public ResultSet select() {
        try {
            String queryStr = "SELECT * FROM " + ProductModel.TABLE + " LEFT JOIN " +
            "(SELECT id as category_id, name as category_name FROM " + CategoryModel.TABLE + ") categoryTbl " +
            "ON " + ProductModel.TABLE + ".category_id = categoryTbl.category_id";
            Statement smt = db.createStatement();
            ResultSet users = smt.executeQuery(queryStr);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ResultSet select2() {
        try {
            String queryStr = "SELECT * FROM " + ProductModel.TABLE;
            Statement smt = db.createStatement();
            ResultSet users = smt.executeQuery(queryStr);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public <Integer> ResultSet single(Integer v) {
        try {
            String queryStr = "SELECT * FROM " + ProductModel.TABLE + " INNER JOIN " +
            "(SELECT id as category_id, name as category_name FROM " + CategoryModel.TABLE + ") categoryTbl " +
            "ON " + ProductModel.TABLE + ".category_id = categoryTbl.category_id WHERE id = " + v;
            Statement smt = db.createStatement();
            ResultSet users = smt.executeQuery(queryStr);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <String> ResultSet search(String k) {
        return null;
    }

    @Override
    public boolean create(Object data) {
        cProduct xdata = (cProduct) data;
        try {
            String queryStr = "INSERT INTO " + ProductModel.TABLE + "(name, price, category_id, stock_id) VALUES ('" 
                    + xdata.getName()  + "', '" + xdata.getPrice() + "', '" 
                    + xdata.getCategory_id() + "', '" + xdata.getStock_id() + "')";
            Statement smt = db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Object data) {
        try {
            cProduct xdata = (cProduct) data;
            String partialQuery = "";
            ArrayList<String> partialQueryList = new ArrayList<String>();
            boolean dataChange[] = {false, false, false, false};
            String dataOrigin[] = {xdata.getName(), xdata.getPrice() + "", xdata.getCategory_id() + "", xdata.getStock_id() + ""};
            int n = 4, c = 0;
            if (!this.product.getName().equals(xdata.getName())) {
                partialQueryList.add(" name = ? ");
                dataChange[0] = true;
            }
            if (this.product.getPrice() != xdata.getPrice()) {
                partialQueryList.add(" price = ? ");
                dataChange[1] = true;
            }
            if (this.product.getCategory_id() != xdata.getCategory_id()) {
                partialQueryList.add(" category_id = ? ");
                dataChange[2] = true;
            }
            if (this.product.getStock_id() != xdata.getStock_id()) {
                partialQueryList.add(" stock_id = ? ");
                dataChange[3] = true;
            }
            for (String s : partialQueryList) {
                String suffix = "";
                if (c != (partialQueryList.size() - 1)) suffix = ",";
                partialQuery += partialQueryList.get(c++) + suffix;
            }
            String query = "UPDATE " + ProductModel.TABLE + " SET" + partialQuery + "WHERE id = ?";
            PreparedStatement psmt = this.db.prepareStatement(query);
            int j = 1;
            int result = 0;
            boolean allowUpdate = false;
            for (int i = 0; i < n; i++) {
                if (dataChange[i] == true) {
                    allowUpdate = true;
                    break;
                }
            }
            for (int i = 0; i < n; i++) {
                if (dataChange[i]) {
                    psmt.setString(j, dataOrigin[i]);
                    j++;
                }
            }
            psmt.setInt(j, xdata.getId());
            if (allowUpdate) {
                result = psmt.executeUpdate();
            }
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteOne(Integer id) {
        try {
            String queryStr = "DELETE FROM " + ProductModel.TABLE + " WHERE id = " + id;
            Statement smt = this.db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteMany(ArrayList<Integer> IDs) {
        if (IDs.size() != 0) {
            try {
                String queryStr = "DELETE FROM " + ProductModel.TABLE + " WHERE";
                if (IDs.size() == 1) {
                    queryStr += " id = " + IDs.get(0);
                } else {
                    int c = 0;
                    String deleteIDs = "(";
                    for (Integer ID : IDs) {
                        deleteIDs += ID + (c++ == IDs.size() - 1 ? "" : ",");
                    }
                    deleteIDs += ")";
                    queryStr += " id IN " + deleteIDs;
                }
                Statement smt = this.db.createStatement();
                int result = smt.executeUpdate(queryStr);
                return result != 0;
            } catch (SQLException ex) {
                Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public <Integer> ResultSet searchID(Integer id) {
        try {
            String queryStr = "SELECT * FROM " + ProductModel.TABLE + " WHERE id = " + id;
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
