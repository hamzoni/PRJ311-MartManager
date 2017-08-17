
package View.Extra;

import Cache.cStock;
import Entity.Product;
import Entity.Stock;
import Model.Model;
import View.Interface.IBundle;
import View.Update.UpdateFrame;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StockExport extends StockView implements UpdateFrame, IBundle {
    public StockExport() {
        initComponents();
        language_transform();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        submitExportStock = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        currentAmount = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        remainingAmount = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        stockCost = new javax.swing.JTextField();
        stocksList = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        totalValue = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        exportAmount = new javax.swing.JTextField();
        newStockAmount = new javax.swing.JTextField();
        stockOutAmount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewStock"); // NOI18N
        jLabel1.setText(bundle.getString("StockExport.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("StockExport.jLabel2.text")); // NOI18N

        submitExportStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitExportStock.setText(bundle.getString("StockExport.submitExportStock.text")); // NOI18N
        submitExportStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitExportStockActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("StockExport.jLabel4.text")); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText(bundle.getString("StockExport.jLabel12.text")); // NOI18N

        currentAmount.setEditable(false);
        currentAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText(bundle.getString("StockExport.jLabel13.text")); // NOI18N

        remainingAmount.setEditable(false);
        remainingAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText(bundle.getString("StockExport.jLabel14.text")); // NOI18N

        stockCost.setEditable(false);
        stockCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stockCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockCostActionPerformed(evt);
            }
        });

        stocksList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stocksListItemStateChanged(evt);
            }
        });
        stocksList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                stocksListPropertyChange(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText(bundle.getString("StockExport.jLabel15.text")); // NOI18N

        totalValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        totalValue.setText(bundle.getString("StockExport.totalValue.text")); // NOI18N

        exportAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        exportAmount.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                exportAmountInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        exportAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportAmountActionPerformed(evt);
            }
        });
        exportAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                exportAmountKeyReleased(evt);
            }
        });

        newStockAmount.setEditable(false);
        newStockAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        stockOutAmount.setEditable(false);
        stockOutAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitExportStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalValue))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(stocksList, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(exportAmount, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currentAmount, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(remainingAmount))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stockCost, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                            .addComponent(newStockAmount)
                            .addComponent(stockOutAmount))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stocksList)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stockOutAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel14)
                    .addComponent(stockCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(remainingAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newStockAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(totalValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitExportStock)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitExportStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitExportStockActionPerformed
        int stockID = this.xStock.getId();
        int newRemaining = Integer.parseInt(remainingAmount.getText());
        int newStockout = Integer.parseInt(newStockAmount.getText());
        if (this.xStock.getRemaining() != newRemaining) {
            stock_mdl.exportStock(stockID, newRemaining, newStockout, Model.getTimeGlobal());
        }
        this.xStock.setRemaining(newRemaining);
        this.xStock.setStockOut(newStockout);
        this.xStock.setLastExportedDate(new Date());
        this.mainFrame.stockController.load_ram_data();
        
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
                if (xstock.getId() == stockID) {
                    xstock = this.xStock;
                    stock.setData(this.xStock);
                    prd.setStock(stock);
                    products.replace(prdID, prd);
                }
            }
        }
        this.mainFrame.productController.load_ram_data();
        this.setVisible(false);
    }//GEN-LAST:event_submitExportStockActionPerformed

    private void stocksListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stocksListItemStateChanged
        if (stocksIDs.size() > 0)
        putFrameData(stocksIDs.get(stocksList.getSelectedIndex()));
    }//GEN-LAST:event_stocksListItemStateChanged

    private void stocksListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_stocksListPropertyChange

    }//GEN-LAST:event_stocksListPropertyChange

    private void exportAmountInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_exportAmountInputMethodTextChanged
    }//GEN-LAST:event_exportAmountInputMethodTextChanged

    private void exportAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_exportAmountKeyReleased
        int expAmount = Integer.parseInt(exportAmount.getText());
        int crrRemaining = Integer.parseInt(currentAmount.getText());
        float costUnit = Float.parseFloat(stockCost.getText());

        int remainingOffset = crrRemaining - expAmount;
        if (expAmount > crrRemaining) expAmount = crrRemaining;
        if (remainingOffset < 0) exportAmount.setText(currentAmount.getText());
        remainingOffset = remainingOffset < 0 ? 0 : remainingOffset;

        float importValue = expAmount * costUnit;
        float maxImportValue = crrRemaining * costUnit;
        importValue = importValue > maxImportValue ? maxImportValue : importValue;

        remainingAmount.setText(remainingOffset + "");
        totalValue.setText(importValue + "");
        
        int currentSOA = Integer.parseInt(stockOutAmount.getText());
        int newSOA = currentSOA + expAmount;
        newStockAmount.setText(newSOA + "");
    }//GEN-LAST:event_exportAmountKeyReleased

    private void stockCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockCostActionPerformed

    private void exportAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportAmountActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField currentAmount;
    private javax.swing.JTextField exportAmount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField newStockAmount;
    private javax.swing.JTextField remainingAmount;
    private javax.swing.JTextField stockCost;
    private javax.swing.JTextField stockOutAmount;
    private javax.swing.JComboBox<String> stocksList;
    private javax.swing.JButton submitExportStock;
    private javax.swing.JLabel totalValue;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setFrameData(Object data) {
        putFrameData(super.frameSetting(data, stocksList));
    }
    public void putFrameData(int StockID) {
        if (stocksIDs.size() > 0) {
            StockID = StockID == -1 ? stocksIDs.get(0) : StockID;
            this.xStock = (cStock) ((Stock) this.mainFrame.data.stocks.get(StockID)).getData();
            remainingAmount.setText(this.xStock.getRemaining() + "");
            stockCost.setText(this.xStock.getCost() + "");
            currentAmount.setText(this.xStock.getRemaining() + "");
            stockOutAmount.setText(this.xStock.getStockOut() + "");
            newStockAmount.setText(this.xStock.getStockOut() + "");
            exportAmount.setText("0");
            totalValue.setText("0");
        }
    }

    @Override
    public void language_transform() {
        jLabel14.setText(bundle.get("StockExport.jLabel14.text"));
        jLabel13.setText(bundle.get("StockExport.jLabel13.text"));
        jLabel12.setText(bundle.get("StockExport.jLabel12.text"));
        jLabel4.setText(bundle.get("StockExport.jLabel4.text"));
        submitExportStock.setText(bundle.get("StockExport.submitExportStock.text"));
        totalValue.setText(bundle.get("StockExport.totalValue.text"));
        jLabel2.setText(bundle.get("StockExport.jLabel2.text"));
        jLabel15.setText(bundle.get("StockExport.jLabel15.text"));
        jLabel1.setText(bundle.get("StockExport.jLabel1.text"));
    }
}
