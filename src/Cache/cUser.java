
package Cache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cUser {
    private int id;
    private String fullname;
    private String username;
    private String password;
    private int privilege;
    private Date date;

    public int getId() {
        return id;
    }

    public String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return ft.format(this.date);
    }
    public Date getDatePure() {
        return this.date;
    }
    public void setDate(String date) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.date = ft.parse(date);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String name) {
        this.fullname = name;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
   
}
