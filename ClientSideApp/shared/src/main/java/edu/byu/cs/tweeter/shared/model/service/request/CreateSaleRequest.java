package edu.byu.cs.tweeter.shared.model.service.request;

public class CreateSaleRequest {

    private String Username;
    private String Date;
    private String City;
    private String State;
    private String Address;
    private String Description;
    private String Type;

    public CreateSaleRequest() {}

    public CreateSaleRequest(String username, String date, String city, String state, String address, String description, String type) {
        Username = username;
        Date = date;
        City = city;
        State = state;
        Address = address;
        Description = description;
        Type = type;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}