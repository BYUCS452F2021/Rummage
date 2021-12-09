package edu.byu.cs.tweeter.shared.model.service.request;

public class DeleteSaleItemRequest {

    private String ItemID;

    public DeleteSaleItemRequest(){}

    public DeleteSaleItemRequest(String itemID) {
        ItemID = itemID;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }
}