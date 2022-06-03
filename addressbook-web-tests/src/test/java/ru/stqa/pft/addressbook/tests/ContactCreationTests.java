package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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

        Contacts before = app.contact().allContacts();

        ContactData contact = new ContactData().withFirstname("Artemka").withLastname("Nosov").
                withAddress("SPb").withMobilePhone("+79523932745").withEmail("artemn@yandex.ru").withAddress("СПб, ул. Брянцева, д.67 к.2, кв.122");

        app.contact().createContact(contact);
        app.goTo().homePage();

        Contacts after = app.contact().allContacts();
        int b = before.size() +1;
        assertThat(after.size(), equalTo(b));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }



    @Test (enabled = false)
    public void testNewContact3() throws Exception {
        int x = 8;
        for (int i = 0; i < x; i++) {
            testNewContact();
        }
    }

    @Test
    public void contactCreationWithFoto() {
        app.goTo().homePage();
        app.contact().initContactCreation();
        File photo = new File("src/test/java/ru/stqa/pft/resources/stru.png");
        app.contact().fillContactForm(new ContactData().withFirstname("Stas").withLastname("Novikov").withPhoto(photo), true);
        app.contact().sendForm();
        app.goTo().homePage();

    }
    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/java/ru/stqa/pft/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());

    }
}

