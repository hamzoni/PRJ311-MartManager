
package View.Create;

import Cache.cCategory;
import Cache.cProduct;
import Cache.cStock;
import Data.Databank;
import Entity.Category;
import Entity.Product;
import Entity.Stock;
import Middleware.Validation;
import Model.ProductModel;
import View.Dialog;
import View.Interface.IBundle;
import View.VValidate;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JDialog;

public class CreateProduct extends Dialog implements VValidate, IBundle {
    private ProductModel prd_mdl;
    private HashMap categoryNames;
    private ArrayList<Integer> categoryIDs =  new ArrayList<Integer>();
    private ArrayList<Integer> stocksIDs =  new ArrayList<Integer>();
    private cStock xstock;
    public CreateProduct() throws SQLException {
        super("Create");
        prd_mdl = new ProductModel();
        initComponents();
        language_transform();
        
        ComponentListener listener = new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                setupCategoryList();
                setupStockList();
            }
        };
        this.addComponentListener(listener);
        setSubEvent(this);
    }
    private void setSubEvent(Dialog dlg) {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dlg.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                reset();
            }
        });
    }

    private void setupCategoryList() {
        category.removeAllItems();
        categoryIDs = new ArrayList<Integer>();
        if (categoryIDs.size() != 0) return;
        Iterator it = this.mainFrame.data.categories.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String value = ((cCategory)((Category)pair.getValue()).getData()).getName();
            category.addItem(value);
            categoryIDs.add((int)pair.getKey());
        }
    }
    public void setupStockList() {
        stockRef.removeAllItems();
        stocksIDs = new ArrayList<Integer>();
        if (stocksIDs.size() != 0) return;
        Iterator it = this.mainFrame.data.stocks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String value = ((cStock)((Stock)pair.getValue()).getData()).getName();
            stockRef.addItem(value);
            stocksIDs.add((int)pair.getKey());
        }
        setStockIOV();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        category = new javax.swing.JComboBox<>();
        createProduct = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        stockRef = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        stockIn = new javax.swing.JTextField();
        stockOut = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewCreate"); // NOI18N
        jLabel1.setText(bundle.getString("CreateProduct.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("CreateProduct.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("CreateProduct.jLabel3.text")); // NOI18N

        name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        category.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryActionPerformed(evt);
            }
        });

        createProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createProduct.setText(bundle.getString("CreateProduct.createProduct.text")); // NOI18N
        createProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createProductActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("CreateProduct.jLabel4.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText(bundle.getString("CreateProduct.jLabel5.text")); // NOI18N

        stockRef.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stockRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockRefActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText(bundle.getString("CreateProduct.jLabel7.text")); // NOI18N

        stockIn.setEditable(false);
        stockIn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stockIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockInActionPerformed(evt);
            }
        });

        stockOut.setEditable(false);
        stockOut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockRef, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(22, 22, 22)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(stockIn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(stockOut, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(createProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createProduct)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryActionPerformed
    @Override
    public boolean validating() {
        return (Validation.v_require(name.getText()) &&
            Validation.v_require(price.getText()) &&
            Validation.v_require(name.getText()) &&
            Validation.v_float(price.getText()) &&
            (category.getSelectedIndex() != -1) &&
            (stockRef.getSelectedIndex() != -1)
        );
    }
    private void createProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProductActionPerformed
        Databank data = this.mainFrame.data;
        Product product = new Product();
        cProduct xProduct = new cProduct();
        
        if (!validating()) {
            getValidationMessage();
            return;
        }
        
        int lastID = data.lastProduct.id;
        if (lastID == -1) lastID = 0;
        xProduct.setId(lastID + 1);
        xProduct.setName(name.getText());
        xProduct.setPrice(Float.parseFloat(price.getText()));
        
        int categoryID = categoryIDs.get(category.getSelectedIndex());
        Category dcategory = (Category) data.categories.get(categoryID);
        dcategory.productsIDs.add(xProduct.getId());
        data.categories.replace(categoryID, dcategory);
        
        int stockID = stocksIDs.get(stockRef.getSelectedIndex());
        Stock dstock = (Stock) data.stocks.get(stockID);
        dstock.productsIDs.add(xProduct.getId());
        data.stocks.replace(stockID, dstock);
        
        xProduct.setCategory_id(categoryID);
        xProduct.setCategoryName((String)category.getSelectedItem());
        xProduct.setStock_id(stockID);
        
        product.setData(xProduct);
        product.setStock(dstock);
        
        prd_mdl.create(xProduct);
        setVisible(false);
        reset();
        mainFrame.productController.append(xProduct);
    }//GEN-LAST:event_createProductActionPerformed

    private void stockRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockRefActionPerformed
        setStockIOV();
    }//GEN-LAST:event_stockRefActionPerformed
    public void setStockIOV() {
        int idx = stockRef.getSelectedIndex();
        if (stocksIDs.size() == 0 || idx == -1) return;
        int stockID = stocksIDs.get(idx);
        Stock stock = (Stock) this.mainFrame.data.stocks.get(stockID);
        xstock = (cStock) stock.getData();
        stockIn.setText(xstock.getRemaining() + "");
        stockOut.setText(xstock.getStockOut() + "");
    }
    private void stockInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockInActionPerformed

    public void reset() {
        name.setText("");
        price.setText("");
        stockIn.setText("");
        stockOut.setText("");
        if (category.getItemCount() > 0) category.setSelectedIndex(0);
        if (stockRef.getItemCount() > 0) stockRef.setSelectedIndex(0);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> category;
    private javax.swing.JButton createProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField name;
    private javax.swing.JTextField price;
    private javax.swing.JTextField stockIn;
    private javax.swing.JTextField stockOut;
    private javax.swing.JComboBox<String> stockRef;
    // End of variables declaration//GEN-END:variables

    @Override
    public void language_transform() {
        createProduct.setText(bundle.get("CreateProduct.createProduct.text"));
        jLabel3.setText(bundle.get("CreateProduct.jLabel3.text"));
        jLabel2.setText(bundle.get("CreateProduct.jLabel2.text"));
        jLabel1.setText(bundle.get("CreateProduct.jLabel1.text"));
        jLabel7.setText(bundle.get("CreateProduct.jLabel7.text"));
        jLabel5.setText(bundle.get("CreateProduct.jLabel5.text"));
        jLabel4.setText(bundle.get("CreateProduct.jLabel4.text"));
    }

}
