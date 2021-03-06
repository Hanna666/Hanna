package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator <Object[]> validGroups () throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validGroups")
    public void testGroupCreation( GroupData group) {
            app.goTo().groupPage();
            Groups before = app.db().groups();
            app.group().create(group);
            Groups after = app.db().groups();
            assertThat(after.size(), equalTo(before.size() + 1));

            assertThat(after, equalTo(
                    before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }

    }


