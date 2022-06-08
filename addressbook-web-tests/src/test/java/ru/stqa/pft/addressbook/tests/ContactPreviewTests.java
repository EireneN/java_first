package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPreviewTests extends TestBase {

    @BeforeMethod
    public static void ensurePreconditions() {
        app.goTo().homePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstname("Artemka").withLastname("Nosov")
                    .withMobilePhone("+79523932745").withHomePhone("599-03-84").withWorkPhone("8 (812) 678 98 98").withPhoneTwo("+79213450625")
                    .withEmail("artemn@mail.ru").withEmailTwo("art@mail.ru").withEmailThree("nosov@mail.ru")
                    .withAddress("СПб, ул. Брянцева, д.67 к.2, кв.122"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAll() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);
        System.out.println(contact.getAllPhones());
        System.out.println(mergePhones(contactInfoFromEditForm));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo((contactInfoFromEditForm.getAddress())));

    }
    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getPhoneTwo())
                .stream().filter((s) -> ! s.equals("")).
                map(ContactPreviewTests::cleaned).
                collect(Collectors.joining("\n"));
    }

    public static String cleaned (String phone) {
     return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
   }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmailTwo(), contact.getEmailThree())
                .stream().filter((s) -> !s.equals("")).
                collect(Collectors.joining("\n"));
    }
}

