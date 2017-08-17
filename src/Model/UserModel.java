
package Model;

import Cache.cUser;
import Middleware.Authentication;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserModel extends Model {
    public static final String TABLE = "Users";
    private cUser user; 

    public cUser getUser() {
        return user;
    }

    public void setUser(cUser user) {
        this.user = user;
    }
    
    @Override
    public ResultSet select() {
        try {
            String queryStr = "SELECT * FROM " + UserModel.TABLE;
            Statement smt = db.createStatement();
            ResultSet users = smt.executeQuery(queryStr);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <Integer> ResultSet single(Integer v) {
        try {
            String queryStr = "SELECT * FROM " + UserModel.TABLE + " WHERE id = " + v;
            Statement smt = db.createStatement();
            ResultSet users = smt.executeQuery(queryStr);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <String> ResultSet search(String k) {
        return null;
    }

    @Override
    public boolean create(Object data) {
        cUser xdata = (cUser) data;
        try {
            String queryStr = "INSERT INTO " + UserModel.TABLE + "(fullname, username, password, privilege, date_create) VALUES ('"
                    + xdata.getFullname()  + "', '" + xdata.getUsername()  + "', '" + xdata.getPassword()  + "', '" + xdata.getPrivilege() + "', '" + getTime() + "')";
            Statement smt = db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Object data) {
        try {
            cUser xdata = (cUser) data;
            String partialQuery = "";
            ArrayList<String> partialQueryList = new ArrayList<String>();
            boolean dataChange[] = {false, false, false, false};
            boolean isChange = false;
            String dataOrigin[] = {xdata.getFullname(), xdata.getUsername(), Authentication.MD5(xdata.getPassword()), "" + xdata.getPrivilege()};
            int n = 4, c = 0;
            if (!this.user.getFullname().equals(xdata.getFullname())) {
                partialQueryList.add(" fullname = ? ");
                dataChange[0] = true;
                isChange = true;
            }
            if (!this.user.getUsername().equals(xdata.getUsername())) {
                partialQueryList.add(" username = ? ");
                dataChange[1] = true;
                isChange = true;
            }
            if (!this.user.getPassword().equals(Authentication.MD5(xdata.getPassword()))) {
                if (!xdata.getPassword().equals("")) {
                    partialQueryList.add(" password = ? ");
                    dataChange[2] = true;
                    isChange = true;
                }
            }
            if (this.user.getPrivilege() != xdata.getPrivilege()) {
                partialQueryList.add(" privilege = ? ");
                dataChange[3] = true;
                isChange = true;
            }
            for (String s : partialQueryList) {
                String suffix = "";
                if (c != (partialQueryList.size() - 1)) suffix = ",";
                partialQuery += partialQueryList.get(c++) + suffix;
            }
            String query = "UPDATE " + UserModel.TABLE + " SET" + partialQuery + "WHERE id = ?";
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
            if (isChange) result = psmt.executeUpdate();
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteOne(Integer id) {
        try {
            String queryStr = "DELETE FROM " + UserModel.TABLE + " WHERE id = " + id;
            Statement smt = this.db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteMany(ArrayList<Integer> IDs) {
        if (IDs.size() != 0) {
            try {
                String queryStr = "DELETE FROM " + UserModel.TABLE + " WHERE";
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
                Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public ResultSet searchID(int id) {
        try {
            String queryStr = "SELECT * FROM " + UserModel.TABLE + " WHERE id = " + id;
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ResultSet searchAccount(String username, String passcode) {
        try {
            String queryStr = "SELECT * FROM " + UserModel.TABLE + " WHERE username = '" + username + "' AND password = '" + passcode + "'";
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
