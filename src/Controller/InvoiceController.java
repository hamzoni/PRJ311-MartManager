
package Controller;

import Cache.cCart;
import Cache.cClient;
import Cache.cInvoice;
import Cache.cProduct;
import Cache.cUser;
import Data.Record;
import Entity.Cart;
import Entity.Client;
import Entity.Invoice;
import Entity.Product;
import Entity.User;
import Model.CartModel;
import Model.InvoiceModel;
import View.Create.CreateInvoice;
import View.General;
import View.Update.UpdateInvoice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InvoiceController extends Controller implements IDataLoader {
    private JTable cartTable;
    private DefaultTableModel modelCart;
    private CartModel crt_mdl;
    
    public UpdateInvoice updateFrame;
    public CreateInvoice createFrame;
    
    public InvoiceController(JTable viewTable, JTable cartTable, General mainFrame) {
        super(viewTable, mainFrame);
        this.cartTable = cartTable;
        crt_mdl = new CartModel();
        modelCart = (DefaultTableModel) cartTable.getModel();
        
        updateFrame = new UpdateInvoice();
        createFrame = new CreateInvoice();
    }

    @Override
    public void set_table() {
        dbModel = new InvoiceModel();
        load_db_data(dbModel.select());
        set_table_event(this);
    }
    public void set_table_event(InvoiceController ctrl) {
        viewTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                ctrl.updateCartInvoice();
                if (me.getClickCount() == 2) ctrl.update();
            }
        });
    }
    @Override
    public void load_db_data(ResultSet data) {
        modelCart.setRowCount(0);
        tableModel.setRowCount(0);
        try {
            while (data.next()) {
                Invoice invoice = new Invoice();
                cInvoice xInvoice = new cInvoice();
                
                String id = data.getString("id");
                String payment_method = data.getString("payment_method");
                String date_create = data.getString("date_create");
                String userID = data.getString("user_id");
                int user_id = -1;
                if (userID != null) user_id = Integer.parseInt(userID);
                
                String clientID = data.getString("client_id");
                int client_id = -1;
                if (clientID != null) client_id =  Integer.parseInt(clientID);
                
                xInvoice.setId(Integer.parseInt(id));
                xInvoice.setPayment_method(payment_method);
                xInvoice.setDate_create(date_create);
                xInvoice.setUser_id(user_id);
                xInvoice.setClient_id(client_id);
                loadedIDs.add(xInvoice.getId());
                
                String clientName = getClientName(client_id);
                String invoicerName = getUserName(user_id);
                
                tableModel.addRow(new Object[]{id, clientName, invoicerName, payment_method, date_create});
                invoice.setData(xInvoice);
                invoice.carts = getCart(xInvoice.getId());
                this.data.invoices.put(xInvoice.getId(), invoice);
                this.data.lastInvoice = new Record(xInvoice.getId(), invoice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int lastID = this.data.lastInvoice.id;
        if (lastID == -1) try {
            dbModel.resetAutoIncrement(InvoiceModel.TABLE);
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Cart> getCart(int invoiceID) throws SQLException {
        ArrayList<Cart> iCart = new ArrayList<Cart>();
        CartModel cart_mdl = new CartModel();
        ResultSet carts = cart_mdl.selectCol("invoice_id", invoiceID + "");
        while (carts.next()) {
            Cart cart = new Cart();
            cCart xCart = new cCart();

            int product_id = carts.getInt("product_id");
            int invoice_id = carts.getInt("invoice_id");
            int quantity = carts.getInt("quantity");
            float unit_price = carts.getFloat("unit_price");

            xCart.setProduct_id(product_id);
            xCart.setInvoice_id(invoice_id);
            xCart.setQuantity(quantity);
            xCart.setUnit_price(unit_price);

            cart.setData(xCart);

            iCart.add(cart);
        }
        return iCart;
    }
    @Override
    public void load_ram_data() {
        tableModel.setRowCount(0);
        modelCart.setRowCount(0);
        HashMap invoices = this.data.invoices;
        Iterator it = invoices.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Invoice crrInvoice = (Invoice) pair.getValue();
            cInvoice xCrrInvoice = (cInvoice) crrInvoice.getData();
            int id = (int) pair.getKey();
            String clientName = getClientName(xCrrInvoice.getClient_id());
            String invoicerName = getUserName(xCrrInvoice.getUser_id());
            String payment_method = xCrrInvoice.getPayment_method();
            String date_create = xCrrInvoice.getDate_create();
            tableModel.addRow(new Object[]{id, clientName, invoicerName, payment_method, date_create});
        }
    }
    public String getUserName(int user_id) {
        User user = (User) this.data.users.get(user_id);
        if (user != null) {
            cUser xuser = (cUser) user.getData();
            return xuser.getFullname();
        }
        return "";
    }
    public String getClientName(int client_id) {
        Client client = (Client) this.data.clients.get(client_id);
        if (client != null) {
            cClient xclient = (cClient) client.getData();
            return xclient.getName();
        }
        return "";
    }
    @Override
    public void append(Object append_data) {
        Invoice invoice = (Invoice) append_data;
        cInvoice xInvoice =  (cInvoice) invoice.getData();
        this.data.invoices.put(xInvoice.getId(), invoice);
        this.data.lastInvoice = new Record(xInvoice.getId(), invoice);
        
        tableModel.addRow(new Object[]{xInvoice.getId(), getClientName(xInvoice.getClient_id()), getUserName(xInvoice.getUser_id()), xInvoice.getPayment_method(), xInvoice.getDate_create()});
        loadedIDs.add(xInvoice.getId());
    }

    @Override
    public void update() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow < 0) return;
        int invoiceID = loadedIDs.get(selectedRow);
        
        Invoice invoice = (Invoice) this.data.invoices.get(invoiceID);
        
        updateFrame.setFrameData(invoice);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
    }

    @Override
    public void delete() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow == -1) return;
        int invoiceID = loadedIDs.get(selectedRow);
        loadedIDs.remove(selectedRow);
        dbModel.deleteOne(invoiceID);
        try {
            crt_mdl.deleteByInvoice(invoiceID);
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.data.invoices.remove(invoiceID);
        
        if (viewTable.getRowCount() == 1) {
            try {
                dbModel.resetAutoIncrement(InvoiceModel.TABLE);
                if (this.data.lastInvoice.id == invoiceID) {
                    this.data.lastInvoice = new Record(-1, null);
                }
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.load_ram_data();
    }
    
    public void updateCartInvoice() {
        int columns = tableModel.getColumnCount();
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow == -1) return;
        int invoiceID = loadedIDs.get(selectedRow);
        Invoice invoice = (Invoice) this.data.invoices.get(invoiceID);
        ArrayList<Cart> invoiceCarts = invoice.carts;
        try {
            load_cartTable(invoiceCarts);
        } catch (SQLException ex) {
            Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void load_cartTable(ArrayList<Cart> carts) throws SQLException, ParseException {
        DefaultTableModel cartModel = (DefaultTableModel) cartTable.getModel();
        cartModel.setRowCount(0);
        float sumValue = 0;
        if (carts.size() == 0) return;
        for (Cart cart : carts) {
            cCart xCart = (cCart) cart.getData();
            
            int invoiceID = xCart.getInvoice_id();
            int productID  = xCart.getProduct_id();
            
            float totalPrice = (xCart.getQuantity() * xCart.getUnit_price());
            sumValue += totalPrice;
            cartModel.addRow(new Object[]{getProductNameSM(xCart.getProduct_id()), xCart.getQuantity(), xCart.getUnit_price(), totalPrice});
        }
        mainFrame.getTotalInvoice().setText(sumValue + "");
    }
    public String getProductNameSM(int productID) {
        Product prd = (Product) this.data.products.get(productID);
        if (prd != null) {
            cProduct xPrd = (cProduct) prd.getData();
            return xPrd.getName();
        }
        return "";
    }
}
