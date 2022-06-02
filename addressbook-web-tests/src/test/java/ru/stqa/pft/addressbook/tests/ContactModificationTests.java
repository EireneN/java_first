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

        if (!app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("Artemka").withLastname("Nosov").
                    withAddress("SPb").withMobilePhone("+79523932745").withEmail("artemn@yandex.ru"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactModification() throws Exception {

        Contacts before = app.contact().allContacts();
        ContactData modifiedContact = before.iterator().next();
        int id = modifiedContact.getId();
        ContactData contact = new ContactData()
                .withId(id)
                .withFirstname("Irinka")
                .withLastname("Nosova")
                .withAddress("Sochi")
                .withMobilePhone("+79214789090")
                .withEmail("irinan@yandex.ru");



        app.contact().modifyContact(contact);
        app.goTo().homePage();

        Contacts after = app.contact().allContacts();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

    }

