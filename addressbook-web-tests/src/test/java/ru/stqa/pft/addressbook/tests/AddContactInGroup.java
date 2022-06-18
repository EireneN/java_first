package ru.stqa.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddContactInGroup extends TestBase {

    Logger logger = LoggerFactory.getLogger(AddContactInGroup.class);
    GroupData groupData;
    ContactData contact;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() != 0) {
            Groups groups = app.db().groups();
            groupData = groups.iterator().next();
        } else {
            app.goTo().groupPage();
            GroupData testContact = new GroupData().withName("testContact");
            app.group().create(testContact);

            Groups groups = app.db().groups();
            groupData = groups.iterator().next();
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData().withFirstname("Artemka").withLastname("Nosov").
                    withAddress("SPb").withMobilePhone("+79523932745").withEmail("artemn@yandex.ru"));
        }
        app.goTo().homePage();
    }

    @Test
    public void addContactInGroup() {
        logger.info("Start test: addContactInGroup");
        Contacts before = app.db().contacts();
        ContactData addGroupContact = before.iterator().next();
        contact = addGroupContact;
        app.contact().selectContactById(addGroupContact.getId());
        app.contact().selectGroupByIdAndDropDownName(groupData.getId(), "to_group");
        app.contact().clickAdd();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());



        assertThat(after, equalTo(before.without(addGroupContact).withAdded(addGroupContact.inGroup(groupData))));
        logger.info("Stop test: addContactInGroup");
    }

    @AfterMethod
    public void ensurePostconditions() {
        app.goTo().homePage();
        app.contact().delete(contact);

    }



}
