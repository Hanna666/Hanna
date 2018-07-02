package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod

    public void ensurePrecondition(){
        app.contact().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create (new ContactData().withFirstname("xtest1").withLastname("xtest2").withAddress("addd").withEmail("dcbs@kf.ru").
                    withEmail2("@22").withEmail3("@33").withHomePhone("h3244").withMobile("+79823").withWorkPhone("w2344"));
        }
    }

    @Test

    public void testsContactModification (){

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).
                withFirstname("xtest1").withLastname("xtest2").withAddress("addd").withEmail("dcbs@kf.ru").
                withEmail2("@22").withEmail3("@33").withHomePhone("h3244").withMobile("+79823").withWorkPhone("w2344");
        app.contact().modify(contact);
        Set <ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
    }


}
