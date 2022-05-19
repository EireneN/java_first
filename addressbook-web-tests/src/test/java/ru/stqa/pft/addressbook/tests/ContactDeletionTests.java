package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().goHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Artem", "Nosov",
                    "+79523932745", "artemn@yandex.ru"));
        }
        app.getNavigationHelper().goHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlert();
    }
}
