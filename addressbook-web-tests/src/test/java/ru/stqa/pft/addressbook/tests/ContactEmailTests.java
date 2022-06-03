package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactEmailTests extends TestBase {
    @BeforeMethod
    public static void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("Artemka").withLastname("Nosov")
                    .withEmail("artemn@mail.ru").withEmailTwo("art@mail.ru").withEmailThree("nosov@mail.ru"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactEmails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmailTwo(), contact.getEmailThree())
                .stream().filter((s) -> !s.equals("")).
                collect(Collectors.joining("\n"));
    }
}



