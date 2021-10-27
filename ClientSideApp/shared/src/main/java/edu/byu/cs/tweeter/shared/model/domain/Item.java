package edu.byu.cs.tweeter.shared.model.domain;

import java.io.Serializable;

public class Item implements Serializable {
    private int itemID;
    private int saleID;
    private String title;
    private String Description;
    private String price;

    public Item(int itemID, int saleID, String title, String description, String price) {
        this.itemID = itemID;
        this.saleID = saleID;
        this.title = title;
        Description = description;
        this.price = price;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", saleID=" + saleID +
                ", title='" + title + '\'' +
                ", Description='" + Description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item status = (Item) o;
        return this.getItemID() == status.getItemID();
    }
}
