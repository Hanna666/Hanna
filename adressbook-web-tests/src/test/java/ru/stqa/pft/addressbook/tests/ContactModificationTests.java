package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod

    public void ensurePrecondition(){
        app.contact().homePage();
        if ( app.contact().list().size() == 0) {
            app.contact().create(new ContactData().whisFirstname("xtest1").withLastname("xtest2") );
        }
    }

    @Test

    public void testsContactModification (){

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).
                whisFirstname("asd1").withLastname("dfg2").withMobile( "hgj3").withEmail("sdf4");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
