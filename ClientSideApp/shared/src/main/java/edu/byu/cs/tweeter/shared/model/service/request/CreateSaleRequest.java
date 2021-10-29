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

    public string getState() {
        return State;
    }

    public void setState(string state) {
        State = state;
    }

    public string getAddress() {
        return Address;
    }

    public void setAddress(string address) {
        Address = address;
    }

    public string getDescription() {
        return Description;
    }

    public void setDescription(string description) {
        Description = description;
    }

    public string getType() {
        return Type;
    }

    public void setType(string type) {
        Type = type;
    }
}