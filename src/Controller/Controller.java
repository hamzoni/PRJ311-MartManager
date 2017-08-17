
package Controller;

import Data.Databank;
import Model.Model;
import View.General;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public abstract class Controller {
    protected JTable viewTable;
    protected General mainFrame;
    protected Databank data;
    protected ArrayList<Integer> loadedIDs;
    protected DefaultTableModel tableModel;
    protected Model dbModel;
    
    public Controller(JTable viewTable, General mainFrame) {
        this.loadedIDs = new ArrayList<Integer>();
        this.tableModel = (DefaultTableModel) viewTable.getModel();
        this.viewTable = viewTable;
        this.mainFrame = mainFrame;
        this.data = mainFrame.data;
    }
}
