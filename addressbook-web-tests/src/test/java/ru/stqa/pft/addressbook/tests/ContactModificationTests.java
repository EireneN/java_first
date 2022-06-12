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
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData().withFirstname("Artemka").withLastname("Nosov")
                    .withMobilePhone("+79523932745").withHomePhone("599-03-84").withWorkPhone("8 (812) 678 98 98").withPhoneTwo("+79213450625")
                    .withEmail("artemn@mail.ru").withEmailTwo("art@mail.ru").withEmailThree("nosov@mail.ru")
                    .withAddress("СПб, ул. Брянцева, д.67 к.2, кв.122"));
        } app.goTo().homePage();
    }

    @Test
    public void testContactModification() throws Exception {

        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        int id = modifiedContact.getId();
        ContactData contact = new ContactData()
                .withId(id)
                .withFirstname("Irinka")
                .withLastname("Nosova")
                .withAddress("Sochi")
                .withMobilePhone("+79214789090")
                .withEmail("irinan@yandex.ru")
                .withHomePhone("599-03-84")
                .withWorkPhone("8 (812) 678 98 98")
                .withPhoneTwo("+79213450625")
                .withEmail("artemn@mail.ru")
                .withEmailTwo("art@mail.ru")
                .withEmailThree("nosov@mail.ru")
                .withAddress("СПб, ул. Брянцева, д.67 к.2, кв.122");



        app.contact().modifyContact(contact);
        app.goTo().homePage();

        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

    }

