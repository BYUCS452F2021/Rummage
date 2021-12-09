package edu.byu.cs.tweeter.shared.model.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a yardsale in the system.
 */
public class Sale implements Serializable {
    private int saleId;
    private String username;
    private String date;
    private int locationID;
    private Location location;
    private String description;
    private String type;

    private int hash;

    public Sale() {}

    @SuppressWarnings("NewApi")
    public Sale(int saleId, String username, ZonedDateTime date, int locationID, String description, String type, Location location) {
        this.saleId = saleId;
        this.username = username;
        if (date == null) {
            this.date = null;
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
            this.date = date.format(formatter);
        }
        this.locationID = locationID;
        this.location = location;
        this.description = description;
        this.type = type;
        this.hash = hashCode();
    }

    public String getDate() {
        return date;
    }

    public int getHash() {
        return hash;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @SuppressWarnings("NewApi")
    public ZonedDateTime generateDate() {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        return ZonedDateTime.parse(date, formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale status = (Sale) o;
        return this.getHash() == status.getHash();
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId, username, date, locationID, description, type);
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", locationID=" + locationID +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", hash=" + hash +
                '}';
    }
}
