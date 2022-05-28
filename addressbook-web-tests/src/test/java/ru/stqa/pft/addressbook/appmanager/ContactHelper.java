package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;


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

        public void selectContact (int index) {
            wd.findElements(By.name("selected[]")).get(index).click();
        }

        public void closeAlert () {
            wd.switchTo().alert().accept();
        }

        public void deleteContact () {
            click(By.xpath("//input[@value='Delete']"));
        }

        public void openContactEditMode (int i) {
            wd.findElements(By.xpath("//img[@alt='Edit']")).get(i).click();
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

        System.out.println("Вызван метод getContactList()");

        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));


        for (WebElement e : elements) {
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("id"));

            String surname = e.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String name = e.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String address = e.findElement(By.cssSelector("td:nth-of-type(4)")).getText();
            String email = e.findElement(By.cssSelector("td:nth-of-type(5)")).getText();
            String number = e.findElement(By.cssSelector("td:nth-of-type(6)")).getText();


            ContactData contact = new ContactData(id, name, surname, address, number, email);
            contacts.add(contact);

            System.out.println(contact.toString());
        }
        return contacts;
    }

    }




