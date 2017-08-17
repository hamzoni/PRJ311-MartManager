
package View;

import Cache.cUser;
import Controller.CategoryController;
import Controller.ClientController;
import Controller.InvoiceController;
import Controller.ProductController;
import Controller.StockController;
import Controller.UserController;
import Data.*;
import Entity.*;
import Language.Bundle;
import Middleware.Authentication;
import Model.Model;
import Model.ProcedureModel;
import Model.UserModel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class General extends JFrame {
    private ArrayList<User> users;
    public Databank data;
    private Bundle bundle;
    
    public UserController userController;
    public CategoryController categoryController;
    public ClientController clientController;
    public StockController stockController;
    public ProductController productController;
    public InvoiceController invoiceController;
    public  ProcedureModel proc_mdl;

    public General(Databank data) throws SQLException, ParseException {
        proc_mdl = new ProcedureModel();
       
        this.data = data;
        initComponents();
        setLocationRelativeTo(null);
        
        bundle = new Bundle();
        bundle.setBundle(Bundle.LANGUAGE, "General");
        
        setMainFrame();
        viewAuthorization();
        setUserAccountData();
        setHomepageData();
        
        clusterLanguageTransform(Bundle.EN);
        this.setVisible(true);
    }
    public void language_transform() { // GENERAL VIEW
        jLabel10.setText(bundle.get("General.jLabel10.text"));
        jButton18.setText(bundle.get("General.jButton18.text"));
        
        jTabbedPane1.setTitleAt(0, bundle.get("General.jPanel_home.TabConstraints.tabTitle"));
        jTabbedPane2.setTitleAt(0, bundle.get("General.jPanel7.TabConstraints.tabTitle"));
        jTabbedPane2.setTitleAt(1, bundle.get("General.jPanel8.TabConstraints.tabTitle"));
        
        if (Authentication.getPRIVILEGE() != User.ROLE_INVENTORY_STAFF) {
            if (Authentication.getPRIVILEGE() == User.ROLE_SELLER) {
                jTabbedPane1.setTitleAt(1, bundle.get("General.jPanel_invoice.TabConstraints.tabTitle"));
                jTabbedPane1.setTitleAt(2, bundle.get("General.jPanel_client.TabConstraints.tabTitle"));
            } else {
                jTabbedPane1.setTitleAt(1, bundle.get("General.jPanel_invoice.TabConstraints.tabTitle"));
                jTabbedPane1.setTitleAt(4, bundle.get("General.jPanel_client.TabConstraints.tabTitle"));
            }
            
            
            deleteClient.setText(bundle.get("General.deleteClient.text"));
            updateClient.setText(bundle.get("General.updateClient.text"));
            createClient.setText(bundle.get("General.createClient.text"));
            
            updateInvoice.setText(bundle.get("General.updateInvoice.text"));
            createInvoice.setText(bundle.get("General.createInvoice.text"));
            
            set_title_table(invoiceTable, 0, bundle.get("General.Invoices.table.ID"));
            set_title_table(invoiceTable, 1, bundle.get("General.Invoices.table.Buyer"));
            set_title_table(invoiceTable, 2, bundle.get("General.Invoices.table.Seller"));
            set_title_table(invoiceTable, 3, bundle.get("General.Invoices.table.Method"));
            set_title_table(invoiceTable, 4, bundle.get("General.Invoices.table.Date"));
            
            set_title_table(cartTable, 0, bundle.get("General.Cart.table.ProductName"));
            set_title_table(cartTable, 1, bundle.get("General.Cart.table.Qty"));
            set_title_table(cartTable, 2, bundle.get("General.Cart.table.UnitPrice"));
            set_title_table(cartTable, 3, bundle.get("General.Cart.table.Total"));

            set_title_table(clientTable, 0, bundle.get("General.Clients.table.ID"));
            set_title_table(clientTable, 1, bundle.get("General.Clients.table.fullName"));
            set_title_table(clientTable, 2, bundle.get("General.Clients.table.Phone"));
            set_title_table(clientTable, 3, bundle.get("General.Clients.table.Address"));
            set_title_table(clientTable, 4, bundle.get("General.Clients.table.DateCreate"));
        }
        
        if (Authentication.getPRIVILEGE() != User.ROLE_SELLER) {
            if (Authentication.getPRIVILEGE() == User.ROLE_INVENTORY_STAFF) {
                jTabbedPane1.setTitleAt(2, bundle.get("General.jPanel_stock.TabConstraints.tabTitle"));
                jTabbedPane1.setTitleAt(1, bundle.get("General.jPanel_product.TabConstraints.tabTitle"));
            } else {
                jTabbedPane1.setTitleAt(3, bundle.get("General.jPanel_stock.TabConstraints.tabTitle"));
                jTabbedPane1.setTitleAt(2, bundle.get("General.jPanel_product.TabConstraints.tabTitle"));
            }
            
            deleteCategory.setText(bundle.get("General.deleteCategory.text"));
            updateCategory.setText(bundle.get("General.updateCategory.text"));
            createCategory.setText(bundle.get("General.createCategory.text"));
            deleteProduct.setText(bundle.get("General.deleteProduct.text"));
            updateProduct.setText(bundle.get("General.updateProduct.text"));
            createProduct.setText(bundle.get("General.createProduct.text"));
            
            destock.setText(bundle.get("General.destock.text"));
            exportStock.setText(bundle.get("General.exportStock.text"));
            importStock.setText(bundle.get("General.importStock.text"));
            deleteStock.setText(bundle.get("General.deleteStock.text"));
            updateStock.setText(bundle.get("General.updateStock.text"));
            createStock.setText(bundle.get("General.createStock.text"));
            
            set_title_table(productTable, 0, bundle.get("General.Product.table.ID"));
            set_title_table(productTable, 1, bundle.get("General.Product.table.Name"));
            set_title_table(productTable, 2, bundle.get("General.Product.table.Price"));
            set_title_table(productTable, 3, bundle.get("General.Product.table.Category"));
            set_title_table(productTable, 4, bundle.get("General.Product.table.SID"));
            set_title_table(productTable, 5, bundle.get("General.Product.table.StockIO"));
            
            set_title_table(categoryTable, 0, bundle.get("General.Category.table.ID"));
            set_title_table(categoryTable, 1, bundle.get("General.Category.table.Name"));
            set_title_table(categoryTable, 2, bundle.get("General.Category.table.NoProduct"));
            
            set_title_table(stockTable, 0, bundle.get("General.Product.table.ID"));
            set_title_table(stockTable, 1, bundle.get("General.Product.table.Name"));
            set_title_table(stockTable, 2, bundle.get("General.Product.table.Price"));
            set_title_table(stockTable, 3, bundle.get("General.Product.table.Category"));
            set_title_table(stockTable, 4, bundle.get("General.Product.table.SID"));
            set_title_table(stockTable, 5, bundle.get("General.Product.table.StockIO"));
        }
        
        if (Authentication.getPRIVILEGE() == User.ROLE_ADMIN) {
            jTabbedPane1.setTitleAt(5, bundle.get("General.jPanel_user.TabConstraints.tabTitle"));
            
            deleteUser.setText(bundle.get("General.deleteUser.text"));
            updateUser.setText(bundle.get("General.updateUser.text"));
            createUser.setText(bundle.get("General.createUser.text"));
            
            set_title_table(userTable, 0, bundle.get("General.Users.table.ID"));
            set_title_table(userTable, 1, bundle.get("General.Users.table.Name"));
            set_title_table(userTable, 2, bundle.get("General.Users.table.Username"));
            set_title_table(userTable, 3, bundle.get("General.Users.table.Privileg"));
            set_title_table(userTable, 4, bundle.get("General.Users.table.DateCreate"));
        }
        
        today_datetime1.setText(bundle.get("General.today_datetime1.text"));
        today_datetime.setText(bundle.get("General.today_datetime.text"));
        welcome_message.setText(bundle.get("General.welcome_message.text"));
        welcomeFullName.setText(Authentication.getFULLNAME());
        jLabel13.setText(bundle.get("General.jLabel13.text"));
        jLabel12.setText(bundle.get("General.jLabel12.text"));
        jLabel11.setText(bundle.get("General.jLabel11.text"));
        jMenu2.setText(bundle.get("General.jMenu2.text"));
        jMenu1.setText(bundle.get("General.jMenu1.text"));
        
        jLabel9.setText(bundle.get("General.jLabel9.text"));
        jLabel6.setText(bundle.get("General.jLabel6.text"));
        jLabel5.setText(bundle.get("General.jLabel5.text"));
        jLabel3.setText(bundle.get("General.jLabel3.text"));
        jLabel24.setText(bundle.get("General.jLabel24.text"));
        Vietnamese.setText(bundle.get("General.Vietnamese.text"));
        English.setText(bundle.get("General.English.text"));
        choose_language.setText(bundle.get("General.choose_language.text"));
        editAccount.setText(bundle.get("General.editAccount.text"));
        jLabel8.setText(bundle.get("General.jLabel8.text"));
        jLabel7.setText(bundle.get("General.jLabel7.text"));
        jLabel4.setText(bundle.get("General.jLabel4.text"));
        jLabel2.setText(bundle.get("General.jLabel2.text"));
        jLabel1.setText(bundle.get("General.jLabel1.text"));
        totalInvoice.setText(bundle.get("General.totalInvoice.text"));
    }
    public void set_title_table(JTable table, int col_index, String col_name){
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }
    public void clusterLanguageTransform(int language) {
        Bundle.LANGUAGE = language;
        bundle.update();
        language_transform();
        if (Authentication.getPRIVILEGE() == User.ROLE_ADMIN) {
            userController.createFrame.bundle.update();
            userController.updateFrame.bundle.update();
            userController.createFrame.language_transform();
            userController.updateFrame.language_transform();
        }
        if (Authentication.getPRIVILEGE() != User.ROLE_SELLER) {
            stockController.createFrame.bundle.update();
            stockController.updateFrame.bundle.update();
            stockController.exportFrame.bundle.update();
            stockController.importFrame.bundle.update();
            stockController.withdrawFrame.bundle.update();
            categoryController.createFrame.bundle.update();
            categoryController.updateFrame.bundle.update();
            productController.createFrame.bundle.update();
            productController.updateFrame.bundle.update();
            
            stockController.createFrame.language_transform();
            stockController.updateFrame.language_transform();
            stockController.exportFrame.language_transform();
            stockController.importFrame.language_transform();
            stockController.withdrawFrame.language_transform();
            
            categoryController.createFrame.language_transform();
            categoryController.updateFrame.language_transform();
            productController.createFrame.language_transform();
            productController.updateFrame.language_transform();
        }
        if (Authentication.getPRIVILEGE() != User.ROLE_INVENTORY_STAFF) {
            clientController.createFrame.bundle.update();
            clientController.updateFrame.bundle.update();
            invoiceController.createFrame.bundle.update();
            invoiceController.updateFrame.bundle.update();
            clientController.createFrame.language_transform();
            clientController.updateFrame.language_transform();
            invoiceController.createFrame.language_transform();
            invoiceController.updateFrame.language_transform();
        }
    }
    public void setUserAccountData() {
        welcomeFullName.setText(Authentication.getUSERNAME());
        accUsername.setText(Authentication.getUSERNAME());
        accFullName.setText(Authentication.getFULLNAME());
        accPassword.setText(Authentication.getPASSWORD());
        
        if (accPrivilege.getItemCount() == 0) {
            int nPrivs = User.N_ROLES;
            for (int i = 0; i < nPrivs; i++) {
                accPrivilege.addItem(User.USER_ROLES[i]);
                if (Authentication.getPRIVILEGE() == i) {
                    accPrivilege.setSelectedIndex(i);
                }
            }       
        }
    }
    public void viewAuthorization() {
        /* Tabbed pane
            0 - BOARD
            1 - INVOICE
            2 - PRODUCT AND CATEGORY
            3 - STOCK
            4 - CLIENT
            5 - USER
        */
        switch (Authentication.getPRIVILEGE()) {
            case User.ROLE_ADMIN:
                break;
            case User.ROLE_INVENTORY_STAFF:
                jTabbedPane1.removeTabAt(5);
                jTabbedPane1.removeTabAt(4);
                jTabbedPane1.removeTabAt(1);
                break;
            case User.ROLE_SELLER:
                jTabbedPane1.removeTabAt(5);
                jTabbedPane1.removeTabAt(3);
                jTabbedPane1.removeTabAt(2);
                break;
        }
    }
    public void setHomepageData() {
        load_data_report();
        // CLOCK
        (new Thread() {
            public void run() {
                while (true) {
                    try {
                        String now = Model.getTimeGlobal2();
                        today_datetime.setText(now);
                        sleep(1000);
                    } catch (Exception e) {}
                    
                }
            }
        }).start();
    }
    public void load_data_report() {
        totalInvoices.setText(proc_mdl.getRecordsCount("Invoice"));
        totalProducts.setText(proc_mdl.getRecordsCount("Product"));
        totalClients.setText(proc_mdl.getRecordsCount("Client"));
        totalUsers.setText(proc_mdl.getRecordsCount("Users"));
        totalCategories.setText(proc_mdl.getRecordsCount("Category"));
        totalStocks.setText(proc_mdl.getRecordsCount("Stock"));
    }
    public void setMainFrame() {
        userController = new UserController(userTable, this);
        userController.set_table();
        if (Authentication.getPRIVILEGE() != User.ROLE_INVENTORY_STAFF) {
            clientController = new ClientController(clientTable, this);
            invoiceController = new InvoiceController(invoiceTable, cartTable, this);
            clientController.set_table();
            invoiceController.set_table();
        }
        if (Authentication.getPRIVILEGE() != User.ROLE_SELLER) {
            stockController = new StockController(stockTable, this);
            productController = new ProductController(productTable, this);
            categoryController = new CategoryController(categoryTable, this);
            stockController.set_table();
            productController.set_table();
            categoryController.set_table();
        }
        if (Authentication.getPRIVILEGE() == User.ROLE_ADMIN) {
            userController.updateFrame.mainFrame = this;
            userController.createFrame.mainFrame = this;
        }
        if (Authentication.getPRIVILEGE() != User.ROLE_INVENTORY_STAFF) {
            clientController.updateFrame.mainFrame = this;
            clientController.createFrame.mainFrame = this;
            invoiceController.createFrame.mainFrame = this;
            invoiceController.updateFrame.mainFrame = this;
        }
        if (Authentication.getPRIVILEGE() != User.ROLE_SELLER) {
            categoryController.updateFrame.mainFrame = this;
            categoryController.createFrame.mainFrame = this;
            productController.updateFrame.mainFrame = this;
            productController.createFrame.mainFrame = this;
            
            stockController.createFrame.mainFrame = this;
            stockController.updateFrame.mainFrame = this;
            stockController.importFrame.mainFrame = this;
            stockController.exportFrame.mainFrame = this;
            stockController.withdrawFrame.mainFrame = this;
        }
    }
    public JLabel getTotalInvoice() {
        return this.totalInvoice;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        language = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel_home = new javax.swing.JPanel();
        welcome_message = new javax.swing.JLabel();
        today_datetime = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        accFullName = new javax.swing.JTextField();
        accUsername = new javax.swing.JTextField();
        accPrivilege = new javax.swing.JComboBox<>();
        editAccount = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        accPassword = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        choose_language = new javax.swing.JLabel();
        English = new javax.swing.JRadioButton();
        Vietnamese = new javax.swing.JRadioButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        Japanese = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        totalInvoices = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        totalCategories = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        totalProducts = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        totalStocks = new javax.swing.JTextField();
        totalClients = new javax.swing.JTextField();
        totalUsers = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        welcomeFullName = new javax.swing.JLabel();
        today_datetime1 = new javax.swing.JLabel();
        jPanel_invoice = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        invoiceTable = new javax.swing.JTable();
        jToolBar6 = new javax.swing.JToolBar();
        createInvoice = new javax.swing.JButton();
        updateInvoice = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel10 = new javax.swing.JLabel();
        totalInvoice = new javax.swing.JLabel();
        jPanel_product = new javax.swing.JPanel();
        productPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jToolBar5 = new javax.swing.JToolBar();
        createProduct = new javax.swing.JButton();
        updateProduct = new javax.swing.JButton();
        deleteProduct = new javax.swing.JButton();
        categoryPanel = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        createCategory = new javax.swing.JButton();
        updateCategory = new javax.swing.JButton();
        deleteCategory = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        categoryTable = new javax.swing.JTable();
        jPanel_stock = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        jToolBar9 = new javax.swing.JToolBar();
        createStock = new javax.swing.JButton();
        updateStock = new javax.swing.JButton();
        deleteStock = new javax.swing.JButton();
        importStock = new javax.swing.JButton();
        exportStock = new javax.swing.JButton();
        destock = new javax.swing.JButton();
        jPanel_client = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        clientTable = new javax.swing.JTable();
        jToolBar7 = new javax.swing.JToolBar();
        createClient = new javax.swing.JButton();
        updateClient = new javax.swing.JButton();
        deleteClient = new javax.swing.JButton();
        jPanel_user = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jToolBar8 = new javax.swing.JToolBar();
        createUser = new javax.swing.JButton();
        updateUser = new javax.swing.JButton();
        deleteUser = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setResizable(false);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        welcome_message.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Language/ViewGeneral"); // NOI18N
        welcome_message.setText(bundle.getString("General.welcome_message.text")); // NOI18N

        today_datetime.setText(bundle.getString("General.today_datetime.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText(bundle.getString("General.jLabel1.text")); // NOI18N

        jLabel2.setText(bundle.getString("General.jLabel2.text")); // NOI18N

        jLabel4.setText(bundle.getString("General.jLabel4.text")); // NOI18N

        jLabel7.setText(bundle.getString("General.jLabel7.text")); // NOI18N

        jLabel8.setText(bundle.getString("General.jLabel8.text")); // NOI18N

        accFullName.setEditable(false);
        accFullName.setEnabled(false);

        accUsername.setEditable(false);
        accUsername.setEnabled(false);

        accPrivilege.setEnabled(false);

        editAccount.setText(bundle.getString("General.editAccount.text")); // NOI18N
        editAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAccountActionPerformed(evt);
            }
        });

        accPassword.setEditable(false);
        accPassword.setEnabled(false);
        accPassword.setMaximumSize(new java.awt.Dimension(2147483647, 185));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(editAccount)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(accUsername)
                                .addComponent(accFullName)
                                .addComponent(accPrivilege, 0, 185, Short.MAX_VALUE)
                                .addComponent(accPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(accFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accPrivilege, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editAccount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        choose_language.setText(bundle.getString("General.choose_language.text")); // NOI18N

        language.add(English);
        English.setSelected(true);
        English.setText(bundle.getString("General.English.text")); // NOI18N
        English.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnglishActionPerformed(evt);
            }
        });

        language.add(Vietnamese);
        Vietnamese.setText(bundle.getString("General.Vietnamese.text")); // NOI18N
        Vietnamese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VietnameseActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText(bundle.getString("General.jLabel24.text")); // NOI18N

        language.add(Japanese);
        Japanese.setText(bundle.getString("General.Japanese.text")); // NOI18N
        Japanese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JapaneseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(choose_language)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(English)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Vietnamese)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Japanese)))
                        .addGap(0, 51, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(choose_language)
                    .addComponent(English)
                    .addComponent(Vietnamese)
                    .addComponent(Japanese))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("General.jLabel3.text")); // NOI18N

        jLabel5.setText(bundle.getString("General.jLabel5.text")); // NOI18N

        totalInvoices.setEditable(false);

        jLabel6.setText(bundle.getString("General.jLabel6.text")); // NOI18N

        totalCategories.setEditable(false);

        jLabel9.setText(bundle.getString("General.jLabel9.text")); // NOI18N

        totalProducts.setEditable(false);

        jLabel11.setText(bundle.getString("General.jLabel11.text")); // NOI18N

        jLabel12.setText(bundle.getString("General.jLabel12.text")); // NOI18N

        jLabel13.setText(bundle.getString("General.jLabel13.text")); // NOI18N

        totalStocks.setEditable(false);

        totalClients.setEditable(false);

        totalUsers.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalInvoices, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalStocks, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalClients, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(totalStocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalClients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(totalUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(totalInvoices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(totalCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(231, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(bundle.getString("General.jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab(bundle.getString("General.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane2)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        welcomeFullName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        welcomeFullName.setText(bundle.getString("General.welcomeFullName.text")); // NOI18N

        today_datetime1.setText(bundle.getString("General.today_datetime1.text")); // NOI18N

        javax.swing.GroupLayout jPanel_homeLayout = new javax.swing.GroupLayout(jPanel_home);
        jPanel_home.setLayout(jPanel_homeLayout);
        jPanel_homeLayout.setHorizontalGroup(
            jPanel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_homeLayout.createSequentialGroup()
                        .addComponent(welcome_message)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(welcomeFullName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(today_datetime1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(today_datetime))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_homeLayout.createSequentialGroup()
                        .addGroup(jPanel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_homeLayout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jSeparator1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel_homeLayout.setVerticalGroup(
            jPanel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(welcome_message)
                    .addComponent(today_datetime)
                    .addComponent(welcomeFullName)
                    .addComponent(today_datetime1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_homeLayout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_homeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        jTabbedPane1.addTab(bundle.getString("General.jPanel_home.TabConstraints.tabTitle"), jPanel_home); // NOI18N

        jPanel3.setInheritsPopupMenu(true);

        jScrollPane4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPane4PropertyChange(evt);
            }
        });

        invoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Buyer", "Seller", "Method", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        invoiceTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                invoiceTablePropertyChange(evt);
            }
        });
        invoiceTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                invoiceTableKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(invoiceTable);
        if (invoiceTable.getColumnModel().getColumnCount() > 0) {
            invoiceTable.getColumnModel().getColumn(0).setResizable(false);
            invoiceTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            invoiceTable.getColumnModel().getColumn(1).setResizable(false);
            invoiceTable.getColumnModel().getColumn(1).setPreferredWidth(60);
            invoiceTable.getColumnModel().getColumn(2).setResizable(false);
            invoiceTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            invoiceTable.getColumnModel().getColumn(3).setResizable(false);
            invoiceTable.getColumnModel().getColumn(3).setPreferredWidth(60);
            invoiceTable.getColumnModel().getColumn(4).setResizable(false);
            invoiceTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        }

        jToolBar6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar6.setRollover(true);
        jToolBar6.setEnabled(false);

        createInvoice.setText(bundle.getString("General.createInvoice.text")); // NOI18N
        createInvoice.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createInvoice.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        createInvoice.setFocusable(false);
        createInvoice.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createInvoice.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createInvoiceActionPerformed(evt);
            }
        });
        jToolBar6.add(createInvoice);

        updateInvoice.setText(bundle.getString("General.updateInvoice.text")); // NOI18N
        updateInvoice.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updateInvoice.setFocusable(false);
        updateInvoice.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateInvoice.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateInvoiceActionPerformed(evt);
            }
        });
        jToolBar6.add(updateInvoice);

        jButton18.setText(bundle.getString("General.jButton18.text")); // NOI18N
        jButton18.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton18.setFocusable(false);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton18);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addComponent(jToolBar6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
        );

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product name", "Qty", "Unit price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(cartTable);
        if (cartTable.getColumnModel().getColumnCount() > 0) {
            cartTable.getColumnModel().getColumn(0).setResizable(false);
            cartTable.getColumnModel().getColumn(0).setPreferredWidth(120);
            cartTable.getColumnModel().getColumn(1).setResizable(false);
            cartTable.getColumnModel().getColumn(1).setPreferredWidth(30);
            cartTable.getColumnModel().getColumn(2).setResizable(false);
            cartTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            cartTable.getColumnModel().getColumn(3).setResizable(false);
            cartTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText(bundle.getString("General.jLabel10.text")); // NOI18N
        jToolBar1.add(jLabel10);

        totalInvoice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        totalInvoice.setText(bundle.getString("General.totalInvoice.text")); // NOI18N
        jToolBar1.add(totalInvoice);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3))
        );

        javax.swing.GroupLayout jPanel_invoiceLayout = new javax.swing.GroupLayout(jPanel_invoice);
        jPanel_invoice.setLayout(jPanel_invoiceLayout);
        jPanel_invoiceLayout.setHorizontalGroup(
            jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_invoiceLayout.setVerticalGroup(
            jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab(bundle.getString("General.jPanel_invoice.TabConstraints.tabTitle"), jPanel_invoice); // NOI18N

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Price", "Category", "SID", "Stock I / O"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(0).setResizable(false);
            productTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            productTable.getColumnModel().getColumn(1).setResizable(false);
            productTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            productTable.getColumnModel().getColumn(2).setResizable(false);
            productTable.getColumnModel().getColumn(2).setPreferredWidth(80);
            productTable.getColumnModel().getColumn(3).setResizable(false);
            productTable.getColumnModel().getColumn(3).setPreferredWidth(60);
            productTable.getColumnModel().getColumn(4).setResizable(false);
            productTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            productTable.getColumnModel().getColumn(5).setResizable(false);
            productTable.getColumnModel().getColumn(5).setPreferredWidth(60);
        }

        jToolBar5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar5.setRollover(true);
        jToolBar5.setEnabled(false);

        createProduct.setText(bundle.getString("General.createProduct.text")); // NOI18N
        createProduct.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        createProduct.setFocusable(false);
        createProduct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createProductActionPerformed(evt);
            }
        });
        jToolBar5.add(createProduct);

        updateProduct.setText(bundle.getString("General.updateProduct.text")); // NOI18N
        updateProduct.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updateProduct.setFocusable(false);
        updateProduct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProductActionPerformed(evt);
            }
        });
        jToolBar5.add(updateProduct);

        deleteProduct.setText(bundle.getString("General.deleteProduct.text")); // NOI18N
        deleteProduct.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteProduct.setFocusable(false);
        deleteProduct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
            }
        });
        jToolBar5.add(deleteProduct);

        javax.swing.GroupLayout productPanelLayout = new javax.swing.GroupLayout(productPanel);
        productPanel.setLayout(productPanelLayout);
        productPanelLayout.setHorizontalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jToolBar5, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)))
        );
        productPanelLayout.setVerticalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5))
        );

        jToolBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar2.setRollover(true);
        jToolBar2.setEnabled(false);

        createCategory.setText(bundle.getString("General.createCategory.text")); // NOI18N
        createCategory.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        createCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCategoryActionPerformed(evt);
            }
        });
        jToolBar2.add(createCategory);

        updateCategory.setText(bundle.getString("General.updateCategory.text")); // NOI18N
        updateCategory.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updateCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCategoryActionPerformed(evt);
            }
        });
        jToolBar2.add(updateCategory);

        deleteCategory.setText(bundle.getString("General.deleteCategory.text")); // NOI18N
        deleteCategory.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCategoryActionPerformed(evt);
            }
        });
        jToolBar2.add(deleteCategory);

        categoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Category name", "No. products"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(categoryTable);
        if (categoryTable.getColumnModel().getColumnCount() > 0) {
            categoryTable.getColumnModel().getColumn(0).setResizable(false);
            categoryTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            categoryTable.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("General.categoryTable.columnModel.title0")); // NOI18N
            categoryTable.getColumnModel().getColumn(1).setResizable(false);
            categoryTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            categoryTable.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("General.categoryTable.columnModel.title1")); // NOI18N
            categoryTable.getColumnModel().getColumn(2).setResizable(false);
            categoryTable.getColumnModel().getColumn(2).setPreferredWidth(30);
            categoryTable.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("General.categoryTable.columnModel.title2")); // NOI18N
        }

        javax.swing.GroupLayout categoryPanelLayout = new javax.swing.GroupLayout(categoryPanel);
        categoryPanel.setLayout(categoryPanelLayout);
        categoryPanelLayout.setHorizontalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoryPanelLayout.createSequentialGroup()
                .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                .addContainerGap())
        );
        categoryPanelLayout.setVerticalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryPanelLayout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_productLayout = new javax.swing.GroupLayout(jPanel_product);
        jPanel_product.setLayout(jPanel_productLayout);
        jPanel_productLayout.setHorizontalGroup(
            jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_productLayout.createSequentialGroup()
                .addComponent(productPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_productLayout.setVerticalGroup(
            jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_productLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_productLayout.createSequentialGroup()
                .addComponent(productPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(bundle.getString("General.jPanel_product.TabConstraints.tabTitle"), jPanel_product); // NOI18N

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Cost", "Origin", "Producer", "Stock In / Out", "Last Import Date", "Last Export Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(stockTable);
        if (stockTable.getColumnModel().getColumnCount() > 0) {
            stockTable.getColumnModel().getColumn(0).setResizable(false);
            stockTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            stockTable.getColumnModel().getColumn(1).setResizable(false);
            stockTable.getColumnModel().getColumn(1).setPreferredWidth(80);
            stockTable.getColumnModel().getColumn(2).setResizable(false);
            stockTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            stockTable.getColumnModel().getColumn(3).setResizable(false);
            stockTable.getColumnModel().getColumn(3).setPreferredWidth(40);
            stockTable.getColumnModel().getColumn(4).setResizable(false);
            stockTable.getColumnModel().getColumn(4).setPreferredWidth(40);
            stockTable.getColumnModel().getColumn(5).setResizable(false);
            stockTable.getColumnModel().getColumn(5).setPreferredWidth(60);
            stockTable.getColumnModel().getColumn(6).setResizable(false);
            stockTable.getColumnModel().getColumn(6).setPreferredWidth(80);
            stockTable.getColumnModel().getColumn(7).setResizable(false);
            stockTable.getColumnModel().getColumn(7).setPreferredWidth(80);
        }

        jToolBar9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar9.setRollover(true);
        jToolBar9.setEnabled(false);

        createStock.setText(bundle.getString("General.createStock.text")); // NOI18N
        createStock.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        createStock.setFocusable(false);
        createStock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createStockActionPerformed(evt);
            }
        });
        jToolBar9.add(createStock);

        updateStock.setText(bundle.getString("General.updateStock.text")); // NOI18N
        updateStock.setFocusable(false);
        updateStock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockActionPerformed(evt);
            }
        });
        jToolBar9.add(updateStock);

        deleteStock.setText(bundle.getString("General.deleteStock.text")); // NOI18N
        deleteStock.setFocusable(false);
        deleteStock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStockActionPerformed(evt);
            }
        });
        jToolBar9.add(deleteStock);

        importStock.setText(bundle.getString("General.importStock.text")); // NOI18N
        importStock.setFocusable(false);
        importStock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        importStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importStockActionPerformed(evt);
            }
        });
        jToolBar9.add(importStock);

        exportStock.setText(bundle.getString("General.exportStock.text")); // NOI18N
        exportStock.setFocusable(false);
        exportStock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exportStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportStockActionPerformed(evt);
            }
        });
        jToolBar9.add(exportStock);

        destock.setText(bundle.getString("General.destock.text")); // NOI18N
        destock.setFocusable(false);
        destock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        destock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        destock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destockActionPerformed(evt);
            }
        });
        jToolBar9.add(destock);

        javax.swing.GroupLayout jPanel_stockLayout = new javax.swing.GroupLayout(jPanel_stock);
        jPanel_stock.setLayout(jPanel_stockLayout);
        jPanel_stockLayout.setHorizontalGroup(
            jPanel_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_stockLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                    .addComponent(jToolBar9, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_stockLayout.setVerticalGroup(
            jPanel_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_stockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(bundle.getString("General.jPanel_stock.TabConstraints.tabTitle"), jPanel_stock); // NOI18N

        clientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Full Name", "Phone", "Address", "Date create"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(clientTable);
        if (clientTable.getColumnModel().getColumnCount() > 0) {
            clientTable.getColumnModel().getColumn(0).setResizable(false);
            clientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            clientTable.getColumnModel().getColumn(1).setResizable(false);
            clientTable.getColumnModel().getColumn(1).setPreferredWidth(30);
            clientTable.getColumnModel().getColumn(2).setResizable(false);
            clientTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            clientTable.getColumnModel().getColumn(3).setResizable(false);
            clientTable.getColumnModel().getColumn(3).setPreferredWidth(60);
            clientTable.getColumnModel().getColumn(4).setResizable(false);
            clientTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        }

        jToolBar7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar7.setRollover(true);
        jToolBar7.setEnabled(false);

        createClient.setText(bundle.getString("General.createClient.text")); // NOI18N
        createClient.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createClient.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        createClient.setFocusable(false);
        createClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createClientActionPerformed(evt);
            }
        });
        jToolBar7.add(createClient);

        updateClient.setText(bundle.getString("General.updateClient.text")); // NOI18N
        updateClient.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updateClient.setFocusable(false);
        updateClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateClientActionPerformed(evt);
            }
        });
        jToolBar7.add(updateClient);

        deleteClient.setText(bundle.getString("General.deleteClient.text")); // NOI18N
        deleteClient.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteClient.setFocusable(false);
        deleteClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteClientActionPerformed(evt);
            }
        });
        jToolBar7.add(deleteClient);

        javax.swing.GroupLayout jPanel_clientLayout = new javax.swing.GroupLayout(jPanel_client);
        jPanel_client.setLayout(jPanel_clientLayout);
        jPanel_clientLayout.setHorizontalGroup(
            jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_clientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                    .addComponent(jToolBar7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_clientLayout.setVerticalGroup(
            jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_clientLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jToolBar7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(bundle.getString("General.jPanel_client.TabConstraints.tabTitle"), jPanel_client); // NOI18N

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Full Name", "Username", "Privilege", "Date create"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setResizable(false);
            userTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            userTable.getColumnModel().getColumn(1).setResizable(false);
            userTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            userTable.getColumnModel().getColumn(2).setResizable(false);
            userTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            userTable.getColumnModel().getColumn(3).setResizable(false);
            userTable.getColumnModel().getColumn(3).setPreferredWidth(30);
            userTable.getColumnModel().getColumn(4).setResizable(false);
            userTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        }

        jToolBar8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar8.setRollover(true);
        jToolBar8.setEnabled(false);

        createUser.setText(bundle.getString("General.createUser.text")); // NOI18N
        createUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        createUser.setFocusable(false);
        createUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserActionPerformed(evt);
            }
        });
        jToolBar8.add(createUser);

        updateUser.setText(bundle.getString("General.updateUser.text")); // NOI18N
        updateUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updateUser.setFocusable(false);
        updateUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserActionPerformed(evt);
            }
        });
        jToolBar8.add(updateUser);

        deleteUser.setText(bundle.getString("General.deleteUser.text")); // NOI18N
        deleteUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteUser.setFocusable(false);
        deleteUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserActionPerformed(evt);
            }
        });
        jToolBar8.add(deleteUser);

        javax.swing.GroupLayout jPanel_userLayout = new javax.swing.GroupLayout(jPanel_user);
        jPanel_user.setLayout(jPanel_userLayout);
        jPanel_userLayout.setHorizontalGroup(
            jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_userLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                    .addComponent(jToolBar8, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_userLayout.setVerticalGroup(
            jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_userLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(bundle.getString("General.jPanel_user.TabConstraints.tabTitle"), jPanel_user); // NOI18N

        jMenu1.setText(bundle.getString("General.jMenu1.text")); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText(bundle.getString("General.jMenuItem1.text")); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(bundle.getString("General.jMenu2.text")); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserActionPerformed
        userController.delete();
    }//GEN-LAST:event_deleteUserActionPerformed

    private void updateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserActionPerformed
        userController.update();
    }//GEN-LAST:event_updateUserActionPerformed

    private void createUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserActionPerformed
        userController.createFrame.display(true);
    }//GEN-LAST:event_createUserActionPerformed

    private void deleteClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteClientActionPerformed
        clientController.delete();
    }//GEN-LAST:event_deleteClientActionPerformed

    private void updateClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateClientActionPerformed
        clientController.update();
    }//GEN-LAST:event_updateClientActionPerformed

    private void createClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createClientActionPerformed
        clientController.createFrame.display(true);
    }//GEN-LAST:event_createClientActionPerformed

    private void destockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destockActionPerformed
        stockController.withdrawStock();
    }//GEN-LAST:event_destockActionPerformed

    private void exportStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportStockActionPerformed
        stockController.exportStock();
    }//GEN-LAST:event_exportStockActionPerformed

    private void importStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importStockActionPerformed
        stockController.importStock();
    }//GEN-LAST:event_importStockActionPerformed

    private void deleteStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStockActionPerformed
        stockController.delete();
    }//GEN-LAST:event_deleteStockActionPerformed

    private void updateStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockActionPerformed
        stockController.update();
    }//GEN-LAST:event_updateStockActionPerformed

    private void createStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createStockActionPerformed
        stockController.createFrame.display(true);
    }//GEN-LAST:event_createStockActionPerformed

    private void deleteCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCategoryActionPerformed
        categoryController.delete();
    }//GEN-LAST:event_deleteCategoryActionPerformed

    private void updateCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCategoryActionPerformed
        categoryController.update();
    }//GEN-LAST:event_updateCategoryActionPerformed

    private void createCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCategoryActionPerformed
        categoryController.createFrame.display(true);
    }//GEN-LAST:event_createCategoryActionPerformed

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductActionPerformed
        productController.delete();
    }//GEN-LAST:event_deleteProductActionPerformed

    private void updateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProductActionPerformed
        productController.update();
    }//GEN-LAST:event_updateProductActionPerformed

    private void createProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProductActionPerformed
        productController.createFrame.display(true);
    }//GEN-LAST:event_createProductActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        invoiceController.delete();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void updateInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateInvoiceActionPerformed
        invoiceController.updateCartInvoice();
    }//GEN-LAST:event_updateInvoiceActionPerformed

    private void createInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createInvoiceActionPerformed
        invoiceController.createFrame.display(true);
    }//GEN-LAST:event_createInvoiceActionPerformed

    private void jScrollPane4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jScrollPane4PropertyChange

    }//GEN-LAST:event_jScrollPane4PropertyChange

    private void invoiceTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_invoiceTableKeyPressed
        invoiceController.updateCartInvoice();
    }//GEN-LAST:event_invoiceTableKeyPressed

    private void invoiceTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_invoiceTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_invoiceTablePropertyChange

    private boolean accountEdit = false;
    private void editAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAccountActionPerformed
        accFullName.setEnabled(!accountEdit);
        accUsername.setEnabled(!accountEdit);
        accPassword.setEnabled(!accountEdit);
        
        
        if (Authentication.getPRIVILEGE() == User.ROLE_ADMIN) {
            accPrivilege.setEnabled(!accountEdit);
        }
        
        accFullName.setEditable(!accountEdit);
        accUsername.setEditable(!accountEdit);
        accPassword.setEditable(!accountEdit);
        if (!accountEdit) {
            // edit section
            accPassword.setText("");
            editAccount.setText(bundle.get("General.saveAccount.text"));
        } else {
            // save section
            UserModel user_model = new UserModel();
            
            HashMap users = this.data.users;
            
            User prevUser = (User) users.get(Authentication.getID());
            cUser prevcUser = (cUser) prevUser.getData();
            
            User newUser = new User();
            cUser nowcUser = new cUser();
            nowcUser.setId(prevcUser.getId());
            nowcUser.setFullname(accFullName.getText());
            nowcUser.setUsername(accUsername.getText());
            nowcUser.setPrivilege(accPrivilege.getSelectedIndex());
            nowcUser.setPassword(new String(accPassword.getPassword()));
            
            try {
                nowcUser.setDate(prevcUser.getDate());
            } catch (ParseException ex) {
                Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
            }
            newUser.setData(nowcUser);
            accPassword.setText(Authentication.MD5(nowcUser.getPassword()));
            users.replace(nowcUser.getId(), newUser);
            try {
                nowcUser.setDate(prevcUser.getDate());
            } catch (ParseException ex) {
                Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
            }
            user_model.setUser(prevcUser);
            user_model.update(nowcUser);
            editAccount.setText(bundle.get("General.editAccount.text"));
        }
        accountEdit = !accountEdit;
        if (Authentication.getPRIVILEGE() == User.ROLE_ADMIN) userController.load_ram_data();
    }//GEN-LAST:event_editAccountActionPerformed

    private void JapaneseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JapaneseActionPerformed
        clusterLanguageTransform(Bundle.JP);
    }//GEN-LAST:event_JapaneseActionPerformed

    private void VietnameseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VietnameseActionPerformed
        clusterLanguageTransform(Bundle.VI);
    }//GEN-LAST:event_VietnameseActionPerformed

    private void EnglishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnglishActionPerformed
        clusterLanguageTransform(Bundle.EN);
    }//GEN-LAST:event_EnglishActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 0) load_data_report();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Authentication.REFRESH();
        App.App.LOAD_LOGIN();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton English;
    private javax.swing.JRadioButton Japanese;
    private javax.swing.JRadioButton Vietnamese;
    private javax.swing.JTextField accFullName;
    private javax.swing.JPasswordField accPassword;
    private javax.swing.JComboBox<String> accPrivilege;
    private javax.swing.JTextField accUsername;
    private javax.swing.JTable cartTable;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JTable categoryTable;
    private javax.swing.JLabel choose_language;
    private javax.swing.JTable clientTable;
    private javax.swing.JButton createCategory;
    private javax.swing.JButton createClient;
    private javax.swing.JButton createInvoice;
    private javax.swing.JButton createProduct;
    private javax.swing.JButton createStock;
    private javax.swing.JButton createUser;
    private javax.swing.JButton deleteCategory;
    private javax.swing.JButton deleteClient;
    private javax.swing.JButton deleteProduct;
    private javax.swing.JButton deleteStock;
    private javax.swing.JButton deleteUser;
    private javax.swing.JButton destock;
    private javax.swing.JButton editAccount;
    private javax.swing.JButton exportStock;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton importStock;
    private javax.swing.JTable invoiceTable;
    private javax.swing.JButton jButton18;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel_client;
    private javax.swing.JPanel jPanel_home;
    private javax.swing.JPanel jPanel_invoice;
    private javax.swing.JPanel jPanel_product;
    private javax.swing.JPanel jPanel_stock;
    private javax.swing.JPanel jPanel_user;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar5;
    private javax.swing.JToolBar jToolBar6;
    private javax.swing.JToolBar jToolBar7;
    private javax.swing.JToolBar jToolBar8;
    private javax.swing.JToolBar jToolBar9;
    private javax.swing.ButtonGroup language;
    private javax.swing.JPanel productPanel;
    private javax.swing.JTable productTable;
    private javax.swing.JTable stockTable;
    private javax.swing.JLabel today_datetime;
    private javax.swing.JLabel today_datetime1;
    private javax.swing.JTextField totalCategories;
    private javax.swing.JTextField totalClients;
    private javax.swing.JLabel totalInvoice;
    private javax.swing.JTextField totalInvoices;
    private javax.swing.JTextField totalProducts;
    private javax.swing.JTextField totalStocks;
    private javax.swing.JTextField totalUsers;
    private javax.swing.JButton updateCategory;
    private javax.swing.JButton updateClient;
    private javax.swing.JButton updateInvoice;
    private javax.swing.JButton updateProduct;
    private javax.swing.JButton updateStock;
    private javax.swing.JButton updateUser;
    private javax.swing.JTable userTable;
    private javax.swing.JLabel welcomeFullName;
    private javax.swing.JLabel welcome_message;
    // End of variables declaration//GEN-END:variables
}
