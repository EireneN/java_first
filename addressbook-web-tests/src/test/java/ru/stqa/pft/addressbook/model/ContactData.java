package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String contactName;
    private final String contactSurname;
    private final String contactMobNumber;
    private final String contactEmail;
    private String group;

    public ContactData(String contactName, String contactSurname, String contactMobNumber, String contactEmail, String group) {
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactMobNumber = contactMobNumber;
        this.contactEmail = contactEmail;
        this.group = group;
        }


    public String getContactName() {
        return contactName;
    }

    public String getContactSurname() {
        return contactSurname;
    }

    public String getContactMobNumber() {
        return contactMobNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getGroup() {
        return group;
    }
}
