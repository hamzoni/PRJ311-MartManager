
package Middleware;

import Cache.cUser;
import Model.UserModel;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
    private static int ID;
    private static int PRIVILEGE;
    private static String FULLNAME;
    private static String USERNAME;
    private static String PASSWORD;
    public static boolean STATUS = false;

    public static void UPDATE(cUser newUser) {
        Authentication.FULLNAME = newUser.getFullname();
        Authentication.USERNAME = newUser.getUsername();
        Authentication.PASSWORD = newUser.getPassword();
        Authentication.PRIVILEGE = newUser.getPrivilege();
    }
    
    public Authentication(String username, String passcode) throws SQLException {
        UserModel user_mdl = new UserModel();
        ResultSet accounts = user_mdl.searchAccount(username, passcode);
        if (!accounts.wasNull()) {
            while (accounts.next()) {
                this.STATUS = true;
                this.ID = accounts.getInt("id");
                this.PRIVILEGE = accounts.getInt("privilege");
                this.FULLNAME = accounts.getString("fullname");
                this.USERNAME = accounts.getString("username");
                this.PASSWORD = accounts.getString("password");
            }
        }
    }
    public static void REFRESH() {
        Authentication.ID = -1;
        Authentication.PRIVILEGE = -1;
        Authentication.FULLNAME = null;
        Authentication.USERNAME = null;
        Authentication.PASSWORD = null;
        Authentication.STATUS = false;
    }
    public static String MD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) hashtext = "0" + hashtext;
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int getID() {
        return ID;
    }

    public static int getPRIVILEGE() {
        return PRIVILEGE;
    }

    public static String getFULLNAME() {
        return FULLNAME;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }
    
    public static String getPASSWORD() {
        return PASSWORD;
    }
}
