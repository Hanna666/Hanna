package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ContactCreationTests extends TestBase{

    @DataProvider
    public Iterator <Object[]> validContacts () throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }
    @Test (dataProvider = "validContacts")
    public void testContactCreation (ContactData contact) {
        app.contact().homePage();
        Set<ContactData> before = app.db().contacts();
        //File photo = new File("src/test/resources/photo.png");
        app.contact().create(contact);
        Set<ContactData> after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size()+1 );

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before,after);

    }


}
