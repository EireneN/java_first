package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.json.JsonOutput;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NewContactTests extends TestBase{

    @Test
    public void testNewContact() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
        }
        app.getNavigationHelper().goHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();

        ContactData contact = new ContactData("Artemka", "Nosov",
                "+79523932745", "artemn@yandex.ru");

        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);

        System.out.println("serach max id");
        int max = 0;
        for (ContactData c : after) {
                 if (c.getId() > max) {
                     max = c.getId();
                     System.out.println(max);
                }
         }
        System.out.println("MaxId = " + max);

        System.out.println("Print lists before add contact.");
        printSort(before, "1---список before");
        printSort(after, "1---список after");

        contact.setId(max);
        before.add(contact);
        Comparator<? super ContactData> byId = new Comparator<ContactData>() {
            @Override
            public int compare(ContactData o1, ContactData o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
        System.out.println("Print lists before sorting.");
        printSort(before, "2---список before");
        printSort(after, "2---список after");

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

        System.out.println("Print lists after sorting.");
        printSort(before, "3---список before");
        printSort(after, "3---список after");

    }
    private void printSort(List<ContactData> list, String msg) {
        System.out.println(msg + "--->");
        for (ContactData contactData:list) {
            System.out.println(contactData.toString());
        }

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

