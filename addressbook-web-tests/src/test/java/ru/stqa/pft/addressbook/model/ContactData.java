package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String address;
    @Expose
    private String mobilePhone;
    @Expose
    private String email;
    @Expose
    private String homePhone;
    @Expose
    private String workPhone;
    @Expose
    private String phoneTwo;
    private String allPhones;
    @Expose
    private String emailTwo;
    @Expose
    private String emailThree;
    private String allEmails;
    private File photo;



    // with - это setter

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }
    public ContactData withPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmailTwo(String emailTwo) {
        this.emailTwo = emailTwo;
        return this;
    }

    public ContactData withEmailThree(String emailThree) {
        this.emailThree = emailThree;
        return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {return allPhones;}

    public String getEmailThree() {
            return emailThree;
        }

    public String getEmailTwo() {
            return emailTwo;
        }

    public String getAllEmails() {
        return allEmails;
    }

    public File getPhoto() {
        return photo;
    }

    public String getPhoneTwo() {return phoneTwo;
    }


        @Override
        public boolean equals (Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ContactData that = (ContactData) o;
            return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
        }

        @Override
        public int hashCode () {
            return Objects.hash(id, firstname, lastname);
        }

        @Override
        public String toString () {
            return "ContactData{" +
                    "ID = '" + id + "', " +
                    "NAME = '" + firstname + "', " +
                    "SURNAME = '" + lastname + "', " +
                    "ADDRESS = '" + address + "', " +
                    "NUMBER = '" + mobilePhone + "', " +
                    "EMAIL = '" + email + "\'" +
                    '}';
        }
    }

