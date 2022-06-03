package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.util.*;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean isCreation) {

        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmailTwo());
        type(By.name("email3"), contactData.getEmailThree());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        attache(By.name("photo"), contactData.getPhoto());


        if (isCreation) {
            Select new_group = new Select(wd.findElement(By.name("new_group")));
            new_group.getFirstSelectedOption();
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void sendForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContactById(int id) {

        wd.findElement(By.cssSelector("input[value= '" + id + "' ]")).click();
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void openContactEditMode(int id) {
        List<WebElement> elements = wd.findElements(By.name("entry"));
        WebElement element = null;

        for (WebElement e : elements) {
            int id2 = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("id"));
            if (Objects.equals(id, id2)) {
                element = e;
            }
        }
        element.findElement(By.cssSelector("td:nth-of-type(8)")).click();

    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        sendForm();
    }

    public Contacts allContacts() {

        System.out.println("Вызван метод allContact()");

        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));


        for (WebElement e : elements) {
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("id"));

            String surname = e.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String name = e.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String address = e.findElement(By.cssSelector("td:nth-of-type(4)")).getText();
            String email = e.findElement(By.cssSelector("td:nth-of-type(5)")).getText();
            String number = e.findElement(By.cssSelector("td:nth-of-type(6)")).getText();


            ContactData contact = new ContactData().withId(id).withFirstname(name).withLastname(surname).
                    withAddress(address).withMobilePhone(number).withEmail(email);
            contacts.add(contact);

            System.out.println(contact.toString());
        }
        return contacts;
    }


    public void modifyContact(ContactData contact) {
        openContactEditMode(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteContact();
        closeAlert();
    }

    public ContactData contactInfoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
        .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmailTwo(email2)
                .withEmailThree(email3).withAddress(address);


    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        // wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();

    }

    public Set<ContactData> all() {

        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));

            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            contacts.add(new ContactData().withId(id).withFirstname(firstname).withAddress(address).withLastname(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmails));
        }
    return contacts;
    }


}




