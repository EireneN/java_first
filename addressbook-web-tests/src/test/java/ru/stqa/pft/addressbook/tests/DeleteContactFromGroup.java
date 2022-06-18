package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteContactFromGroup extends TestBase {

    Logger logger = LoggerFactory.getLogger(DeleteContactFromGroup.class);
    GroupData groupData;
    int contactId;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() != 0) {
            Groups groups = app.db().groups();
            groupData = groups.iterator().next();
        } else {
            app.goTo().groupPage();
            GroupData testContact = new GroupData().withName("testContact2");
            app.group().create(testContact);

            Groups groups = app.db().groups();
            groupData = groups.iterator().next();
        }

        app.goTo().homePage();
        app.contact().createContact(new ContactData().withFirstname("Igor").withLastname("Melnik")
                .inGroup(groupData));
        app.goTo().homePage();
    }

    @Test
    public void deleteContactFromGroup() {
        logger.info("Start test: addContactInGroup");
        Contacts before = app.db().contacts();

        app.contact().selectGroupByIdAndDropDownName(groupData.getId(), "group");

        contactId = app.contact().all().iterator().next().getId();
        ContactData contactWithGroup = null;
        for (ContactData element : before) {
            if (contactId == element.getId()) {
                contactWithGroup = element;
            }
        }

        Groups without = contactWithGroup.getGroups().without(groupData);
        ContactData contactWithoutGroup = contactWithGroup.copy();
        contactWithoutGroup.setGroups(without);

        app.contact().selectContactById(contactId);
        app.contact().clickRemove();

        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(contactWithGroup).withAdded(contactWithoutGroup)));
        logger.info("Stop test: addContactInGroup");
    }

    @AfterMethod
    public void ensurePostconditions() {
        app.goTo().homePage();
        app.contact().delete(contactId);

    }


}

