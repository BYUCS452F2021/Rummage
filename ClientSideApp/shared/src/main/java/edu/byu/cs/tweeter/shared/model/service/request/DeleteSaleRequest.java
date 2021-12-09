package edu.byu.cs.tweeter.shared.model.service.request;

public class DeleteSaleRequest {
    private String SaleID;

    public DeleteSaleRequest(){}

    public DeleteSaleRequest(String saleID) {
        SaleID = saleID;
    }

    public String getSaleID() {
        return SaleID;
    }

    public void setSaleID(String saleID) {
        SaleID = saleID;
    }
}