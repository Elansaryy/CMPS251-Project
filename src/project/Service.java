package project;

import java.io.Serializable;
/**
 * this is the code that contains most of the logic
 *
 * @author all
 * @version 1.0
 * 7-5-2023
 *
 */


public class Service implements Serializable {
    private String title;
    private int ID;
    private int maxSlots;
    private int pricePerSlot;
    public Service(){}
    public Service(String title, int ID, int maxSlots, int pricePerSlot) {
        this.setTitle(title);
        this.setID(ID);
        this.setMaxSlots(maxSlots);
        this.setPricePerSlot(pricePerSlot);
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getMaxSlots() {
        return maxSlots;
    }
    public void setMaxSlots(int maxSlots) {
        if (maxSlots>0)
            this.maxSlots = maxSlots;
    }
    public int getPricePerSlot() {
        return pricePerSlot;
    }
    public void setPricePerSlot(int pricePerSlot) {
        if (pricePerSlot>0)
            this.pricePerSlot = pricePerSlot;
    }
    @Override
    public String toString() {
        return String.format("%-15s%-15d%-17d%d", getTitle(), getID(), getMaxSlots(), getPricePerSlot());
    }
}




