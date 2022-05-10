package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper (WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getContactName());
        type(By.name("lastname"), contactData.getContactSurname());
        type(By.name("mobile"), contactData.getContactMobNumber());
        type(By.name("email"), contactData.getContactEmail());
    }
    public void SendForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContact() {
        click(By.id("13"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void deleteContact(){
        click (By.xpath("//input[@value='Delete']"));
    }

    public void openContactEditMode (){
       click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification (){
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

}



