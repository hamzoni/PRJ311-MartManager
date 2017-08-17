
package View.Update;

import Cache.cCart;
import Cache.cClient;
import Cache.cInvoice;
import Cache.cProduct;
import Cache.cStock;
import Cache.cUser;
import Entity.Cart;
import Entity.Client;
import Entity.Invoice;
import Entity.Product;
import Entity.Stock;
import Entity.User;
import Middleware.Validation;
import Model.CartModel;
import Model.InvoiceModel;
import Model.StockModel;
import View.Dialog;
import View.General;
import View.Interface.IBundle;
import View.VValidate;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UpdateInvoice extends Dialog implements UpdateFrame, VValidate, IBundle {
    public General mainFrame;
    private Invoice invoice;
    private ArrayList<Integer> availableItemIDs;
    private ArrayList<Integer> addedItemIDs;
    private ArrayList<Cart> carts;
    private ArrayList<Integer> clientsID;
    private InvoiceModel invoice_mdl;
    private CartModel cart_mdl;
    
    private int selectedCartRow = -1;
    private int addItemCount = 1;
    public int selectedRow = 0;
    public UpdateInvoice() {
        super("Update");
        initComponents();
        language_transform();
        invoice_mdl = new InvoiceModel();
        cart_mdl = new CartModel();
        cartTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                DefaultTableModel model = (DefaultTableModel)cartTable.getModel();
                if (me.getClickCount() == 2) selectedCartRow = cartTable.getSelectedRow();
            }
            public void mouseClicked(MouseEvent me) {
                selectedCartRow = cartTable.getSelectedRow();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        invoicerName = new javax.swing.JTextField();
        paymentMethod = new javax.swing.JTextField();
        clientList = new javax.swing.JComboBox<>();
        updateInvoice = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jToolBar2 = new javax.swing.JToolBar();
        totalCartValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(500, 0), new java.awt.Dimension(500, 0), new java.awt.Dimension(500, 32767));
        jToolBar1 = new javax.swing.JToolBar();
        itemsList = new javax.swing.JComboBox<>();
        addItem = new javax.swing.JButton();
        removeItem = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        invoice_id = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewUpdate"); // NOI18N
        jLabel1.setText(bundle.getString("UpdateInvoice.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("UpdateInvoice.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("UpdateInvoice.jLabel3.text")); // NOI18N

        invoicerName.setEditable(false);
        invoicerName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        invoicerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoicerNameActionPerformed(evt);
            }
        });

        paymentMethod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        clientList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        clientList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientListActionPerformed(evt);
            }
        });

        updateInvoice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        updateInvoice.setText(bundle.getString("UpdateInvoice.updateInvoice.text")); // NOI18N
        updateInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateInvoiceActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("UpdateInvoice.jLabel4.text")); // NOI18N

        jToolBar2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("UpdateInvoice.jToolBar2.border.title"))); // NOI18N
        jToolBar2.setRollover(true);
        jToolBar2.setEnabled(false);

        totalCartValue.setText(bundle.getString("UpdateInvoice.totalCartValue.text")); // NOI18N
        jToolBar2.add(totalCartValue);

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Product name", "Qty", "Unit price", "Total", "Available"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cartTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cartTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(cartTable);
        if (cartTable.getColumnModel().getColumnCount() > 0) {
            cartTable.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("UpdateInvoice.cartTable.columnModel.title0")); // NOI18N
            cartTable.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("UpdateInvoice.cartTable.columnModel.title1")); // NOI18N
            cartTable.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("UpdateInvoice.cartTable.columnModel.title2")); // NOI18N
            cartTable.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("UpdateInvoice.cartTable.columnModel.title3")); // NOI18N
            cartTable.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("UpdateInvoice.cartTable.columnModel.title4")); // NOI18N
            cartTable.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("UpdateInvoice.cartTable.columnModel.title5")); // NOI18N
        }

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("UpdateInvoice.jToolBar1.border.title"))); // NOI18N
        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        itemsList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.add(itemsList);

        addItem.setText(bundle.getString("UpdateInvoice.addItem.text")); // NOI18N
        addItem.setFocusable(false);
        addItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addItem.setMaximumSize(new java.awt.Dimension(67, 25));
        addItem.setMinimumSize(new java.awt.Dimension(67, 25));
        addItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemActionPerformed(evt);
            }
        });
        jToolBar1.add(addItem);

        removeItem.setText(bundle.getString("UpdateInvoice.removeItem.text")); // NOI18N
        removeItem.setFocusable(false);
        removeItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeItem.setMaximumSize(new java.awt.Dimension(67, 25));
        removeItem.setMinimumSize(new java.awt.Dimension(67, 25));
        removeItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemActionPerformed(evt);
            }
        });
        jToolBar1.add(removeItem);

        invoice_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        invoice_id.setText(bundle.getString("UpdateInvoice.invoice_id.text")); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/invoice.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(10, 10, 10)))
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(invoice_id))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(117, 117, 117))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(14, 14, 14)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(paymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(clientList, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(invoicerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(invoice_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(invoicerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(clientList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(paymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateInvoice)
                        .addGap(10, 10, 10))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void invoicerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoicerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_invoicerNameActionPerformed

    private void clientListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientListActionPerformed

    private void updateInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateInvoiceActionPerformed
        if (validateData()) {
            // SET UP DATA
            Invoice newInvoice = new Invoice();
            cInvoice xInvoice = new cInvoice();
            
            cInvoice crrInvoice = (cInvoice) invoice.getData();
            ArrayList<Cart> crrCarts = invoice.carts;
            newInvoice.carts = new ArrayList<Cart>();
            
            xInvoice.setId(crrInvoice.getId());
            xInvoice.setClient_id(clientsID.get(clientList.getSelectedIndex()));
            xInvoice.setUser_id(crrInvoice.getUser_id());
            xInvoice.setPayment_method(paymentMethod.getText());
            xInvoice.setDate_create(crrInvoice.getDate_create());
            
            // UPDATE INVOICE ID
            newInvoice.setData(xInvoice);
            invoice_mdl.setInvoice(crrInvoice);
            invoice_mdl.update(xInvoice);
            
            // DELETE ALL CURRENT CART
            try {
                for (int i = 0; i < crrCarts.size(); i++) {
                    cCart xcrrCart = (cCart) crrCarts.get(i).getData();
                    int xcrrPrd = xcrrCart.getProduct_id();
                    int xcrrInv = xcrrCart.getInvoice_id();
                    cart_mdl.deleteBySuperKeys(xcrrInv, xcrrPrd);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UpdateInvoice.class.getName()).log(Level.SEVERE, null, ex);
            }
            // CREATE NEW CART AND ADD THEM TO NEW INVOICE
            DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
            int rowsCount = model.getRowCount();
            
            for (int i = 0; i < rowsCount; i++) {
                Cart newCart = new Cart();
                cCart xNewCart = new cCart();
                
                int cPrdID = addedItemIDs.get(i);
                int cInvID = crrInvoice.getId();
                int cQty = (int) model.getValueAt(i, 2);
                float cUPr = (float) model.getValueAt(i, 3);
                
                xNewCart.setInvoice_id(cInvID);
                xNewCart.setProduct_id(cPrdID);
                xNewCart.setQuantity(cQty);
                xNewCart.setUnit_price(cUPr);
                
                newCart.setData(xNewCart);
                newInvoice.carts.add(newCart);
                cart_mdl.create(xNewCart);
                
                // update stock
                Stock stock = ((Product) this.mainFrame.data.products.get(addedItemIDs.get(i))).getStock();
                cStock xstock = (cStock) stock.getData();
                int stockOut = xstock.getStockOut() - xNewCart.getQuantity();
                int stockIn = xstock.getRemaining();
                xstock.setStockOut(stockOut);
                stock.setData(xstock);
                this.mainFrame.data.stocks.replace(xstock.getId(), stock);
                this.mainFrame.stockController.load_ram_data();
                StockModel stk_mdl = new StockModel();
                stk_mdl.exportStock(xstock.getId(), stockIn, stockOut, null);
            }
            this.mainFrame.data.invoices.replace(crrInvoice.getId(), newInvoice);
            this.mainFrame.invoiceController.load_ram_data();
            setVisible(false);
        }
    }//GEN-LAST:event_updateInvoiceActionPerformed
    private boolean validateData(){
        boolean vld = !paymentMethod.getText().isEmpty();
        vld = vld && !addedItemIDs.isEmpty();
        return vld;
    }
    private void addItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemActionPerformed
       int selectedItem = itemsList.getSelectedIndex();
        if (selectedItem == -1) return;
        int selectedProductID = availableItemIDs.get(selectedItem);
        HashMap productsH = this.mainFrame.data.products;
        cProduct selectedProduct = (cProduct)((Product)productsH.get(selectedProductID)).getData();

        availableItemIDs.remove(selectedItem);
        addedItemIDs.add(selectedProductID);
        itemsList.removeItemAt(selectedItem);
        
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.addRow(new Object[]{addItemCount++, selectedProduct.getName(), 1, selectedProduct.getPrice(), selectedProduct.getPrice()});
        
        float currentTotal = Float.parseFloat(totalCartValue.getText());
        currentTotal += selectedProduct.getPrice();
        totalCartValue.setText("" + currentTotal);
    }//GEN-LAST:event_addItemActionPerformed

    private void removeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemActionPerformed
        int idx = this.selectedCartRow;
        if (idx == -1 || addedItemIDs.size() == 0) return;
        int selectedProductID = addedItemIDs.get(idx);
        availableItemIDs.add(selectedProductID);
        addedItemIDs.remove(idx);

        HashMap productsH = this.mainFrame.data.products;
        cProduct selectedProduct = (cProduct)((Product)productsH.get(selectedProductID)).getData();

        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        float currentTotal = Float.parseFloat(totalCartValue.getText());
        currentTotal -= (float)model.getValueAt(idx, 4);
        totalCartValue.setText("" + currentTotal);

        itemsList.addItem(selectedProduct.getName());
        model.removeRow(idx);
        addItemCount--;
    }//GEN-LAST:event_removeItemActionPerformed

    private void cartTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cartTablePropertyChange
        try {
            DefaultTableModel model = (DefaultTableModel)cartTable.getModel();
            int selectedRow = cartTable.getSelectedRow();
            int columns = cartTable.getColumnCount();
            int rows = cartTable.getRowCount();
            int productID = addedItemIDs.get(selectedRow);
            
            int currentQuantity = 0;
            ArrayList<Cart> carts = invoice.carts;
            for (Cart cart : carts) {
                cCart scart = (cCart) cart.getData();
                if (scart.getProduct_id() == productID) {
                    currentQuantity = scart.getQuantity();
                    break;
                }
            }
            
            int qty = (int) model.getValueAt(selectedRow, 2);
            int qtyOffset = qty - currentQuantity;
            float unitPrice = (float) model.getValueAt(selectedRow, 3);
            float totalPrice = unitPrice * qty;
            model.setValueAt(totalPrice, selectedRow, 4);
            
            
            Product product = (Product)this.mainFrame.data.products.get(productID);
            cStock xStock = (cStock) product.getStock().getData();
            qty = qty > xStock.getStockOut() ? xStock.getStockOut() : qty;
            
            int newRemaining = xStock.getStockOut() - qtyOffset;
            model.setValueAt(qty, selectedRow, 2);
            model.setValueAt(newRemaining, selectedRow, 5);
            
            int currentTotal = 0;
            for (int i = 0; i < rows; i++) {
                currentTotal += (float) model.getValueAt(i, 4);
            }
            totalCartValue.setText("" + currentTotal);
        } catch (Exception e) {}
    }//GEN-LAST:event_cartTablePropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItem;
    private javax.swing.JTable cartTable;
    private javax.swing.JComboBox<String> clientList;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel invoice_id;
    private javax.swing.JTextField invoicerName;
    private javax.swing.JComboBox<String> itemsList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTextField paymentMethod;
    private javax.swing.JButton removeItem;
    private javax.swing.JLabel totalCartValue;
    private javax.swing.JButton updateInvoice;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setFrameData(Object data) {
        setupData();
        Invoice invoice = (Invoice) data;
        cInvoice xInvoice = (cInvoice) invoice.getData();
        
        HashMap users = this.mainFrame.data.users;
        HashMap clients = this.mainFrame.data.clients;
        HashMap products = this.mainFrame.data.products;
        
        this.invoice = invoice;
        
        int invoicerID = xInvoice.getUser_id();
        User user = (User) users.get(invoicerID);
        cUser xuser = (cUser) user.getData();
        invoicerName.setText(xuser.getFullname());
        invoice_id.setText("#" + invoicerID);
        
        for (int i = 0; i < clientsID.size(); i++) {
            if (clientsID.get(i) == xInvoice.getClient_id()) {
                clientList.setSelectedIndex(i);
            }
        }
        
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setRowCount(0);
        int stt = 0, qty = 1;
        float price = 0, total = 0;
        String productName;
        
        for (Cart cart : invoice.carts) {
            cCart xCart = (cCart) cart.getData();
            Product prd = (Product) products.get(xCart.getProduct_id());
            if (prd == null) continue;
            cProduct xPrd = (cProduct) prd.getData();
            stt += 1;
            qty = xCart.getQuantity();
            price = xPrd.getPrice();
            total += qty * price;
            productName = xPrd.getName();
            
            Stock stock = prd.getStock();
            int stockOutV = 0;
            if (stock != null) {
                cStock xstock = (cStock) stock.getData();
                stockOutV =  xstock.getStockOut();
            }
            model.addRow(new Object[]{stt, productName, qty, price, qty * price, stockOutV});
        }
        totalCartValue.setText(total + "");
        if (itemsList.getItemCount() > 0) {
            for (int i = 0; i < availableItemIDs.size(); i++) {
                for (int j = 0; j < invoice.carts.size(); j++) {
                    cCart xCart = (cCart) invoice.carts.get(j).getData();
                    if (availableItemIDs.get(i) == xCart.getProduct_id()) {
                        availableItemIDs.remove(i);
                        addedItemIDs.add(xCart. getProduct_id());
                        itemsList.removeItemAt(i);
                    }
                }
            }
        }
        paymentMethod.setText(xInvoice.getPayment_method());
    }
    public void setupData() {
        carts = new ArrayList<Cart>();
        addedItemIDs = new ArrayList<Integer>();
        availableItemIDs = new ArrayList<Integer>();
        itemsList.removeAllItems();
        setItemList();
        
        clientsID = new ArrayList<Integer>();
        setClientList();
        totalCartValue.setText("0");
    }
    public void setClientList() {
        HashMap clientsH = this.mainFrame.data.clients;
        Iterator it = clientsH.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            cClient xClient = ((cClient)((Client)pair.getValue()).getData());
            String clientName = xClient.getName();
            int clientID = xClient.getId();
            clientList.addItem(clientName);
            clientsID.add(clientID);
        }
    }
    public void setItemList() {
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setRowCount(0);
        HashMap products = this.mainFrame.data.products;
        availableItemIDs = new ArrayList<Integer>();
        itemsList.removeAllItems();
        Iterator it = products.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Product product = (Product)pair.getValue();
            cProduct xProduct = ((cProduct)(product).getData());
            Stock stock = product.getStock();
            if (stock != null) {
                cStock cstock = (cStock) stock.getData();
                if (cstock.getStockOut() > 0) {
                    String prdName = xProduct.getName();
                    int prdID = xProduct.getId();
                    itemsList.addItem(prdName);
                    availableItemIDs.add(prdID);
                }
            }
        }
    }

    @Override
    public boolean validating() {
        return (Validation.v_require(paymentMethod.getText()) &&
            Validation.v_require(paymentMethod.getText()) &&
            (itemsList.getSelectedIndex() != -1) &&
            (clientList.getSelectedIndex() != -1)
        );
    }

    @Override
    public void language_transform() {
        invoice_id.setText(bundle.get("UpdateInvoice.invoice_id.text"));
        jLabel4.setText(bundle.get("UpdateInvoice.jLabel4.text"));
        removeItem.setText(bundle.get("UpdateInvoice.removeItem.text"));
        updateInvoice.setText(bundle.get("UpdateInvoice.updateInvoice.text"));
        addItem.setText(bundle.get("UpdateInvoice.addItem.text"));
        jLabel3.setText(bundle.get("UpdateInvoice.jLabel3.text"));
        jLabel2.setText(bundle.get("UpdateInvoice.jLabel2.text"));
        jLabel1.setText(bundle.get("UpdateInvoice.jLabel1.text"));
        totalCartValue.setText(bundle.get("UpdateInvoice.totalCartValue.text"));
        jToolBar2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.get("UpdateInvoice.jToolBar2.border.title")));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.get("UpdateInvoice.jToolBar1.border.title")));
        for (int i = 0; i < 6; i++) {
            set_title_table(cartTable, i, bundle.get("UpdateInvoice.cartTable.columnModel.title" + i));
        }
    }
    
}
