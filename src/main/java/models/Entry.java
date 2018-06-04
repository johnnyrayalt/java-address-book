package models;

import java.util.Objects;

public class Entry {
    private String name;
    private String phoneNumber;
    private String mailingAddress;
    private String emailAddress;
    private int id;

    public Entry(String name, String phoneNumber, String mailingAddress, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mailingAddress = mailingAddress;
        this.emailAddress = emailAddress;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return phoneNumber == entry.phoneNumber &&
                Objects.equals(name, entry.name) &&
                Objects.equals(mailingAddress, entry.mailingAddress) &&
                Objects.equals(emailAddress, entry.emailAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, phoneNumber, mailingAddress, emailAddress);
    }
}
