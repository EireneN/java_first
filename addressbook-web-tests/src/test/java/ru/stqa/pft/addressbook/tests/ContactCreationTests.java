package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("./src/test/java/ru/stqa/pft/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson ();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

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

    @Test (dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact)  {

        Contacts before = app.contact().allContacts();

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

