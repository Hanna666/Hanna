package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class LoginHelper extends HelperBase{

    public  LoginHelper(ApplicationManager app){
        super(app);
    }

    public void login() {
        wd.get(app.getProperty("web.baseURL")+"login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Login']"));
    }

    public void resetPassword(String page, String id){
        wd.get(String.format("%s%s.php%s",app.getProperty("web.baseURL"),page, id));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void newPassword(String newURL, String newPass) {
        wd.get(newURL);
        type(By.name("password"), newPass);
        type(By.name("password_confirm"), newPass);
        click(By.cssSelector("input[value='Update User']"));
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        //в потоке к элементам применяем фильтр - предикат.в результате получим только письма с нужным нам адресатом
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        //ищем ссылку с помощью регулярных выражений, но в нормальном варианте

        return regex.getText(mailMessage.text);//возвращает кусок текста построенный по регулятному выражению
    }

}
