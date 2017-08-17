
package Controller;

import Cache.cUser;
import Data.Record;
import Entity.User;
import Model.UserModel;
import View.Create.CreateUser;
import View.General;
import View.Update.UpdateUser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserController extends Controller implements IDataLoader {
    public UpdateUser updateFrame;
    public CreateUser createFrame;
    public UserController(JTable viewTable, General mainFrame) {
        super(viewTable, mainFrame);
        updateFrame = new UpdateUser();
        createFrame = new CreateUser();
    }
    
    @Override
    public void set_table() {
        dbModel = new UserModel();
        load_db_data(dbModel.select());
        set_table_event(this);
    }
    public void set_table_event(UserController ctrl) {
        viewTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) ctrl.update();
            }
        });
    }

    @Override
    public void load_db_data(ResultSet data) {
        tableModel.setRowCount(0);
        loadedIDs = new ArrayList<Integer>();
        try {
            while (data.next()) {
                User user = new User();
                cUser xUser = new cUser();

                String id = data.getString("id");
                String fullname = data.getString("fullname");
                String username = data.getString("username");
                String password = data.getString("password");
                int privilege = Integer.parseInt(data.getString("privilege"));
                String date_create = data.getString("date_create");

                xUser.setId(Integer.parseInt(id));
                xUser.setFullname(fullname);
                xUser.setUsername(username);
                xUser.setPassword(password);
                xUser.setPrivilege(privilege);
                xUser.setDate(date_create);
                loadedIDs.add(xUser.getId());
                date_create = dbModel.dateToHumanString(xUser.getDatePure());
                
                tableModel.addRow(new Object[]{id, fullname, username, User.USER_ROLES[privilege], date_create});
                user.setData(xUser);
                this.data.users.put(xUser.getId(), user);
                this.data.lastUser = new Record(xUser.getId(), user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void load_ram_data() {
        tableModel = (DefaultTableModel) viewTable.getModel();
        tableModel.setRowCount(0);
        loadedIDs = new ArrayList<Integer>();
        HashMap users = this.data.users;
        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            User crrcUser = (User) pair.getValue();
            cUser xCrrUser = (cUser) crrcUser.getData();
            int id = (int) pair.getKey();
            String fullname = xCrrUser.getFullname();
            String username = xCrrUser.getUsername();
            int privilege = xCrrUser.getPrivilege();
            String date_create = dbModel.dateToHumanString(xCrrUser.getDatePure());
            loadedIDs.add(id);
            tableModel.addRow(new Object[]{id, fullname, username, User.USER_ROLES[privilege], date_create});
        }
    }

    @Override
    public void append(Object append_data) {
        User newUser = new User();
        cUser xUser = (cUser) append_data;
        newUser.setData(xUser);
        this.data.users.put(xUser.getId(), newUser);
        this.data.lastUser = new Record(xUser.getId(), newUser);
        
        tableModel.addRow(new Object[]{xUser.getId(), xUser.getFullname(), xUser.getUsername(), User.USER_ROLES[xUser.getPrivilege()], xUser.getDate()});
        loadedIDs.add(xUser.getId());
    }

    @Override
    public void delete() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow == -1) return;
        int userID = loadedIDs.get(selectedRow);
        loadedIDs.remove(selectedRow);
        dbModel.deleteOne(userID);
        this.data.users.remove(userID);
        
        if (viewTable.getRowCount() == 1) {
            try {
                dbModel.resetAutoIncrement(UserModel.TABLE);
                if (this.data.lastUser.id == userID) {
                    this.data.lastUser = new Record(-1, null);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.load_ram_data();
    }

    @Override
    public void update() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow < 0) return;
        int userID = loadedIDs.get(selectedRow);
        
        User user = (User) this.data.users.get(userID);
        cUser xuser = (cUser) user.getData();
        
        updateFrame.setFrameData(xuser);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
    }
}
