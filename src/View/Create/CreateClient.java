
package View.Create;

import Cache.cClient;
import Data.Databank;
import Middleware.Validation;
import Model.ClientModel;
import Model.Model;
import View.Dialog;
import View.General;
import View.Interface.IBundle;
import View.VValidate;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateClient extends Dialog implements VValidate, IBundle {
    public CreateClient() {
        super("Create");
        initComponents();
        language_transform();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fullName = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        createClient = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewCreate"); // NOI18N
        jLabel1.setText(bundle.getString("CreateClient.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("CreateClient.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("CreateClient.jLabel3.text")); // NOI18N

        fullName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullNameActionPerformed(evt);
            }
        });

        phone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        createClient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createClient.setText(bundle.getString("CreateClient.createClient.text")); // NOI18N
        createClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createClientActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("CreateClient.jLabel4.text")); // NOI18N

        address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(22, 22, 22)
                            .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(createClient, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createClient)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullNameActionPerformed

    private void createClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createClientActionPerformed
        
        if (!validating()) {
            getValidationMessage();
            return;
        }
        
        Databank data = this.mainFrame.data;
        cClient xClient = new cClient();

        int lastID = data.lastClient.id;
        if (lastID == -1) lastID = 0;
        xClient.setId(lastID + 1);
        xClient.setName(fullName.getText());
        xClient.setPhone(phone.getText());
        xClient.setAddress(address.getText());
        try {
            xClient.setDateCreate(Model.getTimeGlobal());
        } catch (ParseException ex) {
            Logger.getLogger(CreateClient.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        ClientModel client_mdl = new ClientModel();
        client_mdl.create(xClient);
        setVisible(false);
        reset();
        mainFrame.clientController.append(xClient);
    }//GEN-LAST:event_createClientActionPerformed

    public void reset() {
        address.setText("");
        fullName.setText("");
        phone.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JButton createClient;
    private javax.swing.JTextField fullName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField phone;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean validating() {
        return (Validation.v_require(address.getText()) &&
            Validation.v_require(fullName.getText()) &&
            Validation.v_require(phone.getText())
        );
    }

    @Override
    public void language_transform() {
        createClient.setText(bundle.get("CreateClient.createClient.text"));
        jLabel3.setText(bundle.get("CreateClient.jLabel3.text"));
        jLabel2.setText(bundle.get("CreateClient.jLabel2.text"));
        jLabel1.setText(bundle.get("CreateClient.jLabel1.text"));
        jLabel4.setText(bundle.get("CreateClient.jLabel4.text"));
    }
}
