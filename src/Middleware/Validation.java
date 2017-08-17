
package Middleware;

import java.util.HashMap;
import javax.swing.JTextField;

public class Validation {
    public static HashMap errData = new HashMap();
    public static int v_string = 0;
    public static int v_email = 0;
    public static int v_int = 0;
    public static int v_float = 0;
    public static int v_length = 0;
    
    public static final void v_setErrString(String err) {
        if (errData.containsKey("v_string")) errData.remove("v_string");
        errData.put("v_string", err);
    }
    public static final void v_setErrEmail(String err) {
        if (errData.containsKey("v_email")) errData.remove("v_email");
        errData.put("v_email", err);
    }
    public static final void v_setErrInt(String err) {
        if (errData.containsKey("v_int")) errData.remove("v_int");
        errData.put("v_int", err);
    }
    public static final void v_setErrFloat(String err) {
        if (errData.containsKey("v_float")) errData.remove("v_float");
        errData.put("v_float", err);
    }
    public static final void v_setErrLength(String err) {
        if (errData.containsKey("v_length")) errData.remove("v_length");
        errData.put("v_length", err);
    }
    public static final void v_setErrEmpty(String err) {
        if (errData.containsKey("v_require")) errData.remove("v_require");
        errData.put("v_require", err);
    }
    
    public static final boolean v_string(String v) {
        if (errData.get("v_string") == null) errData.put("v_string", "Invalid text input");
        return !v.matches("^[\\p{L}\\s'.-]+$");
    }
    public static final boolean  v_email(String v) {
        if (errData.get("v_email") == null) errData.put("v_email", "Invalid email");
        return !v.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }
    public static final boolean  v_int(String v) {
        if (errData.get("v_int") == null) errData.put("v_int", "Invalid integer number");
        return v.matches("^-?\\d+$");
    }
    public static final boolean  v_float(String v) {
        if (errData.get("v_float") == null) errData.put("v_float", "Invalid float number");
        try {
            float x = Float.parseFloat(v);
            return true;
        } catch (Exception e) {}
        return false;
    }
    public static final boolean v_length(String v, int min, int max) {
        if (errData.get("v_length") == null) errData.put("v_length", "Over limited length");
        return v.length() >= min && v.length() <= max;
    }
    public static final boolean v_require(String v) {
        if (errData.get("v_require") == null) errData.put("v_require", "The input could not be empty");
        return v.length() != 0;
    }
}
