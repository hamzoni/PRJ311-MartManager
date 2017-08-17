
package Controller;

import Cache.cProduct;
import Cache.cStock;
import Data.Record;
import Entity.Category;
import Entity.Product;
import Entity.Stock;
import Model.ProductModel;
import View.Create.CreateProduct;
import View.General;
import View.Update.UpdateProduct;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductController extends Controller implements IDataLoader {
    public UpdateProduct updateFrame;
    public CreateProduct createFrame;
    public ProductController(JTable viewTable, General mainFrame) {
        super(viewTable, mainFrame);
        updateFrame = new UpdateProduct();
        try {
            createFrame = new CreateProduct();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void set_table() {
        dbModel = new ProductModel();
        load_db_data(dbModel.select());
        set_table_event(this);
    }
    public void set_table_event(ProductController ctrl) {
        viewTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) ctrl.update();
            }
        });
    }
    @Override
    public void load_db_data(ResultSet data) {
        tableModel.setRowCount(0);
        try {
            while (data.next()) {
                Product product = new Product();
                cProduct xProduct = new cProduct();
                cStock xStock = null;
                
                int id = Integer.parseInt(data.getString("id"));
                String name = data.getString("name");
                float price = data.getFloat("price");
                String category_name = data.getString("category_name");
                String ctg_id = data.getString("category_id");
                
                int category_id = -1;
                if (ctg_id != null) category_id = Integer.parseInt(ctg_id);

                String stk_id = data.getString("stock_id");
                int stock_id = -1;
                if (stk_id != null) stock_id = Integer.parseInt(stk_id);
                String stock_id_str = "";
                String stockIO = "";
                
                if (stock_id != 0) {
                    Stock stock = (Stock) this.data.stocks.get(stock_id);
                    if (stock != null) {
                        xStock = (cStock) stock.getData();
                        product.setStock(stock);
                        stockIO = xStock.getRemaining() + " / " + xStock.getStockOut();
                        stock_id_str += stock_id;
                    }
                }
                xProduct.setId(id);
                xProduct.setName(name);
                xProduct.setPrice(price);
                xProduct.setCategory_id(category_id);
                xProduct.setCategoryName(category_name);
                xProduct.setStock_id(stock_id);
                loadedIDs.add(xProduct.getId());
                
                tableModel.addRow(new Object[]{id, name, price, category_name, stock_id_str, stockIO});
                product.setData(xProduct);
                this.data.products.put(xProduct.getId(), product);
                this.data.lastProduct = new Record(xProduct.getId(), product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void load_ram_data() {
        tableModel.setRowCount(0);
        HashMap products = this.data.products;
        Iterator it = products.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Product crrcProduct = (Product) pair.getValue();
            cProduct xCrrProduct = (cProduct) crrcProduct.getData();
            int id = (int) pair.getKey();
            String name = xCrrProduct.getName();
            float price = xCrrProduct.getPrice();
            
            int ctg_id = xCrrProduct.getCategory_id();
            String category_name = xCrrProduct.getCategoryName();
            if (ctg_id == -1) category_name = "";
            
            
            cStock xStock = null;
            String stockIDV = "";
            int stock_id = xCrrProduct.getStock_id();
            stockIDV = stock_id + "";
            String stockIO = "";
            
            if (stock_id != -1) {
                Stock stock = (Stock) this.data.stocks.get(stock_id);
                if (stock != null) {
                    xStock = (cStock) stock.getData();
                    stockIO = xStock.getRemaining() + " / " + xStock.getStockOut();
                } else {
                    stockIDV = "";
                }
            } else {
                stockIDV = "";
            }
            tableModel.addRow(new Object[]{id, name, price, category_name, stockIDV, stockIO});
        }
    }

    @Override
    public void append(Object append_data) {
        Product newProduct = new Product();
        cProduct xProduct = (cProduct) append_data;
        cStock xStock = null;
        
        int stock_id = xProduct.getStock_id();
        String stockIO = "0 / 0";
        if (stock_id != 0) {
            Stock stock = (Stock) this.data.stocks.get(stock_id);
            xStock = (cStock) stock.getData();
            newProduct.setStock(stock);
            stockIO = xStock.getRemaining() + " / " + xStock.getStockOut();
        }
                
        newProduct.setData(xProduct);
        this.data.products.put(xProduct.getId(), newProduct);
        this.data.lastProduct = new Record(xProduct.getId(), newProduct);
        
        tableModel.addRow(new Object[]{xProduct.getId(), xProduct.getName(), xProduct.getPrice(), xProduct.getCategoryName(), stock_id, stockIO});
        loadedIDs.add(xProduct.getId());
        this.mainFrame.categoryController.load_ram_data();
    }

    @Override
    public void update() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow < 0) return;
        int productID = loadedIDs.get(selectedRow);
        
        Product prd = (Product) this.data.products.get(productID);
        cProduct xprd = (cProduct) prd.getData();
        updateFrame.setFrameData(xprd);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
    }

    @Override
    public void delete() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow == -1) return;
        int productID = loadedIDs.get(selectedRow);
        loadedIDs.remove(selectedRow);
        // DELETE PRODUCT IN STOCK
        Product product = (Product) this.data.products.get(productID);
        cProduct xProduct = (cProduct) product.getData();
        Stock pstock = product.getStock();
        if (pstock != null) {
            cStock xStock = (cStock) pstock.getData();
            Stock stock = (Stock) this.data.stocks.get(xStock.getId());
            for (Integer productsID :  stock.productsIDs) {
                if (productsID == productID)  {
                    stock.productsIDs.remove(productsID);
                    break;
                }
            }
            this.data.stocks.replace(xStock.getId(), stock);
        }
        // DELETE PRODUCT IN CATEGORY
        Category category = (Category) this.data.categories.get(xProduct.getCategory_id());
        if (category != null) {
            for (Integer productsID :  category.productsIDs) {
                if (productsID == productID)  {
                    category.productsIDs.remove(productsID);
                    break;
                }
            }
        }
        this.data.products.remove(productID);
        if (viewTable.getRowCount() == 1) {
            try {
                dbModel.resetAutoIncrement(ProductModel.TABLE);
                if (this.data.lastProduct.id == productID) {
                    this.data.lastProduct = new Record(-1, null);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.mainFrame.categoryController.load_ram_data();
        this.load_ram_data();
        dbModel.deleteOne(productID);
    }
}
