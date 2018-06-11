package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HalperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new")); }

    public void fillContactForm (ContactData contactData){

        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
    }
    public void submitContactCriation() { click(By.xpath("//div[@id='content']/form/input[21]"));}
    public void returnToHomePage () {click(By.linkText("home"));}
    public void selectContact () {click(By.name("selected[]"));}
    public void delitionSelectedContact () {click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));}
    public void okDelitionContact () { wd.switchTo().alert().accept(); }
    public void modificationContact() {click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));}
    public void okModificationContact () {click(By.xpath("//div[@id='content']/form[1]/input[22]"));}
}
