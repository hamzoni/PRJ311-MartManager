
package View.Update;

import Cache.cCategory;
import Cache.cProduct;
import Entity.Category;
import Entity.Product;
import Middleware.Validation;
import Model.CategoryModel;
import View.Dialog;
import View.Interface.IBundle;
import View.VValidate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpdateCategory extends Dialog implements UpdateFrame, VValidate, IBundle {
    private cCategory xCategory;
    public UpdateCategory() {
        super("Update");
        initComponents();
        language_transform();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        categoryName = new javax.swing.JTextField();
        submit_createCategory = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        currentName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewUpdate"); // NOI18N
        jLabel1.setText(bundle.getString("UpdateCategory.jLabel1.text")); // NOI18N

        categoryName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        categoryName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryNameActionPerformed(evt);
            }
        });

        submit_createCategory.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submit_createCategory.setText(bundle.getString("UpdateCategory.submit_createCategory.text")); // NOI18N
        submit_createCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_createCategoryActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("UpdateCategory.jLabel4.text")); // NOI18N

        currentName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        currentName.setText(bundle.getString("UpdateCategory.currentName.text")); // NOI18N

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
                        .addComponent(currentName))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(currentName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
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
        CategoryModel ctg_mdl = new CategoryModel();
        ctg_mdl.setCategory(this.xCategory);
        
        cCategory newCategory = new cCategory();
        newCategory.setId(this.xCategory.getId());
        newCategory.setName(categoryName.getText());

        Category newcCategory = new Category();
        Category oldcCategory = (Category) this.mainFrame.data.categories.get(newCategory.getId());
        newcCategory.productsIDs = oldcCategory.productsIDs;
        newcCategory.setData(newCategory);
        this.mainFrame.data.categories.replace(this.xCategory.getId(), newcCategory);
        // UPDATE CATEGORY NAME IN PRODUCT
        HashMap products = this.mainFrame.data.products;
        Iterator it = products.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Product product = (Product) pair.getValue();
            cProduct xProduct = (cProduct) product.getData();
            if (xProduct.getCategory_id() == newCategory.getId()) {
                xProduct.setCategoryName(categoryName.getText());
                product.setData(xProduct);
            }
        }
        
        ctg_mdl.update(newCategory);
        this.mainFrame.categoryController.load_ram_data();
        this.mainFrame.productController.load_ram_data();
        this.setVisible(false);
    }//GEN-LAST:event_submit_createCategoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField categoryName;
    private javax.swing.JLabel currentName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton submit_createCategory;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setFrameData(Object data) {
        cCategory xCategory = (cCategory) data;
        this.xCategory = xCategory;
        currentName.setText(xCategory.getName());
        categoryName.setText(xCategory.getName());
    }

    @Override
    public boolean validating() {
        return Validation.v_require(categoryName.getText());
    }

    @Override
    public void language_transform() {
        jLabel4.setText(bundle.get("UpdateCategory.jLabel4.text"));
        submit_createCategory.setText(bundle.get("UpdateCategory.submit_createCategory.text"));
        jLabel1.setText(bundle.get("UpdateCategory.jLabel1.text"));
        currentName.setText(bundle.get("UpdateCategory.currentName.text"));
    }
}
