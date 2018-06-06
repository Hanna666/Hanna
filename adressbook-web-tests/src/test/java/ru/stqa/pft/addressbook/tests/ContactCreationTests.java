package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation () {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm (new ContactData ("xtest1",  "xtest2", "xtest3",  "xtest4"));
        app.getContactHelper().submitContactCriation();
        app.getContactHelper().returnToHomePage();
    }

}
