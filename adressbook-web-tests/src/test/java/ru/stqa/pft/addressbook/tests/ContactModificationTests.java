package ru.stqa.pft.addressbook.tests;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test

    public void testsContactModification (){
        app.getContactHelper().returnToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData ("xtest1",  "xtest2", null,  null));
        }
        List <ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().modificationContact();
        ContactData contact = new ContactData("asd1",  "dfg2", "hgj3",  "sdf4");
        app.getContactHelper().fillContactForm (contact);
        app.getContactHelper().okModificationContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
        Comparator <? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, (after));
    }
}
