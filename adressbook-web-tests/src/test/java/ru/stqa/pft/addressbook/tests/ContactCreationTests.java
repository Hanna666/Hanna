package ru.stqa.pft.addressbook.tests;

import gw.internal.ext.com.beust.jcommander.Parameter;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Parameter(names = "-c", description = "GroupCount")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Test
    public void testContactCreation () {
        app.contact().homePage();
        Set<ContactData> before = app.contact().all();
        File photo = new File("src/test/resources/photo.png");
        ContactData contact = new ContactData().whithFirstname("xtest1").withLastname("xtest2").withAddress("addd").withEmail("dcbs@kf.ru").
                withEmail2("@22").withEmail3("@33").withHomePhone("h3244").withMobile("+79823").withWorkPhone("w2344").withPhoto(photo);
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()+1 );

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before,after);

    }


}
