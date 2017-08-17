
package Data;
import java.util.HashMap;
import javax.swing.RowFilter.Entry;

public class Databank {
    public HashMap categories = new HashMap();
    public HashMap clients = new HashMap();
    public HashMap invoices = new HashMap();
    public HashMap products = new HashMap();
    public HashMap stocks = new HashMap();
    public HashMap users = new HashMap();
    
    public Record lastCategory = new Record(-1, null);
    public Record lastClient = new Record(-1, null);
    public Record lastInvoice = new Record(-1, null);
    public Record lastProduct = new Record(-1, null);
    public Record lastStock = new Record(-1, null);
    public Record lastUser = new Record(-1, null);
}
