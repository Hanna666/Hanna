package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelitionTests extends TestBase {
    @BeforeMethod

    public void ensurePrecondition(){
        app.contact().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("xtest1").withLastname("xtest2"));
        }
    }

    @Test
    public void testContactDelition ()

    {

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(deletedContact);
        Assert.assertEquals(before,after);

    }

}
