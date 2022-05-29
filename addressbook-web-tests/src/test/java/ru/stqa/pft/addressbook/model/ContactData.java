package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private  String contactName;
    private  String contactSurname;
    private  String contactAddress;
    private  String contactMobNumber;
    private  String contactEmail;

    public ContactData setId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public ContactData withContactSurname(String contactSurname) {
        this.contactSurname = contactSurname;
        return this;
    }

    public ContactData withContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
        return this;
    }

    public ContactData withContactMobNumber(String contactMobNumber) {
        this.contactMobNumber = contactMobNumber;
        return this;
    }

    public ContactData withContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }


    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(contactName, that.contactName) && Objects.equals(contactSurname, that.contactSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactName, contactSurname);
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
