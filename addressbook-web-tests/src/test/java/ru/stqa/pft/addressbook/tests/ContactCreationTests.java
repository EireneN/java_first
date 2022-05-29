package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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

        Contacts before = app.contactHelper().allContact();

        ContactData contact = new ContactData().withContactName("Artemka").withContactSurname("Nosov").
                withContactAddress("SPb").withContactMobNumber("+79523932745").withContactEmail("artemn@yandex.ru");

        app.contactHelper().createContact(contact);
        app.goTo().homePage();

        Contacts after = app.contactHelper().allContact();
        int b = before.size() +1;
        assertThat(after.size(), equalTo(b));

        assertThat(after, equalTo(
                before.withAdded(contact.setId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }



    @Test (enabled = false)
    public void testNewContact3() throws Exception {
        int x = 8;
        for (int i = 0; i < x; i++) {
            testNewContact();
        }
    }
}

