
package View;

import Language.Bundle;
import Middleware.Validation;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Dialog extends JDialog {
    public General mainFrame;
    protected Bundle bundle;
    public Dialog(String bundlelc) {
        super(new javax.swing.JFrame(), true);
        bundle = new Bundle();
        bundle.setBundle(Bundle.LANGUAGE, bundlelc);
        setSubEvent(this);
    }
    public void display(boolean confirm) {
        if (confirm) {
            this.setVisible(true);
            this.requestFocus();
        } else {
            this.setVisible(false);
        }
    }
    private void setSubEvent(Dialog dlg) {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dlg.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                Component[] components = dlg.getContentPane().getComponents();
                for (Component component : components) {
                    if (component instanceof JTextField || component instanceof JTextArea) {
                        JTextComponent specificObject = (JTextComponent) component;
                        specificObject.setText("");
                    }
                }
            }
        });
    }
    public void getValidationMessage() {
        HashMap e = Validation.errData;
        if (e.containsKey("v_string")) System.out.println(e.get("v_string"));
        if (e.containsKey("v_require")) System.out.println(e.get("v_require"));
        if (e.containsKey("v_email")) System.out.println(e.get("v_email"));
        if (e.containsKey("v_int")) System.out.println(e.get("v_int"));
        if (e.containsKey("v_float")) System.out.println(e.get("v_float"));
        if (e.containsKey("v_length")) System.out.println(e.get("v_length"));
    }
    public void set_title_table(JTable table, int col_index, String col_name){
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }
}
