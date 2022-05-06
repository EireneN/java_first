package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupDelitionTests extends TestBase {

  @Test
  public void testGroupDelition() throws Exception {
    goToGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
