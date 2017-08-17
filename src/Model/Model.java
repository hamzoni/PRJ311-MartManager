
package Model;

import DBContext.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Model implements IModel {
    protected Connection db = new DBConnection().on();
    private static final String humanTimeFormat = "HH:mm:ss dd/MM/yyyy";
    private static final String sqlTimeFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    public String getTime() {
        return new SimpleDateFormat(humanTimeFormat).format(new Date());
    }
    public String dateToHumanString(Date date) {
        return new SimpleDateFormat(humanTimeFormat).format(date);
    }
    public String dateToSQLString(Date date) {
        return new SimpleDateFormat(sqlTimeFormat).format(date);
    }
    public Date stringToUtilDate(String date) throws ParseException {
        return new SimpleDateFormat(sqlTimeFormat).parse(date);
    }
    public static String getTimeGlobal() {
        return new SimpleDateFormat(sqlTimeFormat).format(new Date());
    }
    public static String getTimeGlobal2() {
        return new SimpleDateFormat(humanTimeFormat).format(new Date());
    }
    public boolean resetAutoIncrement(String tableName) throws SQLException {
        String queryStr = "DBCC CHECKIDENT ('" + tableName + "', RESEED, 0);";
        Statement smt = db.createStatement();
        int result = smt.executeUpdate(queryStr);
        return result != 0;
    }
}
