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

        if (!app.contactHelper().isThereAContact()) {
            app.contactHelper().createContact(new ContactData().withContactName("Artemka").withContactSurname("Nosov").
                    withContactAddress("SPb").withContactMobNumber("+79523932745").withContactEmail("artemn@yandex.ru"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactModification() throws Exception {

        List<ContactData> before = app.contactHelper().contactList();
        int id = before.get(0).getId();
        ContactData contact = new ContactData()
                .setId(id)
                .withContactName("Irinka")
                .withContactSurname("Nosova")
                .withContactAddress("Sochi")
                .withContactMobNumber("+79214789090")
                .withContactEmail("irinan@yandex.ru");



        app.contactHelper().modifyContact(contact);
        app.goTo().homePage();

        List<ContactData> after = app.contactHelper().contactList();
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
