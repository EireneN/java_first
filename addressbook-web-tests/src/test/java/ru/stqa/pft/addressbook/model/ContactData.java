package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")

public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose
    @Column(name = "phone2")
    @Type(type = "text")
    private String phoneTwo;

    @Transient
    private String allPhones;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String emailTwo;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String emailThree;

    @Transient
    private String allEmails;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


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
        this.photo = photo.getPath();
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

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmailThree() {
        return emailThree;
    }

    public String getEmailTwo() {
        return emailTwo;
    }

    public String getAllEmails() {
        return allEmails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id
                && Objects.equals(firstname, that.firstname)
                && Objects.equals(lastname, that.lastname)
                && Objects.equals(address, that.address)
                && Objects.equals(mobilePhone, that.mobilePhone)
                && Objects.equals(email, that.email)
                && Objects.equals(homePhone, that.homePhone)
                && Objects.equals(workPhone, that.workPhone)
                && Objects.equals(phoneTwo, that.phoneTwo)
                && Objects.equals(allPhones, that.allPhones)
                && Objects.equals(emailTwo, that.emailTwo)
                && Objects.equals(emailThree, that.emailThree)
                && Objects.equals(allEmails, that.allEmails)
                && Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address, mobilePhone, email, homePhone, workPhone,
                phoneTwo, allPhones, emailTwo, emailThree, allEmails, groups);
    }

    public File getPhoto() {
        return new File(photo);
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }


    public Groups getGroups() {
        return new Groups(groups);
    }

    public void setGroups(Groups groups) {
        this.groups = groups ;
    }

    public void withGroups(Set<GroupData> groups) {
        this.groups = groups ;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "ID = '" + id + "', " +
                "NAME = '" + firstname + "', " +
                "SURNAME = '" + lastname + "', " +
                "ADDRESS = '" + address + "', " +
                "NUMBER = '" + mobilePhone + "', " +
                "EMAIL = '" + email + "', " +
                "homePhone = '" + homePhone + "', " +
                "workPhone = '" + workPhone + "', " +
                "phoneTwo = '" + phoneTwo + "', " +
                "emailTwo = '" + emailTwo + "', " +
                "allPhones = '" + allPhones + "', " +
                "emailThree = '" + emailThree + "', " +
                "allEmails = '" + allEmails + "', " +
                "groups = '" + groups + "', " +
                '}';
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

    public ContactData copy() {
        ContactData copy = new ContactData();
        copy
                .withId(id)
                .withFirstname(firstname)
                .withLastname(lastname)
                .withAddress(address)
                .withMobilePhone(mobilePhone)
                .withEmail(email)
                .withHomePhone(homePhone)
                .withWorkPhone(workPhone)
                .withPhoneTwo(phoneTwo)
                .withEmailTwo(emailTwo)
                .withEmailThree(emailThree)
                .withAllPhones(allPhones)
                .withAllEmails(allEmails)
                .withGroups(groups);
        return copy;
    }

}

