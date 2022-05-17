package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

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

        public void selectContact () {
            click(By.name("selected[]"));
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

}



