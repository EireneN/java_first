package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactTests extends TestBase{

    @Test
    public void testNewContact() throws Exception {

        app.getNavigationHelper().goHomePage();
        app.getContactHelper().createContact(new ContactData("Artem", "Nosov", "+79523932745", "artemn@yandex.ru", "test1"), true);
    }
}
