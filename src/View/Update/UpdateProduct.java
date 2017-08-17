
package View.Update;

import Cache.cCategory;
import Cache.cProduct;
import Cache.cStock;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class UpdateProduct extends Dialog implements UpdateFrame, VValidate, IBundle {
    private ProductModel prd_mdl;
    private cProduct xproduct;
    private ArrayList<Integer> categoryIDs;
    private ArrayList<Integer> stocksIDs;
    private cStock xstock;
    public UpdateProduct() {
        super("Update");
        prd_mdl = new ProductModel();
        categoryIDs = new ArrayList<Integer>();
        stocksIDs = new ArrayList<Integer>();
        initComponents();
        language_transform();
        ComponentListener listener = new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                setupCategoryList();
                setupStockList();
            }
        };
        this.addComponentListener(listener);
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
    public void setStockIOV() {
        if (stocksIDs.size() == 0) return;
        int stockID = stocksIDs.get(stockRef.getSelectedIndex());
        Stock stock = (Stock) this.mainFrame.data.stocks.get(stockID);
        xstock = (cStock) stock.getData();
        stockIn.setText(xstock.getRemaining() + "");
        stockOut.setText(xstock.getStockOut() + "");
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
        updateProduct = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        prdID = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        stockRef = new javax.swing.JComboBox<>();
        stockOut = new javax.swing.JTextField();
        stockIn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewUpdate"); // NOI18N
        jLabel1.setText(bundle.getString("UpdateProduct.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("UpdateProduct.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("UpdateProduct.jLabel3.text")); // NOI18N

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

        updateProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        updateProduct.setText(bundle.getString("UpdateProduct.updateProduct.text")); // NOI18N
        updateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProductActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("UpdateProduct.jLabel4.text")); // NOI18N

        prdID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prdID.setText(bundle.getString("UpdateProduct.prdID.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText(bundle.getString("UpdateProduct.jLabel6.text")); // NOI18N

        stockRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockRefActionPerformed(evt);
            }
        });

        stockOut.setEditable(false);
        stockOut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        stockIn.setEditable(false);
        stockIn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText(bundle.getString("UpdateProduct.jLabel7.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prdID)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(updateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 40, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(stockIn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(stockOut))
                            .addComponent(price, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(category, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(name, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(stockRef, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(prdID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(category, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateProduct)
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
    private void updateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProductActionPerformed
        if (!validating()) {
            getValidationMessage();
            return;
        }
        
        Product newcProduct = new Product();
        cProduct newProduct = new cProduct();
        newProduct.setId(this.xproduct.getId());
        newProduct.setName(name.getText());
        newProduct.setPrice(Float.parseFloat(price.getText()));
        
        newProduct.setCategoryName(category.getItemAt(category.getSelectedIndex()));
        int new_ctg_id = categoryIDs.get(category.getSelectedIndex());
        int new_stk_id = stocksIDs.get(stockRef.getSelectedIndex());
        
        if (new_ctg_id != this.xproduct.getCategory_id()) {
            int oldCtgID = this.xproduct.getCategory_id();
            Category lcategory = (Category) this.mainFrame.data.categories.get(oldCtgID);
            if (lcategory != null) {
                for (int i = 0; i < lcategory.productsIDs.size(); i++) {
                    if (lcategory.productsIDs.get(i) == this.xproduct.getId()) {
                        lcategory.productsIDs.remove(i);
                        break;
                    }
                }
                this.mainFrame.data.categories.replace(oldCtgID, lcategory);
            }
            Category dcategory = (Category) this.mainFrame.data.categories.get(new_ctg_id);
            dcategory.productsIDs.add(newProduct.getId());
            this.mainFrame.data.categories.replace(new_ctg_id, dcategory);
        }
        
        if (new_stk_id != this.xproduct.getStock_id()) {
            int oldStkId = this.xproduct.getStock_id();
            Stock lstock = (Stock) this.mainFrame.data.stocks.get(oldStkId);
            if (lstock != null) {
                for (int i = 0; i < lstock.productsIDs.size(); i++) {
                    if (lstock.productsIDs.get(i) == this.xproduct.getId()) {
                        lstock.productsIDs.remove(i);
                        break;
                    }
                }
                this.mainFrame.data.stocks.replace(oldStkId, lstock);
            }
            Stock dstock = (Stock) this.mainFrame.data.stocks.get(new_stk_id);
            dstock.productsIDs.add(newProduct.getId());
            this.mainFrame.data.stocks.replace(new_stk_id, dstock);
        }
        
        newProduct.setCategory_id(new_ctg_id);
        newProduct.setStock_id(new_stk_id);
        
        newcProduct.setData(newProduct);
        this.prd_mdl.setProduct(this.xproduct);
        this.prd_mdl.update(newProduct);
        
        this.mainFrame.data.products.replace(this.xproduct.getId(), newcProduct);
        this.mainFrame.productController.load_ram_data();
        this.mainFrame.categoryController.load_ram_data();
        this.setVisible(false);
    }//GEN-LAST:event_updateProductActionPerformed

    private void stockRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockRefActionPerformed
        int idx = stockRef.getSelectedIndex();
        if (stocksIDs.size() == 0 || idx == -1) return;
        int stockID = stocksIDs.get(idx);
        Stock stock = (Stock) this.mainFrame.data.stocks.get(stockID);
        xstock = (cStock) stock.getData();
        stockIn.setText(xstock.getRemaining() + "");
        stockOut.setText(xstock.getStockOut() + "");
    }//GEN-LAST:event_stockRefActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> category;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField name;
    private javax.swing.JLabel prdID;
    private javax.swing.JTextField price;
    private javax.swing.JTextField stockIn;
    private javax.swing.JTextField stockOut;
    private javax.swing.JComboBox<String> stockRef;
    private javax.swing.JButton updateProduct;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setFrameData(Object data) {
        cProduct xprd = (cProduct) data;
        this.xproduct = xprd;
        prdID.setText("#" + xprd.getId());
        name.setText(xprd.getName());
        price.setText(Float.toString(xprd.getPrice()));
        
        if (categoryIDs.size() == 0) {
            Iterator it = this.mainFrame.data.categories.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String value = ((cCategory)((Category)pair.getValue()).getData()).getName();
                category.addItem(value);
                categoryIDs.add((int)pair.getKey());
            }
            
        } 
        if (stocksIDs.size() == 0) {
            Iterator it2 = this.mainFrame.data.stocks.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry pair = (Map.Entry)it2.next();
                String value = ((cStock)((Stock)pair.getValue()).getData()).getName();
                stockRef.addItem(value);
                stocksIDs.add((int)pair.getKey());
            }
        }
        for (int i = 0; i < categoryIDs.size(); i++) {
            if (categoryIDs.get(i) == xprd.getCategory_id()) category.setSelectedIndex(i);
        }
        for (int i = 0; i < stocksIDs.size(); i++) {
            if (stocksIDs.get(i) == xprd.getStock_id()) stockRef.setSelectedIndex(i);
        }
        if (xprd.getStock_id() != 0) {
            Stock stock = (Stock) this.mainFrame.data.stocks.get(xprd.getStock_id());
            if (stock != null) {
                xstock = (cStock) stock.getData();
                stockIn.setText(xstock.getRemaining() + "");
                stockOut.setText(xstock.getStockOut() + "");
            }
        }
    }

    @Override
    public void language_transform() {
        jLabel4.setText(bundle.get("UpdateProduct.jLabel4.text"));
        updateProduct.setText(bundle.get("UpdateProduct.updateProduct.text"));
        jLabel7.setText(bundle.get("UpdateProduct.jLabel7.text"));
        jLabel3.setText(bundle.get("UpdateProduct.jLabel3.text"));
        jLabel2.setText(bundle.get("UpdateProduct.jLabel2.text"));
        jLabel1.setText(bundle.get("UpdateProduct.jLabel1.text"));
        jLabel6.setText(bundle.get("UpdateProduct.jLabel6.text"));
        prdID.setText(bundle.get("UpdateProduct.prdID.text"));
    }
}
