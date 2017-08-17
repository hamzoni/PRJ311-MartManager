
package View.Extra;

import Cache.cStock;
import Entity.Stock;
import Model.StockModel;
import View.Dialog;
import View.Interface.IBundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JComboBox;

public class StockView extends Dialog {
    protected ArrayList<Integer> stocksIDs;
    protected StockModel stock_mdl;
    protected cStock xStock;
    public StockView() {
        super("Stock");
        this.stock_mdl = new StockModel();
        stocksIDs = new ArrayList<Integer>();
    }
    protected int frameSetting(Object data, JComboBox<String> stocksList) {
        int stockIndex = (int) data;
        int stockID = -1;
        stocksIDs = new ArrayList<Integer>();
        stocksList.removeAllItems();
        HashMap stocks = this.mainFrame.data.stocks;
        Iterator it = stocks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String value = ((cStock)((Stock)pair.getValue()).getData()).getName();
            stocksList.addItem(value);
            stocksIDs.add((int)pair.getKey());
        }
        if (stocksIDs.size() > 0 && stocksIDs.size() > stockIndex && stockIndex != -1) {
            stocksList.setSelectedIndex(stockIndex);
            stockID = stocksIDs.get(stockIndex);
        }
        return stockID;
    }
}
