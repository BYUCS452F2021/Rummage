package edu.byu.cs.tweeter.shared.model.service.request;

public class SQSPostRequest {
    public PostRequest request;
    public String authorFirstName;
    public String authorLastName;
    public String authorImageURL;
    public String dateString;
    public long postDate;

    SQSPostRequest() {

    }

    public SQSPostRequest(PostRequest request, String authorFirstName, String authorLastName, String authorImageURL, String dateString, long postDate) {
        this.request = request;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.authorImageURL = authorImageURL;
        this.dateString = dateString;
        this.postDate = postDate;
    }

    public PostRequest getRequest() {
        return request;
    }

    public void setRequest(PostRequest request) {
        this.request = request;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorImageURL() {
        return authorImageURL;
    }

    public void setAuthorImageURL(String authorImageURL) {
        this.authorImageURL = authorImageURL;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public long getPostDate() {
        return postDate;
    }

    public void setPostDate(long postDate) {
        this.postDate = postDate;
    }
}
