package App;

import Data.Databank;
import Middleware.Authentication;
import View.General;
import View.Login;
import View.Splash;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    
    public Databank data;
    public static App program;
    private Authentication AUTH;
    
    private Login login;
    private General main;
    
    public static void main(String[] args) throws SQLException, ParseException {
        program = new App();
        program.init();
    }
    public void init() throws SQLException {
        data = new Databank();
        System.out.println(Authentication.MD5("123123"));
        App.LOAD_LOGIN();
    }
    public static void LOAD_CONTENT() {
        Splash splash = new Splash();
        try {
            splash.setVisible(true);
            Authentication auth = new Authentication("admin",Authentication.MD5("123123"));
            program.loadContent();
            splash.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadContent() throws ParseException, SQLException {
        if (Authentication.STATUS) {
            if (main == null) {
                main = new General(data);
            } else {
                main.setVisible(true);
            }
        }
    }
    
    public static void LOAD_LOGIN() {
        Authentication.REFRESH();
        program.loadLogin();
    }
    private void loadLogin() {
        if (main != null) {
            main.setVisible(false);
            main = null;
        }
        if (login == null) {
            login = new Login(AUTH);
        } else {
            login.setVisible(true);
        }
    }
}
