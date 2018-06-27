package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDelitionTests extends TestBase {

    @Test
    public void testContactDelition ()

    {
        app.getContactHelper().returnToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("xtest1",  "xtest2", "xtest3",  "xtest4"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().delitionSelectedContact();
        app.getContactHelper().okDelitionContact();
        app.getContactHelper().returnToHomePage();

        List <ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);

    }

}
