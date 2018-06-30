package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HalperBase {

    public NavigationHelper(WebDriver wd) {

        super(wd);
    }

    public void GroupPage() {
        click(By.linkText("groups"));
    }
}



