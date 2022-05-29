package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public static void ensurePreconditions() {
        app.goTo().homePage();
        if (! app.contactHelper().isThereAContact()) {
            app.contactHelper().createContact(new ContactData().withContactName("Artemka").withContactSurname("Nosov").
                    withContactAddress("SPb").withContactMobNumber("+79523932745").withContactEmail("artemn@yandex.ru"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion() throws Exception {

        Set<ContactData> before = app.contactHelper().allContact();
        ContactData deletedContact = before.iterator().next();

        app.contactHelper().delete(deletedContact);
        app.goTo().homePage();

        Set<ContactData> after = app.contactHelper().allContact();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }


}
