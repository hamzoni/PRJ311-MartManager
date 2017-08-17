
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface IModel {
    public ResultSet select();
    public <T> ResultSet single(T v);
    public <T> ResultSet search(T k);
//    public <T> ResultSet searchID(T id);
    public boolean create(Object data);
    public boolean update(Object data);
    public <T> boolean deleteOne(T id);
    public <T> boolean deleteMany(ArrayList<T> IDs);
}
