package edu.byu.cs.tweeter.shared.model.domain;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ContactInfo {
    private int contactInfoID;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String phone;

    private int hash;

    @SuppressWarnings("NewApi")
    public ContactInfo(int contactInfoID, String firstName, String lastName, ZonedDateTime birthDate, String email, String phone) {
        this.contactInfoID = contactInfoID;
        this.firstName = firstName;
        this.lastName = lastName;
        if (birthDate == null) {
            this.birthDate = null;
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
            this.birthDate = birthDate.format(formatter);
        }
        this.email = email;
        this.phone = phone;
    }

    public int getContactInfoID() {
        return contactInfoID;
    }

    public void setContactInfoID(int contactInfoID) {
        this.contactInfoID = contactInfoID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "contactInfoID=" + contactInfoID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo status = (ContactInfo) o;
        return this.getHash() == status.getHash();
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactInfoID);
    }

    public int getHash() {
        return hash;
    }
}
