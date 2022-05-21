package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;

public class NewContactTests extends TestBase{

    @Test
    public void testNewContact() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
        }

        app.getContactHelper().createContact( new ContactData("Artem", "Nosov",
                "+79523932745", "artemn@yandex.ru"));

    }

    @Test
    public void testNewContact3() throws Exception {
        int x = 8;
        for (int i = 0; i < x; i++) {
            System.out.println(i);
            testNewContact();
        }
    }
}

