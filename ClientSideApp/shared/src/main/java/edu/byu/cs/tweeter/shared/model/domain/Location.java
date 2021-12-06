package edu.byu.cs.tweeter.shared.model.domain;

public class Location {

    private String city;
    private String state;
    private String address;

    public Location(String city, String state, String address) {
        this.city = city;
        this.state = state;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
