
package View.Update;

import Cache.cStock;
import Cache.cUser;
import Entity.Product;
import Entity.Stock;
import Entity.User;
import Middleware.Validation;
import Model.StockModel;
import Model.UserModel;
import View.Dialog;
import View.Interface.IBundle;
import View.VValidate;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateStock extends Dialog implements UpdateFrame, VValidate, IBundle {
    private StockModel stock_mdl;
    private cStock xStock;
    public UpdateStock() {
        super("Update");
        this.stock_mdl = new StockModel();
        initComponents();
        language_transform();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        stockName2 = new javax.swing.JTextField();
        stockCost = new javax.swing.JTextField();
        submitUpdateStock = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        stockOrigin = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        stockProducer = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        stockAmount = new javax.swing.JTextField();
        stockID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewUpdate"); // NOI18N
        jLabel1.setText(bundle.getString("UpdateStock.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("UpdateStock.jLabel2.text")); // NOI18N

        stockName2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stockName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockName2ActionPerformed(evt);
            }
        });

        stockCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        submitUpdateStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitUpdateStock.setText(bundle.getString("UpdateStock.submitUpdateStock.text")); // NOI18N
        submitUpdateStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitUpdateStockActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("UpdateStock.jLabel4.text")); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText(bundle.getString("UpdateStock.jLabel12.text")); // NOI18N

        stockOrigin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText(bundle.getString("UpdateStock.jLabel13.text")); // NOI18N

        stockProducer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText(bundle.getString("UpdateStock.jLabel14.text")); // NOI18N

        stockAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        stockID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        stockID.setText(bundle.getString("UpdateStock.stockID.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stockName2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(stockCost, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(stockAmount))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(33, 33, 33)
                        .addComponent(stockOrigin))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stockID)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(submitUpdateStock, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(stockID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(stockName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(stockCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(stockAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(stockOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(stockProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitUpdateStock)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void stockName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockName2ActionPerformed

    }//GEN-LAST:event_stockName2ActionPerformed

    private void submitUpdateStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitUpdateStockActionPerformed
        cStock newStock = new cStock();
        
        newStock.setId(this.xStock.getId());
        newStock.setName(stockName2.getText());
        newStock.setCost(Float.parseFloat(stockCost.getText()));
        newStock.setOrigin(stockOrigin.getText());
        newStock.setProducer(stockProducer.getText());
        newStock.setRemaining(Integer.parseInt(stockAmount.getText()));
        newStock.setStockOut(this.xStock.getStockOut());
        newStock.setLastImportedDate(this.xStock.getLastImportedDate());
        newStock.setLastExportedDate(this.xStock.getLastExportedDate());
        
        Stock oldcStock = (Stock) this.mainFrame.data.stocks.get(this.xStock.getId());
        oldcStock.setData(newStock);
        
        this.mainFrame.data.stocks.replace(this.xStock.getId(), oldcStock);
        // UPDATE STOCK FOR PRODUCTS
        HashMap products = this.mainFrame.data.products;
        Iterator it2 = products.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pair = (Map.Entry)it2.next();
            int prdID = (int) pair.getKey();
            Product prd = (Product) pair.getValue();
            Stock stock = prd.getStock();
            if (stock != null) {
                cStock xstock = (cStock) stock.getData();
                if (xstock.getId() == this.xStock.getId()) {
                    stock.setData(newStock);
                    prd.setStock(stock);
                    products.replace(prdID, prd);
                }
            }
        }
        this.mainFrame.productController.load_ram_data();
        
        this.stock_mdl.setXstock(this.xStock);
        this.stock_mdl.update(newStock);
        this.mainFrame.stockController.load_ram_data();
        this.setVisible(false);
    }//GEN-LAST:event_submitUpdateStockActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField stockAmount;
    private javax.swing.JTextField stockCost;
    private javax.swing.JLabel stockID;
    private javax.swing.JTextField stockName2;
    private javax.swing.JTextField stockOrigin;
    private javax.swing.JTextField stockProducer;
    private javax.swing.JButton submitUpdateStock;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setFrameData(Object data) {
        cStock xStock = (cStock) data;
        this.xStock = xStock;
        
        stockID.setText("#" + xStock.getId());
        stockName2.setText(xStock.getName());
        stockCost.setText(xStock.getCost() + "");
        stockOrigin.setText(xStock.getOrigin());
        stockProducer.setText(xStock.getProducer());
        stockAmount.setText(xStock.getRemaining() + "");
    }

    @Override
    public boolean validating() {
        return (Validation.v_require(stockAmount.getText()) &&
            Validation.v_require(stockCost.getText()) &&
            Validation.v_require(stockName2.getText()) &&
            Validation.v_require(stockOrigin.getText()) &&
            Validation.v_require(stockProducer.getText()) &&
            Validation.v_int(stockAmount.getText()) &&
            Validation.v_float(stockCost.getText())
        );
    }

    @Override
    public void language_transform() {
        stockID.setText(bundle.get("UpdateStock.stockID.text"));
        jLabel2.setText(bundle.get("UpdateStock.jLabel2.text"));
        jLabel1.setText(bundle.get("UpdateStock.jLabel1.text"));
        jLabel14.setText(bundle.get("UpdateStock.jLabel14.text"));
        jLabel13.setText(bundle.get("UpdateStock.jLabel13.text"));
        jLabel12.setText(bundle.get("UpdateStock.jLabel12.text"));
        jLabel4.setText(bundle.get("UpdateStock.jLabel4.text"));
        submitUpdateStock.setText(bundle.get("UpdateStock.submitUpdateStock.text"));
    }
}
