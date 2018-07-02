package ru.stqa.pft.addressbook.generators;

import com.thoughtworks.xstream.XStream;
import gw.internal.ext.com.beust.jcommander.JCommander;
import gw.internal.ext.com.beust.jcommander.Parameter;
import gw.internal.ext.com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "GroupCount")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String [] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();


    }

    private void run() throws IOException {
        List <ContactData> contact = generateContacts(count);
        if ( format.equals("csv")){
            saveAsCsv(contact, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contact, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;$s;%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getMobilePhone(), contact.getEmail()));
        }
        writer.close();
    }
    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }
    private static List<ContactData> generateContacts(int count) {
        List <ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstname(String.format("test %s", i)).withLastname(String.format("last %s", i)).
                    withMobile(String.format("9878 %s", i)).withEmail(String.format("@re %s", i)));
        }
        return contacts;
    }
}
