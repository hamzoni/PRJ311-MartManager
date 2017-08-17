
package Model;

import Cache.cStock;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StockModel extends Model {
    public static final String TABLE = "Stock";
    private cStock xstock;

    public cStock getXstock() {
        return xstock;
    }

    public void setXstock(cStock xstock) {
        this.xstock = xstock;
    }
    
    public ResultSet getProductsIDs(int stock_id) {
        try {
            String queryStr = "SELECT id FROM " + ProductModel.TABLE + " WHERE stock_id = " + stock_id;
            Statement smt = db.createStatement();
            ResultSet prd_ids = smt.executeQuery(queryStr);
            return prd_ids;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean importStock(int stockID, int newRemaining, String lastImportDate) {
        try {
            String queryStr = "UPDATE " + StockModel.TABLE + " SET remaining = ?, last_import_date = ? WHERE id = ?";
            PreparedStatement smt = db.prepareStatement(queryStr);
            
            smt.setInt(1,newRemaining);
            smt.setString(2, lastImportDate);
            smt.setInt(3,stockID);
            int result = smt.executeUpdate();
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(StockModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean exportStock(int stockID, int newRemaining, int newStockout, String lastExportDate) {
        try {
            String expDate = ", last_export_date = ?";
            if (lastExportDate == null) expDate = "";
            String queryStr = "UPDATE " + StockModel.TABLE + " SET remaining = ?, stock_out = ? " + expDate + " WHERE id = ?";
            PreparedStatement smt = db.prepareStatement(queryStr);
            int i = 0;
            smt.setInt(++i,newRemaining);
            smt.setInt(++i,newStockout);
            if (lastExportDate != null) smt.setString(++i, lastExportDate);
            smt.setInt(++i,stockID);
            int result = smt.executeUpdate();
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(StockModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    @Override
    public ResultSet select() {
        try {
            String queryStr = "SELECT * FROM " + StockModel.TABLE;
            Statement smt = db.createStatement();
            ResultSet stocks = smt.executeQuery(queryStr);
            return stocks;
        } catch (SQLException ex) {
            Logger.getLogger(StockModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <Integer> ResultSet single(Integer v) {
        try {
            String queryStr = "SELECT * FROM " + StockModel.TABLE + " WHERE id = " + v;
            Statement smt = db.createStatement();
            ResultSet users = smt.executeQuery(queryStr);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(StockModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <String> ResultSet search(String k) {
        return null;
    }

    @Override
    public boolean create(Object data) {
        cStock xdata = (cStock) data;
        try {
            String queryStr = "INSERT INTO " + StockModel.TABLE 
                    + "(name, cost, origin, producer, remaining, last_import_date, last_export_date) VALUES ('" 
                    + xdata.getName() + "','" + xdata.getCost() + "','"
                    + xdata.getOrigin() + "','" + xdata.getProducer() + "','"
                    + xdata.getRemaining() + "','" + getTime() + "','"
                    + getTime() + "')";
            Statement smt = db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(StockModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Object data) {
        try {
            cStock xdata = (cStock) data;
            String partialQuery = "";
            ArrayList<String> partialQueryList = new ArrayList<String>();
            boolean dataChange[] = {false, false, false, false, false, false, false};
            boolean isChanged = false;
            int n = 7, c = 0;
                    
            String crrDateImport = dateToSQLString(this.xstock.getLastImportedDate());
            String crrDateExport = dateToSQLString(this.xstock.getLastExportedDate());
            String dateImport = dateToSQLString(xdata.getLastImportedDate());
            String dateExport = dateToSQLString(xdata.getLastExportedDate());
            
            String dataOrigin[] = {
                xdata.getName(), xdata.getCost() + "", xdata.getOrigin(), 
                xdata.getProducer(), xdata.getRemaining() + "", 
                dateImport , dateExport};
            
            if (!this.xstock.getName().equals(xdata.getName())) {
                partialQueryList.add(" name = ? ");
                dataChange[0] = true;
                isChanged = true;
            }
            if (this.xstock.getCost() != xdata.getCost()) {
                partialQueryList.add(" cost = ? ");
                dataChange[1] = true;
                isChanged = true;
            }
            if (!this.xstock.getOrigin().equals(xdata.getOrigin())) {
                partialQueryList.add(" origin = ? ");
                dataChange[2] = true;
                isChanged = true;
            }
            if (!this.xstock.getProducer().equals(xdata.getProducer())) {
                partialQueryList.add(" producer = ? ");
                dataChange[3] = true;
                isChanged = true;
            }
            if (this.xstock.getRemaining() != xdata.getRemaining()) {
                partialQueryList.add(" remaining = ? ");
                dataChange[4] = true;
                isChanged = true;
            }
            if (!crrDateImport.equals(dateImport)) {
                partialQueryList.add(" last_import_date = ? ");
                dataChange[5] = true;
                isChanged = true;
            }
            if (!crrDateExport.equals(dateExport)) {
                partialQueryList.add(" last_export_date = ? ");
                dataChange[6] = true;
                isChanged = true;
            }
            for (String s : partialQueryList) {
                String suffix = "";
                if (c != (partialQueryList.size() - 1)) {
                    suffix = ",";
                }
                partialQuery += partialQueryList.get(c++) + suffix;
            }
            if (partialQuery.equals("")) return false;
            String query = "UPDATE " + StockModel.TABLE + " SET" + partialQuery + "WHERE id = ?";

            PreparedStatement psmt = this.db.prepareStatement(query);
            int j = 1;
            for (int i = 0; i < n; i++) {
                if (dataChange[i]) {
                    psmt.setString(j, dataOrigin[i]);
                    j++;
                }
            }
            psmt.setInt(j, xdata.getId());
            int result = psmt.executeUpdate();
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(StockModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteOne(Integer id) {
        try {
            String queryStr = "DELETE FROM " + StockModel.TABLE + " WHERE id = " + id;
            Statement smt = this.db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(StockModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteMany(ArrayList<Integer> IDs) {
        if (IDs.size() != 0) {
            try {
                String queryStr = "DELETE FROM " + StockModel.TABLE + " WHERE";
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
                Logger.getLogger(StockModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
