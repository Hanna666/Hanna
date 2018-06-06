package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void testsContactModification (){
        app.getContactHelper().returnToHomePage();
        app.getContactHelper().modificationContact();
        app.getContactHelper().fillContactForm (new ContactData("test1",  "test2", "test3",  "test4"));
        app.getContactHelper().okModificationContact();
        app.getContactHelper().returnToHomePage();
    }
}
