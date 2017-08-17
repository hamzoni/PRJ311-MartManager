package Controller;

import Cache.cProduct;
import Cache.cStock;
import Data.Record;
import Entity.Product;
import Entity.Stock;
import Model.ProductModel;
import Model.StockModel;
import View.Create.CreateStock;
import View.Extra.StockExport;
import View.Extra.StockImport;
import View.Extra.StockWithdraw;
import View.General;
import View.Update.UpdateStock;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

public class StockController extends Controller implements IDataLoader {
    public UpdateStock updateFrame;
    public CreateStock createFrame;
    public StockImport importFrame;
    public StockExport exportFrame;
    public StockWithdraw withdrawFrame;
    public StockController(JTable viewTable, General mainFrame) {
        super(viewTable, mainFrame);
        updateFrame = new UpdateStock();
        createFrame = new CreateStock();
        importFrame = new StockImport();
        exportFrame = new StockExport();
        withdrawFrame = new StockWithdraw();
    }
    public void importStock() {
        importFrame.setFrameData(viewTable.getSelectedRow());
        importFrame.display(true);
    }
    public void exportStock() {
        exportFrame.setFrameData(viewTable.getSelectedRow());
        exportFrame.display(true);
    }
    public void withdrawStock() {
        withdrawFrame.setFrameData(viewTable.getSelectedRow());
        withdrawFrame.display(true);
    }
    @Override
    public void set_table() {
        dbModel = new StockModel();
        load_db_data(dbModel.select());
        set_table_event(this);
    }
    public void set_table_event(StockController ctrl) {
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
                Stock stock = new Stock();
                cStock xstock = new cStock();
                
                String id = data.getString("id");
                String name = data.getString("name");
                Float cost = data.getFloat("cost");
                String origin = data.getString("origin");
                String producer = data.getString("producer");
                Integer remaining = data.getInt("remaining");
                Integer stockOut = data.getInt("stock_out");
                String last_import_date = data.getString("last_import_date");
                String last_export_date = data.getString("last_export_date");
                
                xstock.setId(Integer.parseInt(id));
                xstock.setName(name);
                xstock.setCost(cost);
                xstock.setOrigin(origin);
                xstock.setProducer(producer);
                xstock.setRemaining(remaining);
                xstock.setStockOut(stockOut);
                xstock.setLastImportedDate((Date) dbModel.stringToUtilDate(last_import_date));
                xstock.setLastExportedDate((Date) dbModel.stringToUtilDate(last_export_date));
                
                last_import_date = dbModel.dateToHumanString(xstock.getLastImportedDate());
                last_export_date = dbModel.dateToHumanString(xstock.getLastExportedDate());
                
                loadedIDs.add(xstock.getId());
                
                StockModel ctg_mdl = new StockModel();
                // LOAD PRODUCTS IDS
                ResultSet prdIDs = ctg_mdl.getProductsIDs(xstock.getId());
                while (prdIDs.next()) {
                    try {
                        stock.productsIDs.add(prdIDs.getInt("id"));
                    } catch (Exception e) {}
                }
                
                tableModel.addRow(new Object[]{id, name, cost, origin, producer, remaining + " / " + stockOut, last_import_date, last_export_date});
                stock.setData(xstock);
                this.data.stocks.put(xstock.getId(), stock);
                this.data.lastStock = new Record(xstock.getId(), stock);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void load_ram_data() {
        tableModel.setRowCount(0);
        HashMap stocks = this.data.stocks;
        Iterator it = stocks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Stock crrStock = (Stock) pair.getValue();
            cStock xcrrStock = (cStock) crrStock.getData();
            int id = (int) pair.getKey();
            String name = xcrrStock.getName();
            Float cost = xcrrStock.getCost();
            String origin = xcrrStock.getOrigin();
            String producer = xcrrStock.getProducer();
            Integer remaining = xcrrStock.getRemaining();
            Integer stockOut = xcrrStock.getStockOut();
            Date lid = xcrrStock.getLastImportedDate();
            Date led = xcrrStock.getLastExportedDate();
            String last_import_date = dbModel.dateToHumanString(lid);
            String last_export_date = dbModel.dateToHumanString(led);
            tableModel.addRow(new Object[]{id, name, cost, origin, producer, remaining + " / " + stockOut, last_import_date, last_export_date});
        }
    }

    @Override
    public void append(Object append_data) {
        Stock newStock = new Stock();
        cStock xStock = (cStock) append_data;
        newStock.setData(xStock);
        this.data.stocks.put(xStock.getId(), newStock);
        this.data.lastStock = new Record(xStock.getId(), newStock);
        tableModel.addRow(new Object[]{
            xStock.getId(), xStock.getName(), xStock.getCost(),
            xStock.getOrigin(), xStock.getProducer(), xStock.getRemaining() + " / " + xStock.getStockOut(),
            xStock.getLastImportedDate().toString(), xStock.getLastExportedDate().toString()
        });
        loadedIDs.add(xStock.getId());
    }

    @Override
    public void update() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow < 0) return;
        int stockID = loadedIDs.get(selectedRow);
        Stock stock = (Stock) this.data.stocks.get(stockID);
        cStock xStock = (cStock) stock.getData();
        updateFrame.setFrameData(xStock);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
    }

    @Override
    public void delete() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow == -1) return;
        int stockID = loadedIDs.get(selectedRow);
        loadedIDs.remove(selectedRow);
        dbModel.deleteOne(stockID);
        
        Stock stock = (Stock) this.data.stocks.get(stockID);
        for (Integer prdID : stock.productsIDs) {
            Product newProduct = new Product();
            Product product = (Product) this.data.products.get(prdID);
            cProduct xProduct = (cProduct) product.getData();
            xProduct.setStock_id(-1);
            newProduct.setData(xProduct);
            this.data.products.replace(prdID, newProduct);
        }
        
        this.data.stocks.remove(stockID);
        
        if (viewTable.getRowCount() == 1) {
            try {
                dbModel.resetAutoIncrement(StockModel.TABLE);
                if (this.data.lastStock.id == stockID) {
                    this.data.lastStock = new Record(-1, null);
                }
            } catch (SQLException ex) {
                Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.load_ram_data();
        
        ProductController prd_ctrl = this.mainFrame.productController;
        ProductModel prd_mdl = new ProductModel();
        prd_mdl.updateStockNull(stockID);
        prd_ctrl.load_ram_data();
    }
}
