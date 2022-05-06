package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class GroupDelitionTests extends TestBase {

  @Test
  public void testGroupDelition() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
