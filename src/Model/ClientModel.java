
package Model;

import Cache.cClient;
import DBContext.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientModel extends Model {
    public static final String TABLE = "Client";
    private cClient client; 

    public cClient getClient() {
        return client;
    }

    public void setClient(cClient client) {
        this.client = client;
    }
    
    @Override
    public ResultSet select() {
        try {
            String queryStr = "SELECT * FROM " + ClientModel.TABLE;
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <Integer> ResultSet single(Integer v) {
        try {
            String queryStr = "SELECT * FROM " + ClientModel.TABLE + " WHERE id = " + v;
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <String> ResultSet search(String k) {
        return null;
    }

    @Override
    public boolean create(Object data) {
        cClient xdata = (cClient) data;
        try {
            String queryStr = "INSERT INTO " + ClientModel.TABLE + "(name, phone, address, date_create) VALUES ('" 
                    + xdata.getName()  + "', '" 
                    + xdata.getPhone() + "', '" 
                    + xdata.getAddress() + "', '" 
                    + getTime() + "')";
            Statement smt = db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Object data) {
        try {
            cClient xdata = (cClient) data;
            String partialQuery = "";
            ArrayList<String> partialQueryList = new ArrayList<String>();
            boolean dataChange[] = {false, false, false};
            boolean hasChanged = false;
            String dataOrigin[] = {xdata.getName(), xdata.getPhone() + "", xdata.getAddress()};
            int n = 3, c = 0;
            if (!this.client.getName().equals(xdata.getName())) {
                partialQueryList.add(" name = ? ");
                dataChange[0] = true;
                hasChanged = true;
            }
            if (!this.client.getPhone().equals(xdata.getPhone())) {
                partialQueryList.add(" phone = ? ");
                dataChange[1] = true;
                hasChanged = true;
            }
            if (!this.client.getAddress().equals(xdata.getAddress())) {
                partialQueryList.add(" address = ? ");
                dataChange[2] = true;
                hasChanged = true;
            }
   
            for (String s : partialQueryList) {
                String suffix = "";
                if (c != (partialQueryList.size() - 1)) suffix = ",";
                partialQuery += partialQueryList.get(c++) + suffix;
            }
            String query = "UPDATE " + ClientModel.TABLE + " SET" + partialQuery + "WHERE id = ?";
            
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
            if (hasChanged) {
                result = psmt.executeUpdate();
            }
            
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteOne(Integer id) {
        try {
            String queryStr = "DELETE FROM " + ClientModel.TABLE + " WHERE id = " + id;
            Statement smt = this.db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteMany(ArrayList<Integer> IDs) {
        if (IDs.size() != 0) {
            try {
                String queryStr = "DELETE FROM " + ClientModel.TABLE + " WHERE";
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
                Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public ResultSet searchID(int id) {
        try {
            String queryStr = "SELECT * FROM " + ClientModel.TABLE + " WHERE id = " + id;
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
