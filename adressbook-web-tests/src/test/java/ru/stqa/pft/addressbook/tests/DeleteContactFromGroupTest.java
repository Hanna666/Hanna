package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteContactFromGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        GroupData group;
        Groups groupsCurrentContact;
        ContactData currentContact = null;
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();


        if(app.db().contacts().size() == 0) {
            app.contact().homePage();
            app.contact().create (new ContactData().withFirstname("xtest1").withLastname("xtest2").withAddress("addd").withEmail("dcbs@kf.ru").
                    withEmail2("@22").withEmail3("@33").withHomePhone("h3244").withMobile("+79823").withWorkPhone("w2344"));
        }
        if(groups.size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1"));
        }


        for (ContactData contact:contacts) {
            groupsCurrentContact = contact.getGroups();
            if(groupsCurrentContact.size() != 0){
                currentContact = contact;
                break;
            }
        }

        if(currentContact == null){
            app.contact().homePage();
            currentContact = contacts.iterator().next();
            group = groups.iterator().next();
            app.contact().addContactInGroup(currentContact, group);
        }


    }

    @Test
    public void deleteContactFromGroup(){

        GroupData group;
        Groups groupsAfter;
        Groups groupsCurrentContact = null;
        ContactData currentContact = null;

        Contacts contacts = app.db().contacts();
        app.contact().homePage();


        for (ContactData contact:contacts) {
            groupsCurrentContact = contact.getGroups();
            if(groupsCurrentContact.size() != 0){
                currentContact = contact;
                break;
            }
        }

        group = groupsCurrentContact.iterator().next();
        app.contact().deleteContactFromGroup(currentContact, group);
        groupsAfter = app.db().contactById(currentContact.getId()).getGroups();
        MatcherAssert.assertThat(groupsCurrentContact.without(group),equalTo(groupsAfter));

    }
}
