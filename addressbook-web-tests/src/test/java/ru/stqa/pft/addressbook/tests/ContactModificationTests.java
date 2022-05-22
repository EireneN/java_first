package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() throws Exception {
        app.getNavigationHelper().goHomePage();

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Artem", "Nosov",
                    "+79523932745", "artemn@yandex.ru"));
        }
        app.getNavigationHelper().goHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().openContactEditMode(0);
        ContactData contact = new ContactData(before.get(0).getId(), "Irinka", "Nosova",
                "+79214789090", "irinan@yandex.ru");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();

        app.getNavigationHelper().goHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(contact);
        Comparator<? super ContactData> byId = new Comparator<ContactData>() {
            @Override
            public int compare(ContactData o1, ContactData o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
        printSort(before, "befor sort");
        printSort(after, "befor sort");

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

        printSort(before, "after sort");
        printSort(after, "after sort");

    }
    private void printSort(List<ContactData> list, String msg) {
        System.out.println(msg + "Print--->");
        for (ContactData contactData:list) {
            System.out.println(contactData.toString());
        }
    }
}
