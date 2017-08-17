
package Model;

import Cache.cCategory;
import DBContext.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryModel extends Model {
    public static final String TABLE = "Category";
    private cCategory category;

    public cCategory getCategory() {
        return category;
    }

    public void setCategory(cCategory category) {
        this.category = category;
    }
    
    public ResultSet getProductsIDs(int category_id) {
        try {
            String queryStr = "SELECT id FROM " + ProductModel.TABLE + " WHERE category_id = " + category_id;
            Statement smt = db.createStatement();
            ResultSet prd_ids = smt.executeQuery(queryStr);
            return prd_ids;
        } catch (SQLException ex) {
            Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public ResultSet select() {
        try {
            String queryStr = "SELECT * FROM " + CategoryModel.TABLE;
            Statement smt = db.createStatement();
            ResultSet categories = smt.executeQuery(queryStr);
            return categories;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <Integer> ResultSet single(Integer v) {
        try {
            String queryStr = "SELECT * FROM " + CategoryModel.TABLE + " WHERE id = " + v;
            Statement smt = db.createStatement();
            ResultSet users = smt.executeQuery(queryStr);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <String> ResultSet search(String k) {
        return null;
    }

    @Override
    public boolean create(Object data) {
        cCategory xdata = (cCategory) data;
        try {
            String queryStr = "INSERT INTO " + CategoryModel.TABLE + "(name) VALUES ('" + xdata.getName() + "')";
            Statement smt = db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Object data) {
        try {
            cCategory xdata = (cCategory) data;
            String partialQuery = "";
            ArrayList<String> partialQueryList = new ArrayList<String>();
            boolean dataChange[] = {false};
            String dataOrigin[] = {xdata.getName()};
            int n = 1, c = 0;
            if (!this.category.getName().equals(xdata.getName())) {
                partialQueryList.add(" name = ? ");
                dataChange[0] = true;
            }
            for (String s : partialQueryList) {
                String suffix = "";
                if (c != (partialQueryList.size() - 1)) {
                    suffix = ",";
                }
                partialQuery += partialQueryList.get(c++) + suffix;
            }
            if (partialQuery.equals("")) return false;
            String query = "UPDATE " + CategoryModel.TABLE + " SET" + partialQuery + "WHERE id = ?";

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
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteOne(Integer id) {
        try {
            String queryStr = "DELETE FROM " + CategoryModel.TABLE + " WHERE id = " + id;
            Statement smt = this.db.createStatement();
            int result = smt.executeUpdate(queryStr);
            return result != 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public <Integer> boolean deleteMany(ArrayList<Integer> IDs) {
        if (IDs.size() != 0) {
            try {
                String queryStr = "DELETE FROM " + CategoryModel.TABLE + " WHERE";
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
                Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public <Integer> ResultSet searchID(Integer id) {
        try {
            String queryStr = "SELECT * FROM " + CategoryModel.TABLE + " WHERE id = " + id;
            Statement smt = db.createStatement();
            ResultSet clients = smt.executeQuery(queryStr);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
