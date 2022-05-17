package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().goHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Artem", "Nosov",
                    "+79523932745", "artemn@yandex.ru", "test1"));
        }
        app.getNavigationHelper().goHomePage();
        app.getContactHelper().openContactEditMode();
        app.getContactHelper().fillContactForm(new ContactData("Irina", "Nosova",
                "+79214789090", "irinan@yandex.ru", null), false);
        app.getContactHelper().submitContactModification();
    }
}
