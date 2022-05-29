package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @BeforeMethod
    public static void ensurePreconditions() {
        app.goTo().groupPage();
        if(! app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName ("test"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testNewContact() throws Exception {

        List<ContactData> before = app.getContactHelper().getContactList();

        ContactData contact = new ContactData("Artemka", "Nosov", "SPb",
                "+79523932745", "artemn@yandex.ru");

        app.getContactHelper().createContact(contact);
        app.goTo().homePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);

        int max = 0;
        for (ContactData c : after) {
                 if (c.getId() > max) {
                     max = c.getId();
                }
         }

        contact.setId(max);
        before.add(contact);
        Comparator<? super ContactData> byId = new Comparator<ContactData>() {
            @Override
            public int compare(ContactData o1, ContactData o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
    private void printSort(List<ContactData> list, String msg) {
        System.out.println(msg + "--->");
        for (ContactData contactData:list) {
        }

    }

    @Test (enabled = false)
    public void testNewContact3() throws Exception {
        int x = 8;
        for (int i = 0; i < x; i++) {
            testNewContact();
        }
    }
}

