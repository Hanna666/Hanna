package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.dialect.Dialect;
import org.hibernate.sql.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HalperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData) {

        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobilePhone());
        //type(By.name("work"), contactData.getWorkPhone());
        //type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
        //type(By.name("email2"), contactData.getEmail2());
        //type(By.name("email3"), contactData.getEmail3());
        //type(By.name("address"), contactData.getAddress());
        //attach(By.name("photo"), contactData.getPhoto());
    }

    public void submitContactCriation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void homePage() {
        click(By.linkText("home"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();

    }
    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }
    public void selectGroup (String id) {
        wd.findElement(By.cssSelector("option[value'" + id + "]")).click();
    }

    public void delitionSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void okDelitionContact() {
        wd.switchTo().alert().accept();
    }

    public void modificationContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void okModificationContact() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCriation();
        contactCach = null;
        homePage();
    }
    public void modify(ContactData contact) {
        initModificationContactById(contact.getId());
        fillContactForm (contact);
        okModificationContact();
        contactCach = null;
        homePage();
    }


    public void delete(int index) {
        selectContact(index);
        delitionSelectedContact();
        okDelitionContact();
        homePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        delitionSelectedContact();
        okDelitionContact();
        contactCach = null;
        homePage();
    }



    public boolean isThereAContact() {
       return isElementPresent(By.name("selected[]"));

    }
    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
        for (WebElement element: elements){

        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
           ContactData contact = new ContactData().withFirstname(element.findElement( By.xpath(".//td[3]")).getText()).
                   withLastname(element.findElement(By.xpath(".//td[2]")).getText()).withMobile(element.findElement(By.xpath(".//td[6]")).getText()).
                   withEmail(element.findElement(By.xpath(".//td[5]")).getText());
            contacts.add(contact);
        }
        return contacts;
    }

    private Contacts contactCach = null;

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.xpath(".//tr[@name='entry']"));
        for (WebElement row: rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).
                    withAllPhones(allPhones).
                    withAllEmail(allEmails).
                    withAddress(address));

        }
        return contacts;
    }


    public ContactData infoFormEditForm(ContactData contact) {
        initModificationContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withMobile(mobile).
                withWorkPhone(work).withHomePhone(home).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    private void initModificationContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
               selectContactById(contact.getId());
                addInGroup(group);
                contactCach  = null;
                homePage();
            }

            private void addInGroup(GroupData group){
                WebElement window = wd.findElement(By.name("to_group"));
                Select select = new Select((Dialect) window);
                selectGroup(String.valueOf(group.getId()));
                click(By.name("add"));
            }

            public void deleteContactFromGroup(ContactData contact, GroupData group) {
                deleteFromGroup(contact, group);
                contactCach = null;
                homePage();
            }

            private void deleteFromGroup(ContactData contact, GroupData group){
                WebElement window = wd.findElement(By.name("group"));
                Select select = new Select((Dialect) window);
                selectGroup(String.valueOf(group.getId()));
                selectContactById(contact.getId());
                click(By.name("remove"));
            }


}
