package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {

        app.goToGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test11", "test12", "test13"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }
}
