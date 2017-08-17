
package View.Create;

import Cache.cUser;
import Data.Databank;
import Entity.User;
import Model.Model;
import Model.UserModel;
import Middleware.Validation;
import View.Dialog;
import View.General;
import View.Interface.IBundle;
import View.VValidate;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

public class CreateUser extends Dialog implements VValidate, IBundle {
    public CreateUser() {
        super("Create");
        initComponents();
        language_transform();
        for (int i = 0; i < User.N_ROLES; i++) {
            user_privilege.addItem(User.USER_ROLES[i]);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        user_fullName = new javax.swing.JTextField();
        user_userName = new javax.swing.JTextField();
        user_privilege = new javax.swing.JComboBox<>();
        submitCreateUser = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        user_password = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewCreate"); // NOI18N
        jLabel1.setText(bundle.getString("CreateUser.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("CreateUser.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("CreateUser.jLabel3.text")); // NOI18N

        user_fullName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_fullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_fullNameActionPerformed(evt);
            }
        });

        user_userName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        user_privilege.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_privilege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_privilegeActionPerformed(evt);
            }
        });

        submitCreateUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitCreateUser.setText(bundle.getString("CreateUser.submitCreateUser.text")); // NOI18N
        submitCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCreateUserActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("CreateUser.jLabel4.text")); // NOI18N

        user_password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_passwordActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText(bundle.getString("CreateUser.jLabel5.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(user_fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(submitCreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(user_privilege, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(user_password, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(user_userName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(user_fullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(user_userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(user_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_privilege, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitCreateUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void user_privilegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_privilegeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_privilegeActionPerformed

    private void user_fullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_fullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_fullNameActionPerformed

    private void submitCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCreateUserActionPerformed
        try {
            Databank data = this.mainFrame.data;
            cUser xUser = new cUser();
            
            int lastID = data.lastUser.id;
            if (lastID == -1) lastID = 0;
            xUser.setId(lastID + 1);
            xUser.setFullname(user_fullName.getText());
            xUser.setUsername(user_userName.getText());
            xUser.setPassword(user_password.getText());
            xUser.setPrivilege(user_privilege.getSelectedIndex());
            xUser.setDate(Model.getTimeGlobal());
            UserModel user_mdl = new UserModel();
            user_mdl.create(xUser);
            setVisible(false);
            reset();
            mainFrame.userController.append(xUser);
        } catch (ParseException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submitCreateUserActionPerformed

    private void user_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_passwordActionPerformed

    public void reset() {
        user_fullName.setText("");
        user_password.setText("");
        user_privilege.setSelectedIndex(0);
        user_userName.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton submitCreateUser;
    private javax.swing.JTextField user_fullName;
    private javax.swing.JTextField user_password;
    private javax.swing.JComboBox<String> user_privilege;
    private javax.swing.JTextField user_userName;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean validating() {
        return false;
    }

    @Override
    public void language_transform() {
        jLabel3.setText(bundle.get("CreateUser.jLabel3.text"));
        jLabel2.setText(bundle.get("CreateUser.jLabel2.text"));
        jLabel1.setText(bundle.get("CreateUser.jLabel1.text"));
        jLabel5.setText(bundle.get("CreateUser.jLabel5.text"));
        jLabel4.setText(bundle.get("CreateUser.jLabel4.text"));
        submitCreateUser.setText(bundle.get("CreateUser.submitCreateUser.text"));
    }
}
