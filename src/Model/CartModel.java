
package Model;

import Cache.cCart;
import DBContext.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartModel extends Model {
    public static final String TABLE = "Cart";
    private cCart cart; 

    public cCart getCart() {
        return cart;
    }

    public void setCart(cCart cart) {
        this.cart = cart;
    }
    
    @Override
    public ResultSet select() {
        try {
            String queryStr = "SELECT * FROM " + CartModel.TABLE;
            Statement smt = db.createStatement();
            ResultSet carts = smt.executeQuery(queryStr);
            return carts;
        } catch (SQLException ex) {
            Logger.getLogger(CartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <Integer> ResultSet single(Integer v) {
        try {
            String queryStr = "SELECT * FROM " + CartModel.TABLE + " WHERE id = " + v;
            Statement smt = db.createStatement();
            ResultSet carts = smt.executeQuery(queryStr);
            return carts;
        } catch (SQLException ex) {
            Logger.getLogger(CartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <String> ResultSet search(String k) {
        return null;
    }

    @Override
    public boolean create(Object data) {
        cCart xdata = (cCart) data;
        try {
            String queryStr = "INSERT INTO " + CartModel.TABLE + "(product_id, invoice_id, quantity, unit_price) VALUES ('" 
                    + xdata.getProduct_id()  + "', '" + xdata.getInvoice_id() + "', '" + xdata.getQuantity() + "', '" + xdata.getUnit_price() + "')";
            Statement smt = db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(CartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Object data) {
        try {
            cCart xdata = (cCart) data;
            String partialQuery = "";
            ArrayList<String> partialQueryList = new ArrayList<String>();
            boolean dataChange[] = {false, false, false, false};
            String dataOrigin[] = {"" + xdata.getProduct_id(), "" + xdata.getQuantity(), "" + xdata.getUnit_price()};
            int n = 3, c = 0;
            if (this.cart.getProduct_id() != xdata.getProduct_id()) {
                partialQueryList.add(" product_id = ? ");
                dataChange[0] = true;
            }
            if (this.cart.getQuantity() != xdata.getQuantity()) {
                partialQueryList.add(" quantity = ? ");
                dataChange[1] = true;
            }
            if (this.cart.getUnit_price() != xdata.getUnit_price()) {
                partialQueryList.add(" unit_price = ? ");
                dataChange[2] = true;
            }
            for (String s : partialQueryList) {
                String suffix = "";
                if (c != (partialQueryList.size() - 1)) suffix = ",";
                partialQuery += partialQueryList.get(c++) + suffix;
            }
            String query = "UPDATE " + CartModel.TABLE + " SET" + partialQuery + "WHERE product_id = ? AND invoice_id = ? ";
            PreparedStatement psmt = this.db.prepareStatement(query);
            int j = 1;
            for (int i = 0; i < n; i++) {
                if (dataChange[i]) {
                    psmt.setString(j, dataOrigin[i]);
                    j++;
                }
            }
            psmt.setInt(j++, this.cart.getProduct_id());
            psmt.setInt(j, this.cart.getInvoice_id());
            int result = psmt.executeUpdate();
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(CartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteOne(Integer id) {
        try {
            String queryStr = "DELETE FROM " + CartModel.TABLE + " WHERE id = " + id;
            Statement smt = this.db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(CartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteMany(ArrayList<Integer> IDs) {
        if (IDs.size() != 0) {
            try {
                String queryStr = "DELETE FROM " + CartModel.TABLE + " WHERE";
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
                Logger.getLogger(CartModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public boolean deleteByInvoice(int invoiceID) throws SQLException {
        String queryStr = "DELETE FROM " + CartModel.TABLE + " WHERE invoice_id = "  + invoiceID;
        Statement smt = this.db.createStatement();
        int result = smt.executeUpdate(queryStr);
        return result != 0;
    }
    public boolean deleteBySuperKeys(int invoiceID, int productID) throws SQLException {
        String queryStr = "DELETE FROM " + CartModel.TABLE
                + " WHERE invoice_id = "  + invoiceID
                + " AND product_id = " + productID;
        Statement smt = this.db.createStatement();
        int result = smt.executeUpdate(queryStr);
        return result != 0;
    }
    public <Integer> ResultSet searchID(Integer id) {
        try {
            String queryStr = "SELECT * FROM " + CartModel.TABLE + " WHERE id = " + id;
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(CartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ResultSet selectCol(String col, String val) {
        try {
            String queryStr = "SELECT * FROM " + CartModel.TABLE + " WHERE " + col + " = " + val;
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(CartModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
