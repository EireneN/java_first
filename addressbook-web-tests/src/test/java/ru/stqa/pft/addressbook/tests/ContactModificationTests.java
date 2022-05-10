package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getContactHelper().openContactEditMode();
        app.getContactHelper().fillContactForm(new ContactData("Irina", "Nosova", "+79214789090", "irinan@yandex.ru"));
        app.getContactHelper().submitContactModification();
    }
}
