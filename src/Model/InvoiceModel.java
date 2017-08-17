
package Model;

import Cache.cInvoice;
import DBContext.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvoiceModel extends Model {
    public static final String TABLE = "Invoice";
    private cInvoice invoice; 

    public cInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(cInvoice invoice) {
        this.invoice = invoice;
    }
    
    @Override
    public ResultSet select() {
        try {
            String queryStr = "SELECT * FROM " + InvoiceModel.TABLE;
            Statement smt = db.createStatement();
            ResultSet invoices = smt.executeQuery(queryStr);
            return invoices;
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <Integer> ResultSet single(Integer v) {
        try {
            String queryStr = "SELECT * FROM " + InvoiceModel.TABLE + " WHERE id = " + v;
            Statement smt = db.createStatement();
            ResultSet invoices = smt.executeQuery(queryStr);
            return invoices;
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <String> ResultSet search(String k) {
        return null;
    }

    @Override
    public boolean create(Object data) {
        cInvoice xdata = (cInvoice) data;
        try {
            String queryStr = "INSERT INTO " + InvoiceModel.TABLE + "(payment_method, client_id, user_id, date_create) VALUES ('" 
                    + xdata.getPayment_method()  + "', '" + xdata.getClient_id() + "', '" + xdata.getUser_id() + "', '" + getTime() + "')";
            Statement smt = db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Object data) {
        try {
            cInvoice xdata = (cInvoice) data;
            String partialQuery = "";
            ArrayList<String> partialQueryList = new ArrayList<String>();
            boolean dataChange[] = {false, false, false};
            boolean isChanged = false;
            String dataOrigin[] = {xdata.getPayment_method(), "" + xdata.getClient_id(), "" + xdata.getUser_id()};
            int n = 3, c = 0;
            if (!this.invoice.getPayment_method().equals(xdata.getPayment_method())) {
                partialQueryList.add(" payment_method = ? ");
                dataChange[0] = true;
                isChanged = true;
            }
            if (this.invoice.getClient_id() != xdata.getClient_id()) {
                partialQueryList.add(" client_id = ? ");
                dataChange[1] = true;
                isChanged = true;
            }
            if (this.invoice.getUser_id() != xdata.getUser_id()) {
                partialQueryList.add(" user_id = ? ");
                dataChange[2] = true;
                isChanged = true;
            }
            for (String s : partialQueryList) {
                String suffix = "";
                if (c != (partialQueryList.size() - 1)) suffix = ",";
                partialQuery += partialQueryList.get(c++) + suffix;
            }
            
            String query = "UPDATE " + InvoiceModel.TABLE + " SET" + partialQuery + "WHERE id = ?";
            PreparedStatement psmt = this.db.prepareStatement(query);
            int j = 1;
            for (int i = 0; i < n; i++) {
                if (dataChange[i]) {
                    psmt.setString(j, dataOrigin[i]);
                    j++;
                }
            }
            psmt.setInt(j, xdata.getId());
            int result = 0;
            if (isChanged) result = psmt.executeUpdate();
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteOne(Integer id) {
        try {
            String queryStr = "DELETE FROM " + InvoiceModel.TABLE + " WHERE id = " + id;
            Statement smt = this.db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteMany(ArrayList<Integer> IDs) {
        if (IDs.size() != 0) {
            try {
                String queryStr = "DELETE FROM " + InvoiceModel.TABLE + " WHERE";
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
                Logger.getLogger(InvoiceModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public <Integer> ResultSet searchID(Integer id) {
        try {
            String queryStr = "SELECT * FROM " + InvoiceModel.TABLE + " WHERE id = " + id;
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
