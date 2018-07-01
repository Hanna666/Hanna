package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).
                whisFirstname("asd1").withLastname("dfg2").withMobile( "hgj3").withEmail("sdf4");
        app.contact().modify(contact);
        Set <ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
    }


}
