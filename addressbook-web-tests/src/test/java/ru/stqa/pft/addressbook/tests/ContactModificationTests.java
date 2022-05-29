package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public static void ensurePreconditions() {
        app.goTo().homePage();

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Artem", "Nosov", "SPb",
                    "+79523932745", "artemn@yandex.ru"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactModification() throws Exception {

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().openContactEditMode(0);
        ContactData contact = new ContactData(before.get(0).getId(), "Irinka", "Nosova", "Sochi",
                "+79214789090", "irinan@yandex.ru");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();

        app.goTo().homePage();

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

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    private void printSort(List<ContactData> list, String msg) {
        for (ContactData contactData:list) {
        }
    }
}
