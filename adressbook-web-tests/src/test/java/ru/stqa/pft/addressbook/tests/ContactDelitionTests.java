package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDelitionTests extends TestBase {

    @Test
    public void testContactDelition ()

    {
        app.getContactHelper().returnToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().delitionSelectedContact();
        app.getContactHelper().okDelitionContact();
        app.getContactHelper().returnToHomePage();

    }

}
