package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

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

        List<ContactData> before = app.contactHelper().contactList();
        int index = before.size() - 1;

        app.contactHelper().delete(index);

        app.goTo().homePage();

        List<ContactData> after = app.contactHelper().contactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
