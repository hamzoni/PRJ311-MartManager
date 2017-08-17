
package Middleware;

import View.Dialog;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class GUITools {
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    public static void set_key_submit(Component dlg2, JButton submitButton, Class className) {
        JFrame JF = null;
        JDialog DL = null;
        if (className == JFrame.class) JF = (JFrame) dlg2;
        if (className == JDialog.class) DL = (JDialog) dlg2;
        Component[] components = className == JFrame.class ? 
                JF.getContentPane().getComponents() :
                DL.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JTextField || component instanceof JTextArea) {
                JTextComponent specificObject = (JTextComponent) component;
                specificObject.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            submitButton.doClick();
                        }
                    }
                });
            }
        }
    }
}
