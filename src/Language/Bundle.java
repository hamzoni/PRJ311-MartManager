
package Language;

import java.util.Locale;
import java.util.ResourceBundle;

public class Bundle {
    private String lang;
    private String country;
    private Locale lc;
    private ResourceBundle rs;
    private String bdl;
    public static int LANGUAGE;
    public static final int VI = 0;
    public static final int EN = 1;
    public static final int JP = 2;
    public void setBundle(int language, String bundle) {
        Bundle.LANGUAGE = language;
        switch (language) {
            case 0: lang = "vi"; country = "VN"; break;
            case 1: lang = "en"; country = "US"; break;
            case 2: lang = "ja"; country = "JP"; break;
            default: lang = "vi"; country = "VN"; break;
        }
        this.lc = new Locale(lang, country);
        bdl = "Language/View" + bundle;
        this.rs = Bundle.LANGUAGE == 1 ?
            ResourceBundle.getBundle(bdl) :
            ResourceBundle.getBundle(bdl, lc);
    }
    public void update() {
        switch (Bundle.LANGUAGE) {
            case 0: lang = "vi"; country = "VN"; break;
            case 1: lang = "en"; country = "US"; break;
            case 2: lang = "ja"; country = "JP"; break;
            default: lang = "vi"; country = "VN"; break;
        }
        this.lc = new Locale(lang, country);
        this.rs = Bundle.LANGUAGE == 1 ?
                ResourceBundle.getBundle(bdl) :
                ResourceBundle.getBundle(bdl, lc);
    }
    public String get(String key) {
        return this.rs.getString(key);
    }
}
