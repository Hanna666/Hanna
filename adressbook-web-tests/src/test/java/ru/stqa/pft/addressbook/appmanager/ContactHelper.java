package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
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
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
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
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();

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
        homePage();
    }
    public void modify(ContactData contact) {
        modificationContact();
        fillContactForm (contact);
        okModificationContact();
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
           ContactData contact = new ContactData().whisFirstname(element.findElement( By.xpath(".//td[3]")).getText()).
                   withLastname(element.findElement(By.xpath(".//td[2]")).getText()).withMobile(element.findElement(By.xpath(".//td[6]")).getText()).
                   withEmail(element.findElement(By.xpath(".//td[5]")).getText());
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
        for (WebElement element: elements){

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            ContactData contact = new ContactData().withId(id).whisFirstname(element.findElement( By.xpath(".//td[3]")).getText()).
                    withLastname(element.findElement(By.xpath(".//td[2]")).getText()).withMobile(element.findElement(By.xpath(".//td[6]")).getText()).
                    withEmail(element.findElement(By.xpath(".//td[5]")).getText());
            contacts.add(contact);
        }
        return contacts;
    }


}