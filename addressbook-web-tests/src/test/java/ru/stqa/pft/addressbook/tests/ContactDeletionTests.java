package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.TimeUtils;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().goHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Artem", "Nosov",
                    "+79523932745", "artemn@yandex.ru"));
            app.getNavigationHelper().goHomePage();
        }

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlert();

        app.getNavigationHelper().goHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
