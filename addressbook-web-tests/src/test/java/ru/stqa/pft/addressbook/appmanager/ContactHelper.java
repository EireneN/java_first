package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.*;

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
        type(By.name("address"), contactData.getContactAddress());
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

        public void selectContactById (int id) {

        wd.findElement(By.cssSelector("input[value= '"+ id +"' ]")).click();
    }

        public void closeAlert () {
            wd.switchTo().alert().accept();
        }

        public void deleteContact () {
            click(By.xpath("//input[@value='Delete']"));
        }

        public void openContactEditMode (int id) {
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

    public Set<ContactData> allContact() {

        System.out.println("Вызван метод allContact()");

        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));


        for (WebElement e : elements) {
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("id"));

            String surname = e.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String name = e.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String address = e.findElement(By.cssSelector("td:nth-of-type(4)")).getText();
            String email = e.findElement(By.cssSelector("td:nth-of-type(5)")).getText();
            String number = e.findElement(By.cssSelector("td:nth-of-type(6)")).getText();


            ContactData contact = new ContactData().setId(id).withContactName(name).withContactSurname(surname).
                    withContactAddress(address).withContactMobNumber(number).withContactEmail(email);
            contacts.add(contact);

            System.out.println(contact.toString());
        }
        return contacts;
    }


    public void modifyContact(ContactData contact) {
        openContactEditMode (contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteContact();
        closeAlert();
    }

}




