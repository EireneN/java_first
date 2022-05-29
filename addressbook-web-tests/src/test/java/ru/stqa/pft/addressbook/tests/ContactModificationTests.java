package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public static void ensurePreconditions() {
        app.goTo().homePage();

        if (!app.contactHelper().isThereAContact()) {
            app.contactHelper().createContact(new ContactData().withContactName("Artemka").withContactSurname("Nosov").
                    withContactAddress("SPb").withContactMobNumber("+79523932745").withContactEmail("artemn@yandex.ru"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactModification() throws Exception {

        Contacts before = app.contactHelper().allContact();
        ContactData modifiedContact = before.iterator().next();
        int id = modifiedContact.getId();
        ContactData contact = new ContactData()
                .setId(id)
                .withContactName("Irinka")
                .withContactSurname("Nosova")
                .withContactAddress("Sochi")
                .withContactMobNumber("+79214789090")
                .withContactEmail("irinan@yandex.ru");



        app.contactHelper().modifyContact(contact);
        app.goTo().homePage();

        Contacts after = app.contactHelper().allContact();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

    }

