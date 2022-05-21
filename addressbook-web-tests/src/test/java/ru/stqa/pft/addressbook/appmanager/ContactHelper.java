package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean isCreation) {

        type(By.name("firstname"), contactData.getContactName());
        type(By.name("lastname"), contactData.getContactSurname());
        type(By.name("mobile"), contactData.getContactMobNumber());
        type(By.name("email"), contactData.getContactEmail());

            if (isCreation) {
            Select new_group = new Select(wd.findElement(By.name("new_group")));
            new_group.getFirstSelectedOption();
            } else {
             Assert.assertFalse (isElementPresent(By.name("new_group")));
            }
        }


        public void sendForm () {
            click(By.xpath("//div[@id='content']/form/input[21]"));
        }

        public void selectContact (int index) {
            wd.findElements(By.name("selected[]")).get(index).click();
        }

        public void closeAlert () {
            wd.switchTo().alert().accept();
        }

        public void deleteContact () {
            click(By.xpath("//input[@value='Delete']"));
        }

        public void openContactEditMode () {
            click(By.xpath("//img[@alt='Edit']"));
        }

        public void submitContactModification () {
            click(By.xpath("//div[@id='content']/form/input[22]"));
        }

        public boolean isThereAContact () {
            return isElementPresent(By.name("selected[]"));
        }

        public void createContact (ContactData contact){
            initContactCreation();
            fillContactForm(contact,true);
            sendForm();
        }

    public List<ContactData> getContactList() {
        System.out.println("Get contact list");
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement e : elements) {
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("id"));
            String contactInfo = e.getText();
            System.out.println(contactInfo);
            String[] cd = contactInfo.split(" ");
            String surname = cd[0];
            String name = cd[1];
            String email = cd[2];
            String number = cd[3];

            ContactData contact = new ContactData(id, name, surname, number, email);
            contacts.add(contact);
            System.out.println(contact.toString());
        }
        return contacts;
    }

    }




