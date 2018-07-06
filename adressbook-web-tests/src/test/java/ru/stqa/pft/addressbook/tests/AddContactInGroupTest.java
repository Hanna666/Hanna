package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class AddContactInGroupTest {
    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            app.contact().homePage();
            app.contact().create(new ContactData().withFirstname("xtest1").withLastname("xtest2").withAddress("addd").withEmail("dcbs@kf.ru").
                    withEmail2("@22").withEmail3("@33").withHomePhone("h3244").withMobile("+79823").withWorkPhone("w2344"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));
        }
    }

    @Test
    public void testAddContactInGroup(){
        Groups groups, groupsBefore, groupsAfter;
        GroupData group;

        app.contact().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        groups = app.db().groupsNotUsed(contact);
        groupsBefore = contact.getGroups();

        if(groups.size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 4").withHeader("header 1").withFooter("footer 1"));
            groups = app.db().groupsNotUsed(contact);
            app.contact().homePage();
        }

        group = groups.iterator().next();
        app.contact().addContactInGroup(contact, group);
        groupsAfter = app.db().contactById(contact.getId()).getGroups();
        assertThat(groupsBefore.withAdded(group), equalTo(groupsAfter));

    }
}

