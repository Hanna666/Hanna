package ru.stqa.pft.addressbook.generators;

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

        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);
    }

    private void run() throws IOException {
        List <ContactData> contact = generateContacts(count);
        save(contact, new File(file));
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s,%s,$s,%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getMobilePhone(), contact.getEmail()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List <ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().whithFirstname(String.format("test %s", i)).withLastname(String.format("last %s", i)).
                    withMobile(String.format("9878 %s", i)).withEmail(String.format("@re %s", i)));
        }
        return contacts;
    }
}
