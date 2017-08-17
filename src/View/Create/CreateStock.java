
package View.Create;

import Cache.cStock;
import Data.Databank;
import Middleware.Validation;
import Model.StockModel;
import View.Dialog;
import View.Interface.IBundle;
import View.VValidate;
import java.util.Date;

public class CreateStock extends Dialog implements VValidate, IBundle {

    public CreateStock() {
        super("Create");
        initComponents();
        language_transform();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        stockName = new javax.swing.JTextField();
        stockCost = new javax.swing.JTextField();
        submitCreateStock = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        stockOrigin = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        stockProducer = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        stockAmount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewCreate"); // NOI18N
        jLabel1.setText(bundle.getString("CreateStock.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("CreateStock.jLabel2.text")); // NOI18N

        stockName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stockName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockNameActionPerformed(evt);
            }
        });

        stockCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        submitCreateStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitCreateStock.setText(bundle.getString("CreateStock.submitCreateStock.text")); // NOI18N
        submitCreateStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCreateStockActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("CreateStock.jLabel4.text")); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText(bundle.getString("CreateStock.jLabel12.text")); // NOI18N

        stockOrigin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText(bundle.getString("CreateStock.jLabel13.text")); // NOI18N

        stockProducer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText(bundle.getString("CreateStock.jLabel14.text")); // NOI18N

        stockAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
                            .addComponent(stockName)
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(submitCreateStock, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(stockName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(submitCreateStock)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void stockNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockNameActionPerformed
        
    }//GEN-LAST:event_stockNameActionPerformed

    private void submitCreateStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCreateStockActionPerformed
        
        if (!validating()) {
            getValidationMessage();
            return;
        }
        
        Databank data = this.mainFrame.data;
        cStock xstock = new cStock();
        
        int lastID = data.lastStock.id;
        if (lastID == -1) lastID = 0;
        xstock.setId(lastID + 1);
        xstock.setName(stockName.getText());
        xstock.setProducer(stockProducer.getText());
        xstock.setOrigin(stockOrigin.getText());
        xstock.setRemaining(Integer.parseInt(stockAmount.getText()));
        xstock.setCost(Integer.parseInt(stockCost.getText()));
        xstock.setLastImportedDate(new Date());
        xstock.setLastExportedDate(new Date());
                
        StockModel stk_mdl = new StockModel();
        stk_mdl.create(xstock);
        setVisible(false);
        reset();
        mainFrame.stockController.append(xstock);
    }//GEN-LAST:event_submitCreateStockActionPerformed
    public void reset() {
        stockName.setText("");
        stockCost.setText("");
        stockAmount.setText("");
        stockName.setText("");
        stockProducer.setText("");
        stockOrigin.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField stockAmount;
    private javax.swing.JTextField stockCost;
    private javax.swing.JTextField stockName;
    private javax.swing.JTextField stockOrigin;
    private javax.swing.JTextField stockProducer;
    private javax.swing.JButton submitCreateStock;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean validating() {
        return (Validation.v_require(stockAmount.getText()) &&
            Validation.v_require(stockCost.getText()) &&
            Validation.v_require(stockName.getText()) &&
            Validation.v_require(stockOrigin.getText()) &&
            Validation.v_require(stockProducer.getText()) &&
            Validation.v_int(stockAmount.getText()) &&
            Validation.v_float(stockCost.getText())
        );
    }

    @Override
    public void language_transform() {
        jLabel14.setText(bundle.get("CreateStock.jLabel14.text"));
        jLabel13.setText(bundle.get("CreateStock.jLabel13.text"));
        jLabel12.setText(bundle.get("CreateStock.jLabel12.text"));
        jLabel4.setText(bundle.get("CreateStock.jLabel4.text"));
        submitCreateStock.setText(bundle.get("CreateStock.submitCreateStock.text"));
        jLabel2.setText(bundle.get("CreateStock.jLabel2.text"));
        jLabel1.setText(bundle.get("CreateStock.jLabel1.text"));
    }
}
