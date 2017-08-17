
package Model;

import DBContext.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcedureModel {
    protected Connection db = new DBConnection().on();
    public String getRecordsCount(String tableName) {
        try {
            CallableStatement stmt = db.prepareCall("{ call countRecords(?) }");
            stmt.setString(1, tableName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("result");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "0";
    }
}
