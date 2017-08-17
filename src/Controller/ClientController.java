
package Controller;

import Cache.cClient;
import Data.Record;
import Entity.Client;
import Model.ClientModel;
import View.Create.CreateClient;
import View.General;
import View.Update.UpdateClient;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientController extends Controller implements IDataLoader {
    public UpdateClient updateFrame;
    public CreateClient createFrame;
    public ClientController(JTable viewTable, General mainFrame) {
        super(viewTable, mainFrame);
        updateFrame = new UpdateClient();
        createFrame = new CreateClient();
    }

    @Override
    public void set_table() {
        dbModel = new ClientModel();
        load_db_data(dbModel.select());
        set_table_event(this);
    }
    public void set_table_event(ClientController ctrl) {
        viewTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) ctrl.update();
            }
        });
    }

    @Override
    public void load_db_data(ResultSet data) {
        try {
            tableModel.setRowCount(0);
            while (data.next()) {
                Client client = new Client();
                cClient xClient = new cClient();
                
                String id = data.getString("id");
                String name = data.getString("name");
                String phone = data.getString("phone");
                String address = data.getString("address");
                String date_create = data.getString("date_create");
                
                xClient.setId(Integer.parseInt(id));
                xClient.setName(name);
                xClient.setPhone(phone);
                xClient.setAddress(address);
                xClient.setDateCreate(date_create);
                loadedIDs.add(xClient.getId());
                date_create = dbModel.dateToHumanString(xClient.getDatePure());
                
                tableModel.addRow(new Object[]{id, name, phone, address, date_create});
                client.setData(xClient);
                this.data.clients.put(xClient.getId(), client);
                this.data.lastClient = new Record(xClient.getId(), client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void load_ram_data() {
        tableModel.setRowCount(0);
        HashMap clients = this.data.clients;
        Iterator it = clients.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Client crrcClient = (Client) pair.getValue();
            cClient xCrrClient = (cClient) crrcClient.getData();
            int id = (int) pair.getKey();
            String name = xCrrClient.getName();
            String phone = xCrrClient.getPhone();
            String address = xCrrClient.getAddress();
            String date_create = dbModel.dateToHumanString(xCrrClient.getDatePure());
            tableModel.addRow(new Object[]{id, name, phone, address, date_create});
        }
    }

    @Override
    public void append(Object append_data) {
        Client newClient = new Client();
        cClient xClient = (cClient) append_data;
        newClient.setData(xClient);
        this.data.clients.put(xClient.getId(), newClient);
        this.data.lastClient = new Record(xClient.getId(), newClient);
        
        tableModel.addRow(new Object[]{xClient.getId(), xClient.getName(), xClient.getPhone(), xClient.getAddress(), xClient.getDateCreate()});
        loadedIDs.add(xClient.getId());
    }

    @Override
    public void update() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow < 0) return;
        int clientID = loadedIDs.get(selectedRow);
        
        Client client = (Client) this.data.clients.get(clientID);
        cClient xclient = (cClient) client.getData();
        
        updateFrame.setFrameData(xclient);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
    }

    @Override
    public void delete() {
        int selectedRow = viewTable.getSelectedRow();
        if (selectedRow == -1) return;
        int clientID = loadedIDs.get(selectedRow);
        loadedIDs.remove(selectedRow);
        dbModel.deleteOne(clientID);
        this.data.clients.remove(clientID);
        
        if (viewTable.getRowCount() == 1) {
            try {
                dbModel.resetAutoIncrement(ClientModel.TABLE);
                if (this.data.lastClient.id == clientID) {
                    this.data.lastClient = new Record(-1, null);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.load_ram_data();
    }
}
