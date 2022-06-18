package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public static void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData().withFirstname("Artemka").withLastname("Nosov").
                    withAddress("SPb").withMobilePhone("+79523932745").withEmail("artemn@yandex.ru"));
        } app.goTo().homePage();
    }


    @Test
    public void testContactDeletion() throws Exception {

        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.contact().delete(deletedContact);
        app.goTo().homePage();

        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(deletedContact)));
    }



}
