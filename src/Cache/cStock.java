
package Cache;

import java.util.Date;

public class cStock {
    private int id;
    private String name;
    private float cost;
    private int remaining;
    private int stockOut;
    private String producer;
    private String origin;
    private Date lastImportedDate;
    private Date lastExportedDate;
    
    public int getStockOut() {
        return stockOut;
    }

    public void setStockOut(int stockOut) {
        this.stockOut = stockOut;
    }
    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getLastImportedDate() {
        return lastImportedDate;
    }

    public void setLastImportedDate(Date lastImportedDate) {
        this.lastImportedDate = lastImportedDate;
    }

    public Date getLastExportedDate() {
        return lastExportedDate;
    }

    public void setLastExportedDate(Date lastExportedDate) {
        this.lastExportedDate = lastExportedDate;
    }
    
}
