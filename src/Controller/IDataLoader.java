
package Controller;

import java.sql.ResultSet;

public interface IDataLoader {
    public void set_table();
    public void load_db_data(ResultSet data);
    public void load_ram_data();
    public void append(Object append_data);
    public void update();
    public void delete();
}
