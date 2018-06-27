package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();

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

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCriation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
       return isElementPresent(By.name("selected[]"));

    }
    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element: elements){

//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData (null,element.findElement( By.xpath("//td[3]")).getText(),
                    element.findElement(By.xpath("//td[2]")).getText(), element.findElement(By.xpath("//td[6]")).getText(),
                    element.findElement(By.xpath("//td[5]")).getText());
            contacts.add(contact);
        }
        return contacts;
    }
}