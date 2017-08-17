
package View.Create;

import Cache.cCart;
import Cache.cClient;
import Cache.cInvoice;
import Cache.cProduct;
import Cache.cStock;
import Data.Databank;
import Entity.Cart;
import Entity.Client;
import Entity.Invoice;
import Entity.Product;
import Entity.Stock;
import Middleware.Authentication;
import Middleware.Validation;
import Model.CartModel;
import Model.InvoiceModel;
import Model.Model;
import Model.StockModel;
import View.Dialog;
import View.Interface.IBundle;
import View.VValidate;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class CreateInvoice extends Dialog implements VValidate, IBundle {
    private ArrayList<Integer> availableItemIDs;
    private ArrayList<Integer> addedItemIDs;
    private HashMap<Integer, ArrayList<Integer>> addedStocksIDs;
    private ArrayList<Cart> carts;
    private ArrayList<Integer> clientsID;
    
    private int selectedCartRow = -1;
    public int selectedRow = 0;
    public CreateInvoice() {
        super("Create");
        initComponents();
        language_transform();
        ComponentListener listener = new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                Component c = (Component) evt.getSource();
                setupData();
            }
        };
        this.addComponentListener(listener);
    }
    public void setupData() {
        carts = new ArrayList<Cart>();
        addedItemIDs = new ArrayList<Integer>();
        addedStocksIDs = new HashMap<Integer, ArrayList<Integer>>();
        availableItemIDs = new ArrayList<Integer>();
        itemsList.removeAllItems();
        setItemList();
        
        clientsID = new ArrayList<Integer>();
        setClientList();
        
        invoicerName.setText(Authentication.getFULLNAME());
        totalCartValue.setText("0");
        cartTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) selectedCartRow = cartTable.getSelectedRow();
            }
            public void mouseClicked(MouseEvent me) {
                selectedCartRow = cartTable.getSelectedRow();
            }
        });
    }
    public void setClientList() {
        HashMap clientsH = this.mainFrame.data.clients;
        clientsID = new ArrayList<Integer>();
        clientList.removeAllItems();
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        invoicerName = new javax.swing.JTextField();
        paymentMethod = new javax.swing.JTextField();
        clientList = new javax.swing.JComboBox<>();
        createInvoice = new javax.swing.JButton();
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
        jLabel5 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewCreate"); // NOI18N
        jLabel1.setText(bundle.getString("CreateInvoice.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("CreateInvoice.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("CreateInvoice.jLabel3.text")); // NOI18N

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

        createInvoice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createInvoice.setText(bundle.getString("CreateInvoice.createInvoice.text")); // NOI18N
        createInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createInvoiceActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText(bundle.getString("CreateInvoice.jLabel4.text")); // NOI18N

        jToolBar2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("CreateInvoice.jToolBar2.border.title"))); // NOI18N
        jToolBar2.setRollover(true);
        jToolBar2.setEnabled(false);

        totalCartValue.setText(bundle.getString("CreateInvoice.totalCartValue.text")); // NOI18N
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
            cartTable.getColumnModel().getColumn(0).setResizable(false);
            cartTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            cartTable.getColumnModel().getColumn(1).setResizable(false);
            cartTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            cartTable.getColumnModel().getColumn(2).setResizable(false);
            cartTable.getColumnModel().getColumn(2).setPreferredWidth(30);
            cartTable.getColumnModel().getColumn(3).setResizable(false);
            cartTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            cartTable.getColumnModel().getColumn(4).setResizable(false);
            cartTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            cartTable.getColumnModel().getColumn(5).setResizable(false);
        }

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("CreateInvoice.jToolBar1.border.title"))); // NOI18N
        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        itemsList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.add(itemsList);

        addItem.setText(bundle.getString("CreateInvoice.addItem.text")); // NOI18N
        addItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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

        removeItem.setText(bundle.getString("CreateInvoice.removeItem.text")); // NOI18N
        removeItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/invoice.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(10, 10, 10)))
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 113, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(createInvoice)
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
    
    
    private void createInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createInvoiceActionPerformed
        
        if (!validating()) {
            getValidationMessage();
            return;
        }

        // create invoice
        Invoice invoice = new Invoice();
        Databank data = this.mainFrame.data;
        cInvoice xInvoice = new cInvoice();

        int lastID = data.lastInvoice.id;
        if (lastID == -1) lastID = 0;
        int invoiceID = lastID + 1;
        xInvoice.setId(invoiceID);
        xInvoice.setClient_id(clientsID.get(clientList.getSelectedIndex()));
        xInvoice.setUser_id(Authentication.getID());
        xInvoice.setPayment_method(paymentMethod.getText());
        xInvoice.setDate_create(Model.getTimeGlobal());

        InvoiceModel invoice_mdl = new InvoiceModel(); 
        invoice_mdl.create(xInvoice);
        invoice.setData(xInvoice);
        // create carts
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        CartModel cart_mdl = new CartModel();

        int rowsCount = model.getRowCount();
        int colsCount = model.getColumnCount();
        ArrayList<Integer> itemQty = new ArrayList<Integer>();
        for (int i = 0; i < rowsCount; i++) {
            Cart cart = new Cart();
            cCart xCart = new cCart();
            xCart.setInvoice_id(invoiceID);
            xCart.setProduct_id(addedItemIDs.get(i));
            xCart.setQuantity((int)model.getValueAt(i, 2));
            itemQty.add(xCart.getQuantity());
            xCart.setUnit_price((float)model.getValueAt(i, 3));

            cart_mdl.create(xCart);
            cart.setData(xCart);
            invoice.carts.add(cart);
        }
        
        // UPDATE STOCK OUT
        Iterator cursor = addedStocksIDs.entrySet().iterator();
        while (cursor.hasNext()) {
            HashMap dstocks = this.mainFrame.data.stocks;
            HashMap dproducts = this.mainFrame.data.products;
            
            Map.Entry pair = (Map.Entry) cursor.next();
            int qtySum = 0;
            int stockID = (int) pair.getKey();
            ArrayList<Integer> itemsIDs = (ArrayList<Integer>) pair.getValue();
            for (Integer itemsID : itemsIDs) {
                int idx = addedItemIDs.indexOf(itemsID);
                qtySum += itemQty.get(idx);
            }
            Stock crrStock = (Stock) dstocks.get(stockID);
            cStock xcrrStock = (cStock) crrStock.getData();
            xcrrStock.setStockOut(xcrrStock.getStockOut() - qtySum);
            crrStock.setData(xcrrStock);
            dstocks.replace(stockID, crrStock);
            // update stock in stockModel
            StockModel stk_mdl = new StockModel();
            stk_mdl.exportStock(stockID, xcrrStock.getRemaining(), xcrrStock.getStockOut(), null);
            // update stock in each product
             for (Integer itemsID : itemsIDs) {
                Product crrPrd = (Product) dproducts.get(itemsID);
                crrPrd.setStock(crrStock);
                dproducts.replace(itemsID, crrPrd);
            }
        }
        
        this.mainFrame.productController.load_ram_data();
        this.mainFrame.stockController.load_ram_data();
        setVisible(false);
        reset();
        this.mainFrame.invoiceController.append(invoice);
    }//GEN-LAST:event_createInvoiceActionPerformed
    public void reset() {
        clientList.setSelectedIndex(0);
        if (itemsList.getItemCount() != 0) itemsList.setSelectedIndex(0);
        paymentMethod.setText("");
        totalCartValue.setText("0");
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setRowCount(0);
        addedItemIDs = new ArrayList<Integer>();
        availableItemIDs = new ArrayList<Integer>();
        setItemList();
    }
    private int addItemCount = 1;
    private void addItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemActionPerformed
        int selectedItem = itemsList.getSelectedIndex();
        if (selectedItem == -1) return;
        int selectedProductID = availableItemIDs.get(selectedItem);
        HashMap productsH = this.mainFrame.data.products;
        HashMap stocksH = this.mainFrame.data.stocks;
        Product product = (Product)productsH.get(selectedProductID);
        cProduct selectedProduct = (cProduct)(product).getData();
        cStock selectedStock = (cStock) product.getStock().getData();
      
        availableItemIDs.remove(selectedItem);
        addedItemIDs.add(selectedProductID);
        itemsList.removeItemAt(selectedItem);
        
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        int newRemaining = 0;
        
        int stockID = selectedStock.getId();
        int sumQty = 1;
        ArrayList<Integer> stockItemsIDs = new ArrayList<Integer>();
        ArrayList<Integer> similarStockItems = new ArrayList<Integer>();
        if (addedStocksIDs.containsKey(stockID)) {
            stockItemsIDs = addedStocksIDs.get(stockID);
            
            for (int itemSID : stockItemsIDs) {
                for (int i = 0; i < addedItemIDs.size(); i++) {
                    if (addedItemIDs.get(i) == itemSID) {
                        sumQty += (int) model.getValueAt(i, 2);
                        similarStockItems.add(i);
                        break;
                    }
                }
            }
            newRemaining = selectedStock.getStockOut() - sumQty;
            stockItemsIDs.add(selectedProductID);
            addedStocksIDs.replace(stockID, stockItemsIDs);
        } else {
            stockItemsIDs.add(selectedProductID);
            addedStocksIDs.put(stockID, stockItemsIDs);
            newRemaining = selectedStock.getStockOut() - 1;
        }
        
        for (Integer rowIndex : similarStockItems) {
            model.setValueAt(newRemaining, rowIndex, 5);
        }
        
        model.addRow(new Object[]{addItemCount++, selectedProduct.getName(), 1, 
            selectedProduct.getPrice(), selectedProduct.getPrice(), newRemaining});
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
        Product selectedPrd = (Product) productsH.get(selectedProductID);
        cProduct selectedProduct = (cProduct)selectedPrd.getData();

        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        int qty = (int) model.getValueAt(idx, 2);
        Stock crrStock = selectedPrd.getStock();
        float currentTotal = Float.parseFloat(totalCartValue.getText());
        currentTotal -= (float)model.getValueAt(idx, 4);
        totalCartValue.setText("" + currentTotal);
        
        int rowCount = model.getRowCount();
        int newRemaining = (int) model.getValueAt(idx, 5) + qty;
        for (int i = 0; i < rowCount; i++) {
            if (i != idx) {
                model.setValueAt(newRemaining, i, 5);
            }
        }
        
        itemsList.addItem(selectedProduct.getName());
        model.removeRow(idx);
        addItemCount--;
        
        cStock selectedStock = (cStock) selectedPrd.getStock().getData();
        stockGC(selectedProduct.getId(), selectedStock.getId());
    }//GEN-LAST:event_removeItemActionPerformed
    public void stockGC(int prdID, int stkID) {
        ArrayList<Integer> stockItemsIDs = addedStocksIDs.get(stkID);
        if (stockItemsIDs != null) {
            for (int i = 0; i < stockItemsIDs.size(); i++) {
                if (stockItemsIDs.get(i) == prdID) {
                    stockItemsIDs.remove(i);
                    addedStocksIDs.replace(stkID, stockItemsIDs);
                    break;
                }
            }
        }
        // update all remaining of common stock item
        
    }
    private void cartTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cartTablePropertyChange
        try {
            DefaultTableModel model = (DefaultTableModel)cartTable.getModel();
            int selectedRow = cartTable.getSelectedRow();
            int columns = cartTable.getColumnCount();
            int rows = cartTable.getRowCount();
            
            int qty = (int) model.getValueAt(selectedRow, 2);
            
            // SET STOCK OUT
            int productID = addedItemIDs.get(selectedRow);
            Product product = (Product)this.mainFrame.data.products.get(productID);
            cStock xStock = (cStock) product.getStock().getData();
            
            // CURRENT REMAINING = SUM(ALL ITEMS OF SAME STOCK)
            int sumQty = 0;
            ArrayList<Integer> stockItemsIDs = product.getStock().productsIDs;
            for (int j = 0; j < addedItemIDs.size(); j++) {
                int cPrdID = addedItemIDs.get(j);
                if (j != selectedRow && stockItemsIDs.contains(cPrdID)) {
                    int itemQty = (int)model.getValueAt(j, 2);
                    sumQty += itemQty;
                }
            }
            
            qty = qty > xStock.getStockOut() - sumQty ? xStock.getStockOut() - sumQty : qty;
            int newRemaining = xStock.getStockOut() - qty - sumQty;
            
            model.setValueAt(qty, selectedRow, 2);
            model.setValueAt(newRemaining, selectedRow, 5);
            // UPDATE ALL CART ROWS WITH SAME STOCK
            for (int i = 0; i < addedItemIDs.size(); i++) {
                if (i != selectedRow && stockItemsIDs.contains(productID)) {
                    model.setValueAt(newRemaining, i, 5);
                }
            }
            
            // SET TOTAL PRICE
            float unitPrice = (float) model.getValueAt(selectedRow, 3);
            float totalPrice = unitPrice * qty;
            model.setValueAt(totalPrice, selectedRow, 4);
            
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
    private javax.swing.JButton createInvoice;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JTextField invoicerName;
    private javax.swing.JComboBox<String> itemsList;
    private javax.swing.JDesktopPane jDesktopPane1;
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
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean validating() {
        return (Validation.v_require(paymentMethod.getText()) &&
            Validation.v_require(paymentMethod.getText()) &&
            (this.addedItemIDs.size() > 0) &&
            (clientList.getSelectedIndex() != -1)
        );
    }

    @Override
    public void language_transform() {
        addItem.setText(bundle.get("CreateInvoice.addItem.text"));
        jToolBar2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.get("CreateInvoice.jToolBar2.border.title")));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.get("CreateInvoice.jToolBar1.border.title")));
        jLabel3.setText(bundle.get("CreateInvoice.jLabel3.text"));
        jLabel2.setText(bundle.get("CreateInvoice.jLabel2.text"));
        jLabel1.setText(bundle.get("CreateInvoice.jLabel1.text"));
        totalCartValue.setText(bundle.get("CreateInvoice.totalCartValue.text"));
        jLabel4.setText(bundle.get("CreateInvoice.jLabel4.text"));
        removeItem.setText(bundle.get("CreateInvoice.removeItem.text"));
        createInvoice.setText(bundle.get("CreateInvoice.createInvoice.text"));
        for (int i = 0; i < 6; i++) {
            set_title_table(cartTable, i, bundle.get("CreateInvoice.cartTable.columnModel.title" + i));
        }
    }
}
