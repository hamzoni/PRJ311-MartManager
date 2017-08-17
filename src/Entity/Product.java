
package Entity;

import Cache.cProduct;

public class Product extends Entity {
    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
    
}
