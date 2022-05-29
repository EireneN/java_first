package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


public class ContactCreationTests extends TestBase{

    @BeforeMethod
    public static void ensurePreconditions() {
        app.goTo().groupPage();
        if(! app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName ("test"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testNewContact() throws Exception {

        Set<ContactData> before = app.contactHelper().allContact();

        ContactData contact = new ContactData().withContactName("Artemka").withContactSurname("Nosov").
                withContactAddress("SPb").withContactMobNumber("+79523932745").withContactEmail("artemn@yandex.ru");

        app.contactHelper().createContact(contact);
        app.goTo().homePage();

        Set<ContactData> after = app.contactHelper().allContact();
        int b = before.size() +1;
        Assert.assertEquals(after.size(), b);

        contact.setId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }



    @Test (enabled = false)
    public void testNewContact3() throws Exception {
        int x = 8;
        for (int i = 0; i < x; i++) {
            testNewContact();
        }
    }
}

