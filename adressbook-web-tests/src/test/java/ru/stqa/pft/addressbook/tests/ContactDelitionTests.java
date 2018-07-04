package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;


public class ContactDelitionTests extends TestBase {

    @BeforeMethod

    public void ensurePrecondition(){
        if (app.db().contacts().size() == 0) {
            app.contact().homePage();
            app.contact().create(new ContactData().withFirstname("xtest1").withLastname("xtest2"));
        }
    }

    @Test
    public void testContactDelition ()

    {

        Set<ContactData> before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);

        Set<ContactData> after = app.db().contacts();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(deletedContact);
        Assert.assertEquals(before,after);

    }

}
