package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

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

        Set<ContactData> before = app.contactHelper().allContact();
        ContactData modifiedContact = before.iterator().next();
        int id = modifiedContact.getId();
        ContactData contact = new ContactData()
                .setId(id)
                .withContactName("Irinka")
                .withContactSurname("Nosova")
                .withContactAddress("Sochi")
                .withContactMobNumber("+79214789090")
                .withContactEmail("irinan@yandex.ru");



        app.contactHelper().modifyContact(contact);
        app.goTo().homePage();

        Set<ContactData> after = app.contactHelper().allContact();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);

        Assert.assertEquals(before, after);
    }



    private void printSort(List<ContactData> list, String msg) {
        for (ContactData contactData:list) {
        }
    }
}
