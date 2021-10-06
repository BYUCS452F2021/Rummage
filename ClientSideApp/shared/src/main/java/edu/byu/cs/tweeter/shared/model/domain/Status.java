package edu.byu.cs.tweeter.shared.model.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a status in the system.
 */
public class Status implements Serializable {
    private String message;
    private User poster;
    private String date;
    private int hash;

    public Status() {}

    @SuppressWarnings("NewApi")
    public Status(String message, User poster, ZonedDateTime date) {
        this.message = message;
        this.poster = poster;
        if (date == null) {
            this.date = null;
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
            this.date = date.format(formatter);
        }
        this.hash = hashCode();
    }

    public String getMessage() {
        return message;
    }

    public User getPoster() {
        return poster;
    }

    public String getDate() {
        return date;
    }

    public int getHash() {
        return hash;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPoster(User poster) {
        this.poster = poster;
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
        Status status = (Status) o;
        return this.getHash() == status.getHash();
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, poster, date);
    }

    @Override
    public String toString() {
        return "Status{" +
                "message='" + message + '\'' +
                ", poster=" + poster +
                ", date='" + date + '\'' +
                ", hash=" + hash +
                '}';
    }
}
