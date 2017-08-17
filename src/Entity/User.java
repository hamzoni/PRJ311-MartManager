
package Entity;

import Cache.cUser;
import java.util.ArrayList;

public class User extends Entity {
    public static final int ROLE_ADMIN = 0;
    public static final int ROLE_SELLER = 1;
    public static final int ROLE_INVENTORY_STAFF = 2;
    public static final String[] USER_ROLES = {"Admin", "Seller", "Inventory staff"};
    public static final int N_ROLES = 3;
}
