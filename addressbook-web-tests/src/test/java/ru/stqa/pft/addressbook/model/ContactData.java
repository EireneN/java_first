package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String contactName;
    private final String contactSurname;
    private final String contactAddress;
    private final String contactMobNumber;
    private final String contactEmail;

    public ContactData(int id, String contactName, String contactSurname, String contactAddress, String contactMobNumber, String contactEmail) {
        this.id = id;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactAddress = contactAddress;
        this.contactMobNumber = contactMobNumber;
        this.contactEmail = contactEmail;
        }
    public ContactData(String contactName, String contactSurname, String contactAddress, String contactMobNumber, String contactEmail) {
        this.id = Integer.MAX_VALUE;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactAddress = contactAddress;
        this.contactMobNumber = contactMobNumber;
        this.contactEmail = contactEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(contactName, that.contactName)
                && Objects.equals(contactSurname, that.contactSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactName, contactSurname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "ID = '" + id + "', " +
                "NAME = '" + contactName + "', " +
                "SURNAME = '" + contactSurname + "', " +
                "ADDRESS = '" + contactAddress + "', " +
                "NUMBER = '" + contactMobNumber + "', " +
                "EMAIL = '" + contactEmail + "\'" +
                '}';
    }


    public String getContactName() {
        return contactName;
    }

    public String getContactSurname() {
        return contactSurname;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public String getContactMobNumber() {
        return contactMobNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

}
