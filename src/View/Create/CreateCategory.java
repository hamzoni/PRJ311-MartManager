
package View.Create;

import Cache.cCategory;
import Data.Databank;
import Entity.Category;
import Language.Bundle;
import Middleware.Validation;
import Model.CategoryModel;
import View.Dialog;
import View.General;
import View.Interface.IBundle;
import View.VValidate;

public class CreateCategory extends Dialog implements VValidate, IBundle {
    public CreateCategory() {
        super("Create");
        initComponents();
        language_transform();
    }
    @Override
    public void language_transform() {
        submit_createCategory.setText(bundle.get("CreateCategory.submit_createCategory.text"));
        jLabel4_newCategory.setText(bundle.get("CreateCategory.jLabel4_newCategory.text"));
        jLabel1CategoryName.setText(bundle.get("CreateCategory.jLabel1CategoryName.text"));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1CategoryName = new javax.swing.JLabel();
        categoryName = new javax.swing.JTextField();
        submit_createCategory = new javax.swing.JButton();
        jLabel4_newCategory = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1CategoryName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewCreate"); // NOI18N
        jLabel1CategoryName.setText(bundle.getString("CreateCategory.jLabel1CategoryName.text")); // NOI18N

        categoryName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        categoryName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryNameActionPerformed(evt);
            }
        });

        submit_createCategory.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submit_createCategory.setText(bundle.getString("CreateCategory.submit_createCategory.text")); // NOI18N
        submit_createCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_createCategoryActionPerformed(evt);
            }
        });

        jLabel4_newCategory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4_newCategory.setText(bundle.getString("CreateCategory.jLabel4_newCategory.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4_newCategory)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1CategoryName)
                            .addGap(22, 22, 22)
                            .addComponent(categoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(submit_createCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4_newCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1CategoryName)
                    .addComponent(categoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submit_createCategory)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void categoryNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryNameActionPerformed

    private void submit_createCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_createCategoryActionPerformed
        
        if (!validating()) {
            getValidationMessage();
            return;
        }
        
        Databank data = this.mainFrame.data;
        Category category = new Category();
        cCategory xCategory = new cCategory();

        int lastID = data.lastCategory.id;
        if (lastID == -1) lastID = 0;

        xCategory.setId(lastID + 1);
        xCategory.setName(categoryName.getText());
        
        CategoryModel ctg_mdl = new CategoryModel();
        ctg_mdl.create(xCategory);
        
        category.setData(xCategory);
        mainFrame.data.categories.put(xCategory.getId(), category);
        mainFrame.categoryController.append(xCategory);
        setVisible(false);
        reset();
    }//GEN-LAST:event_submit_createCategoryActionPerformed

     public void reset() {
        categoryName.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField categoryName;
    private javax.swing.JLabel jLabel1CategoryName;
    private javax.swing.JLabel jLabel4_newCategory;
    private javax.swing.JButton submit_createCategory;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean validating() {
        return Validation.v_require(categoryName.getText());
    }
}
