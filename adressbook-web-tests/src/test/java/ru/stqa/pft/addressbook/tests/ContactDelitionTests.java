package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDelitionTests extends TestBase {

    @Test
    public void testContactDelition ()

    {
        app.getContactHelper().returnToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("xtest1",  "xtest2", "xtest3",  "xtest4"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().delitionSelectedContact();
        app.getContactHelper().okDelitionContact();
        app.getContactHelper().returnToHomePage();

    }

}
