
package Controller;

import Cache.cCategory;
import Cache.cProduct;
import Data.Record;
import Entity.Category;
import Entity.Product;
import Model.CategoryModel;
import Model.ProductModel;
import View.Create.CreateCategory;
import View.General;
import View.Update.UpdateCategory;
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

public class CategoryController extends Controller implements IDataLoader {
    public UpdateCategory updateFrame;
    public CreateCategory createFrame;
    public CategoryController(JTable viewTable, General mainFrame) {
        super(viewTable, mainFrame);
        updateFrame = new UpdateCategory();
        createFrame = new CreateCategory();
    }

    @Override
    public void set_table() {
        dbModel = new CategoryModel();
        load_db_data(dbModel.select());
        set_table_event(this);
    }
    public void set_table_event(CategoryController ctrl) {
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
                Category category = new Category();
                cCategory xCategory = new cCategory();
                
                String id = data.getString("id");
                String name = data.getString("name");
                int noProducts = 0;
                        
                xCategory.setId(Integer.parseInt(id));
                xCategory.setName(name);
                loadedIDs.add(xCategory.getId());
                
                CategoryModel ctg_mdl = new CategoryModel();
                // LOAD PRODUCTS IDS
                ResultSet prdIDs = ctg_mdl.getProductsIDs(xCategory.getId());
                while (prdIDs.next()) {
                    try {
                        category.productsIDs.add(prdIDs.getInt("id"));
                    } catch (Exception e) {}
                }
                noProducts = category.productsIDs.size();
                
                tableModel.addRow(new Object[]{id, name, noProducts});
                category.setData(xCategory);
                this.data.categories.put(xCategory.getId(), category);
                this.data.lastCategory = new Record(xCategory.getId(), category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void load_ram_data() {
        tableModel.setRowCount(0);
        HashMap categories = this.data.categories;
        Iterator it = categories.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Category crrCategory = (Category) pair.getValue();
            cCategory xCrrCategory = (cCategory) crrCategory.getData();
            int noProducts = crrCategory.productsIDs.size();
            int id = (int) pair.getKey();
            String name = xCrrCategory.getName();
            tableModel.addRow(new Object[]{id, name, noProducts});
        }
    }

    @Override
    public void append(Object append_data) {
        Category newCategory = new Category();
        cCategory xCategory = (cCategory) append_data;
        newCategory.setData(xCategory);
        this.data.categories.put(xCategory.getId(), newCategory);
        this.data.lastCategory = new Record(xCategory.getId(), newCategory);
        tableModel.addRow(new Object[]{xCategory.getId(), xCategory.getName()});
        loadedIDs.add(xCategory.getId());
    }

    @Override
    public void update() {
        cCategory xctg = new cCategory();
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow < 0) return;
        int categoryID = loadedIDs.get(selectedRow);
        xctg.setId(categoryID);
        xctg.setName((String) tableModel.getValueAt(selectedRow, 1));
        updateFrame.setFrameData(xctg);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
    }

    @Override
    public void delete() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow == -1) return;
        int categoryID = loadedIDs.get(selectedRow);
        loadedIDs.remove(selectedRow);
        dbModel.deleteOne(categoryID);
        
        Category category = (Category) this.data.categories.get(categoryID);
        for (Integer prdID : category.productsIDs) {
            Product newProduct = new Product();
            Product product = (Product) this.data.products.get(prdID);
            cProduct xProduct = (cProduct) product.getData();
            xProduct.setCategory_id(-1);
            xProduct.setCategoryName("");
            newProduct.setData(xProduct);
            this.data.products.replace(prdID, newProduct);
        }
        
        this.data.categories.remove(categoryID);
        ProductModel prd_mdl = new ProductModel();
        prd_mdl.updateCategoryNull(categoryID);
        if (viewTable.getRowCount() == 1) {
            try {
                dbModel.resetAutoIncrement(CategoryModel.TABLE);
                if (this.data.lastCategory.id == categoryID) {
                    this.data.lastCategory = new Record(-1, null);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.load_ram_data();
        this.mainFrame.productController.load_ram_data();
    }
}
