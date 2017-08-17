
package Cache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cClient {
    private int id;
    private String name;
    private String phone;
    private String address;
    private Date date_create;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateCreate() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return ft.format(this.date_create);
    }

    public void setDateCreate(String date) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.date_create = ft.parse(date);
    }
    public Date getDatePure() {
        return this.date_create;
    }
}
