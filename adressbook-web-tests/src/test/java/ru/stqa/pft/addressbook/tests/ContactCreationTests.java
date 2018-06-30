package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation () {
        app.contact().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().whisFirstname("asd1").withLastname("dfg2").withMobile("hgj3").withEmail("sdf4");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()+1 );

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
