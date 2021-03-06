package ru.stqa.pft.addressbook.tests;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {



    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        File file = new File("./src/test/java/ru/stqa/pft/resources/groups.xml");
        try (BufferedReader reader = new BufferedReader(new FileReader (file))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }

            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader (new File("./src/test/java/ru/stqa/pft/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson ();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());  //почти тоже самое что List<GroupData>.class
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator(); // каждый объект заворачиваем в маленький массив состоящий из 1 такого элемента
        }
    }


    @Test (dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {
            app.goTo().groupPage();
            Groups before = app.db().groups();
            app.group().create(group);
            assertThat(app.group().count(), equalTo(before.size() +1 ));
            Groups after = app.db().groups();

            GroupData groupData = group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
            assertThat(after, equalTo(before.withAdded(groupData)));
        }

    @Test (enabled = false)
    public void testBadGroupCreation() throws Exception {

        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }
}
