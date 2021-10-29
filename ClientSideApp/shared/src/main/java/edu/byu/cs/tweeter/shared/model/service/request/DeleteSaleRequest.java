package edu.byu.cs.tweeter.shared.model.service.request;

public class DeleteSaleRequest {
    private string SaleID;

    public DeleteSaleRequest(){}

    public DeleteSaleRequest(string saleID) {
        SaleID = saleID;
    }

    public string getSaleID() {
        return SaleID;
    }

    public void setSaleID(string saleID) {
        SaleID = saleID;
    }
}