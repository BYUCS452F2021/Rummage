package edu.byu.cs.tweeter.shared.model.service.response;

import java.util.List;
import java.util.Objects;

import edu.byu.cs.tweeter.shared.model.domain.Sale;

public class FollowedSalesResponse extends PagedResponse {
    List<Sale> saleList;

    public FollowedSalesResponse() {}
    /**
     * Creates an instance with a null message and a yardSaleList
     *
     * @param saleList the returned list of statuses
     * @param hasMorePages whether there are more pages to load
     */
    public FollowedSalesResponse(List<Sale> saleList, boolean hasMorePages) {
        super(true, hasMorePages);
        this.saleList = saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    /**
     * Creates an instance with a failure message and a yardsalelist
     *
     * @param message the reason for failure
     */
    public FollowedSalesResponse(String message) {
        super(false, message, false);
    }

    /**
     * Returns the list of yardSale
     *
     * @return the yardsalelist
     */
    public List<Sale> getSaleList() {
        return saleList;
    }

    @Override
    public boolean equals(Object param) {
        if (this == param) {
            return true;
        }

        if (param == null || getClass() != param.getClass()) {
            return false;
        }

        FollowedSalesResponse that = (FollowedSalesResponse) param;

        return (Objects.equals(saleList, that.saleList) &&
                Objects.equals(this.getMessage(), that.getMessage()) &&
                this.isSuccess() == that.isSuccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleList);
    }

    @Override
    public String toString() {
        return "FollowedSalesResponse{" +
                "yardSaleList=" + saleList +
                '}';
    }

}
