package edu.byu.cs.tweeter.shared.model.service.request;

public class AddSaleItemRequest {

    private String SaleID;
    private String Title;
    private String Description;
    private float Price;

    public AddSaleItemRequest(){}

    public AddSaleItemRequest(String saleID, String title, String description, float price) {
        SaleID = saleID;
        Title = title;
        Description = description;
        Price = price;
    }

    public String getSaleID() {
        return SaleID;
    }

    public void setSaleID(String saleID) {
        SaleID = saleID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }
}