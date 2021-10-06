package edu.byu.cs.tweeter.shared.model.service.response;

import java.util.List;
import java.util.Objects;

import edu.byu.cs.tweeter.shared.model.domain.Status;

public class StatusListResponse extends PagedResponse {
    List<Status> statusList;

    public StatusListResponse() {}
    /**
     * Creates an instance with a null message and a statusList
     *
     * @param statusList the returned list of statuses
     * @param hasMorePages whether there are more pages to load
     */
    public StatusListResponse(List<Status> statusList, boolean hasMorePages) {
        super(true, hasMorePages);
        this.statusList = statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    /**
     * Creates an instance with a failure message and a statusList
     *
     * @param message the reason for failure
     */
    public StatusListResponse(String message) {
        super(false, message, false);
    }

    /**
     * Returns the list of statuses
     *
     * @return the statusList
     */
    public List<Status> getStatusList() {
        return statusList;
    }

    @Override
    public boolean equals(Object param) {
        if (this == param) {
            return true;
        }

        if (param == null || getClass() != param.getClass()) {
            return false;
        }

        StatusListResponse that = (StatusListResponse) param;

        return (Objects.equals(statusList, that.statusList) &&
                Objects.equals(this.getMessage(), that.getMessage()) &&
                this.isSuccess() == that.isSuccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusList);
    }

    @Override
    public String toString() {
        return "StatusListResponse{" +
                "statusList=" + statusList +
                '}';
    }

}
