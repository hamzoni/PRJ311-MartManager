
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

public class StockImport extends StockView implements UpdateFrame, IBundle {
    public StockImport() {
        initComponents();
        language_transform();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        submitImportStock = new javax.swing.JButton();
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
        importAmount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewStock"); // NOI18N
        jLabel1.setText(bundle.getString("StockImport.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("StockImport.jLabel2.text")); // NOI18N

        submitImportStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitImportStock.setText(bundle.getString("StockImport.submitImportStock.text")); // NOI18N
        submitImportStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitImportStockActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("StockImport.jLabel4.text")); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText(bundle.getString("StockImport.jLabel12.text")); // NOI18N

        currentAmount.setEditable(false);
        currentAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText(bundle.getString("StockImport.jLabel13.text")); // NOI18N

        remainingAmount.setEditable(false);
        remainingAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText(bundle.getString("StockImport.jLabel14.text")); // NOI18N

        stockCost.setEditable(false);
        stockCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
        jLabel15.setText(bundle.getString("StockImport.jLabel15.text")); // NOI18N

        totalValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        totalValue.setText(bundle.getString("StockImport.totalValue.text")); // NOI18N

        importAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        importAmount.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                importAmountInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        importAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                importAmountKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitImportStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(stocksList, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel2))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(currentAmount))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14)
                                                .addComponent(stockCost, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(importAmount)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(23, 23, 23)
                                    .addComponent(remainingAmount)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalValue)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stocksList)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currentAmount)
                        .addComponent(stockCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(remainingAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(totalValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitImportStock)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitImportStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitImportStockActionPerformed
        int stockID = this.xStock.getId();
        int newRemaining = Integer.parseInt(remainingAmount.getText());
        if (this.xStock.getRemaining() != newRemaining) {
            stock_mdl.importStock(stockID, newRemaining, Model.getTimeGlobal());
        }
        this.xStock.setRemaining(newRemaining); 
        this.xStock.setLastImportedDate(new Date());
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
    }//GEN-LAST:event_submitImportStockActionPerformed

    private void stocksListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_stocksListPropertyChange
    }//GEN-LAST:event_stocksListPropertyChange

    private void stocksListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stocksListItemStateChanged
        if (stocksIDs.size() > 0) 
            putFrameData(stocksIDs.get(stocksList.getSelectedIndex()));
    }//GEN-LAST:event_stocksListItemStateChanged

    private void importAmountInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_importAmountInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_importAmountInputMethodTextChanged

    private void importAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_importAmountKeyReleased
        try {
            int impAmount = Integer.parseInt(importAmount.getText());
            int crrRemaining = Integer.parseInt(currentAmount.getText());
            float costUnit = Float.parseFloat(stockCost.getText());

            int remainingOffset = crrRemaining + impAmount;
            float importValue = impAmount * costUnit;

            remainingAmount.setText(remainingOffset + "");
            totalValue.setText(importValue + "");
        } catch (Exception e) {};
    }//GEN-LAST:event_importAmountKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField currentAmount;
    private javax.swing.JTextField importAmount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField remainingAmount;
    private javax.swing.JTextField stockCost;
    private javax.swing.JComboBox<String> stocksList;
    private javax.swing.JButton submitImportStock;
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
            totalValue.setText("0");
            currentAmount.setText(this.xStock.getRemaining() + "");
            importAmount.setText("0");
        }
    }

    @Override
    public void language_transform() {
        jLabel1.setText(bundle.get("StockImport.jLabel1.text"));
        totalValue.setText(bundle.get("StockImport.totalValue.text"));
        jLabel15.setText(bundle.get("StockImport.jLabel15.text"));
        jLabel14.setText(bundle.get("StockImport.jLabel14.text"));
        jLabel13.setText(bundle.get("StockImport.jLabel13.text"));
        jLabel12.setText(bundle.get("StockImport.jLabel12.text"));
        jLabel4.setText(bundle.get("StockImport.jLabel4.text"));
        submitImportStock.setText(bundle.get("StockImport.submitImportStock.text"));
        jLabel2.setText(bundle.get("StockImport.jLabel2.text"));
    }
}
