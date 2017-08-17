package View.Update;

import Cache.cClient;
import Entity.Client;
import Middleware.Validation;
import Model.ClientModel;
import View.Dialog;
import View.Interface.IBundle;
import View.VValidate;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateClient extends Dialog implements UpdateFrame, VValidate, IBundle {
    private cClient xclient;
    private ClientModel clt_mdl;
    public UpdateClient() {
        super("Update");
        initComponents();
        language_transform();
        clt_mdl = new ClientModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fullName = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        updateClient = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        current_client_id = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewUpdate"); // NOI18N
        jLabel1.setText(bundle.getString("UpdateClient.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("UpdateClient.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("UpdateClient.jLabel3.text")); // NOI18N

        fullName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullNameActionPerformed(evt);
            }
        });

        phone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        updateClient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        updateClient.setText(bundle.getString("UpdateClient.updateClient.text")); // NOI18N
        updateClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateClientActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("UpdateClient.jLabel4.text")); // NOI18N

        address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        current_client_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        current_client_id.setText(bundle.getString("UpdateClient.current_client_id.text")); // NOI18N

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
                        .addComponent(current_client_id))
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
                                .addComponent(updateClient, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(current_client_id))
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
                .addComponent(updateClient)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullNameActionPerformed

    private void updateClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateClientActionPerformed
        cClient newClient = new cClient();
        newClient.setId(this.xclient.getId());
        newClient.setName(fullName.getText());
        newClient.setPhone(phone.getText());
        newClient.setAddress(address.getText());
        try {
            newClient.setDateCreate(this.xclient.getDateCreate());
        } catch (ParseException ex) {
            Logger.getLogger(UpdateClient.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        Client newcClient = new Client();
        newcClient.setData(newClient);
        this.mainFrame.data.clients.replace(this.xclient.getId(), newcClient);
        
        this.clt_mdl.setClient(this.xclient);
        this.clt_mdl.update(newClient);
        this.mainFrame.clientController.load_ram_data();
        this.setVisible(false);
    }//GEN-LAST:event_updateClientActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JLabel current_client_id;
    private javax.swing.JTextField fullName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField phone;
    private javax.swing.JButton updateClient;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setFrameData(Object data) {
        cClient xclient = (cClient) data;
        this.xclient = xclient;
        current_client_id.setText("#" + xclient.getId());
        address.setText(xclient.getAddress());
        fullName.setText(xclient.getName());
        phone.setText(xclient.getPhone());
    }

    @Override
    public boolean validating() {
        return (Validation.v_require(address.getText()) &&
            Validation.v_require(fullName.getText()) &&
            Validation.v_require(phone.getText())
        );
    }

    @Override
    public void language_transform() {
        jLabel1.setText(bundle.get("UpdateClient.jLabel1.text"));
        current_client_id.setText(bundle.get("UpdateClient.current_client_id.text"));
        jLabel4.setText(bundle.get("UpdateClient.jLabel4.text"));
        updateClient.setText(bundle.get("UpdateClient.updateClient.text"));
        jLabel3.setText(bundle.get("UpdateClient.jLabel3.text"));
        jLabel2.setText(bundle.get("UpdateClient.jLabel2.text"));
    }
}
