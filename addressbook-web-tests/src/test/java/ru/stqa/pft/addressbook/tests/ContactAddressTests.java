package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {
    @BeforeMethod
    public static void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("Artemka").withLastname("Nosov")
                    .withAddress("СПб, ул. Брянцева, д.67 к.2, кв.122"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo((contactInfoFromEditForm.getAddress())));

    }

}

