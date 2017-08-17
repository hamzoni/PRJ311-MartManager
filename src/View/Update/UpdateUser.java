
package View.Update;

import Cache.cProduct;
import Cache.cUser;
import Entity.User;
import Middleware.Authentication;
import Middleware.Validation;
import Model.UserModel;
import View.Dialog;
import View.General;
import View.Interface.IBundle;
import View.VValidate;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateUser extends Dialog implements UpdateFrame, VValidate, IBundle {
    private UserModel user_mdl;
    private cUser xuser;
    public UpdateUser() {
        super("Update");
        initComponents();
        language_transform();
        user_mdl = new UserModel();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        user_userName = new javax.swing.JTextField();
        user_fullName = new javax.swing.JTextField();
        submitUpdateUser = new javax.swing.JButton();
        userFullName = new javax.swing.JLabel();
        user_password = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        user_privilege = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        user_userName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        user_fullName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_fullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_fullNameActionPerformed(evt);
            }
        });

        submitUpdateUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewUpdate"); // NOI18N
        submitUpdateUser.setText(bundle.getString("UpdateUser.submitUpdateUser.text")); // NOI18N
        submitUpdateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitUpdateUserActionPerformed(evt);
            }
        });

        userFullName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userFullName.setText(bundle.getString("UpdateUser.userFullName.text")); // NOI18N

        user_password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_passwordActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("UpdateUser.jLabel3.text")); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText(bundle.getString("UpdateUser.jLabel5.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("UpdateUser.jLabel4.text")); // NOI18N

        user_privilege.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_privilege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_privilegeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("UpdateUser.jLabel2.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText(bundle.getString("UpdateUser.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userFullName))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(submitUpdateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(user_password)
                            .addComponent(user_userName)
                            .addComponent(user_fullName)
                            .addComponent(user_privilege, 0, 197, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(userFullName))
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
                .addComponent(submitUpdateUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void user_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_passwordActionPerformed

    private void submitUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitUpdateUserActionPerformed

        if (!validating()) {
            getValidationMessage();
            return;
        }

        cUser newUser = new cUser();
        newUser.setId(this.xuser.getId());
        newUser.setFullname(user_fullName.getText());
        newUser.setUsername(user_userName.getText());
        newUser.setPassword(user_password.getText());
        newUser.setPrivilege(user_privilege.getSelectedIndex());
        try {
            newUser.setDate(this.xuser.getDate());
        } catch (ParseException ex) {
            Logger.getLogger(UpdateUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        User newcUser = new User();
        newcUser.setData(newUser);
        this.mainFrame.data.users.replace(this.xuser.getId(), newcUser);

        this.user_mdl.setUser(this.xuser);
        this.user_mdl.update(newUser);
        this.mainFrame.userController.load_ram_data();

        if (newUser.getId() == Authentication.getID()) {
            Authentication.UPDATE(newUser);
            this.mainFrame.setUserAccountData();
        }

        this.setVisible(false);
    }//GEN-LAST:event_submitUpdateUserActionPerformed

    private void user_privilegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_privilegeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_privilegeActionPerformed

    private void user_fullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_fullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_fullNameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton submitUpdateUser;
    private javax.swing.JLabel userFullName;
    private javax.swing.JTextField user_fullName;
    private javax.swing.JTextField user_password;
    private javax.swing.JComboBox<String> user_privilege;
    private javax.swing.JTextField user_userName;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setFrameData(Object data) {
        cUser xuser = (cUser) data;
        this.xuser = xuser;
        
        user_fullName.setText(xuser.getFullname());
        userFullName.setText(xuser.getFullname());
        user_password.setText("");
        user_userName.setText(xuser.getUsername());
        if (user_privilege.getItemCount() < User.N_ROLES) {
            for (int i = 0; i < User.N_ROLES; i++) {
                user_privilege.addItem(User.USER_ROLES[i]);
            }
        }
        for (int i = 0; i < User.N_ROLES; i++) {
            if (i == xuser.getPrivilege()) {
                user_privilege.setSelectedIndex(i);
            }
        }
    }

    @Override
    public boolean validating() {
        return (Validation.v_require(user_fullName.getText()) &&
            Validation.v_require(user_userName.getText()) &&
            (user_privilege.getSelectedIndex() != -1)
        );
    }

    @Override
    public void language_transform() {
        jLabel1.setText(bundle.get("UpdateUser.jLabel1.text"));
        userFullName.setText(bundle.get("UpdateUser.userFullName.text"));
        jLabel5.setText(bundle.get("UpdateUser.jLabel5.text"));
        jLabel4.setText(bundle.get("UpdateUser.jLabel4.text"));
        submitUpdateUser.setText(bundle.get("UpdateUser.submitUpdateUser.text"));
        jLabel3.setText(bundle.get("UpdateUser.jLabel3.text"));
        jLabel2.setText(bundle.get("UpdateUser.jLabel2.text"));
    }
}
