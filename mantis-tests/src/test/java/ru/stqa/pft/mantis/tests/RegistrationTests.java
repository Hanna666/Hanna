package ru.stqa.pft.mantis.tests;

import com.google.protobuf.ServiceException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

    //@BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException, ServiceException {
        skipIfNotFixed(0000001);
        long now = System.currentTimeMillis();
        String user = String.format("user%s",now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        app.james().createUser(user, password);//создание пользователя на внешнем сервере
        app.registration().start(user, email);
        //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);//ждем 2 письма 10 секунд

        //тут получаем почту с внещнего сервера
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);

        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();

        return regex.getText(mailMessage.text);
    }

    //@AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
