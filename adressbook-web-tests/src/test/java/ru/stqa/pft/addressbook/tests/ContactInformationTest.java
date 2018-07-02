package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactInformationTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.contact().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create (new ContactData().whithFirstname("xtest1").withLastname("xtest2").withAddress("addd").withEmail("dcbs@kf.ru").
                    withEmail2("@22").withEmail3("@33").withHomePhone("h3244").withMobile("+79823").withWorkPhone("w2344"));
        }
    }
        @Test

        public void testsContactsInformation() {
            app.contact().homePage();
            ContactData contact = app.contact().all().iterator().next();
            ContactData contactInformationForm = app.contact().infoFormEditForm(contact);
            assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInformationForm)));
            assertThat(contact.getAllEmail(), equalTo(mergeEmails(contactInformationForm)));
            assertThat(contact.getAddress(), equalTo(contactInformationForm.getAddress()));
        }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter(s -> !s.equals(""))
                .map(ContactInformationTest::clened)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails (ContactData contact){
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter(s -> !s.equals(""))
                .map(ContactInformationTest::clened)
                .collect(Collectors.joining("\n"));
    }

    public static String clened(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
        }
    }


