package ru.stqa.pft.mantis.appmanager;


import ru.stqa.pft.mantis.model.MailMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app){
        this.app = app;
        wiser = new Wiser();//создается почтовый сервер
    }

    public List<MailMessage> waitForMail(int count, long timeout){//count - кол-во ожидаемых писем
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start+timeout){
            if(wiser.getMessages().size() >= count){//если почты пришло нужное кол-во, то ожидание прекращаем
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
                //берем список, превращаем в поток, ко всем элементам потока применяем одну и ту же функцию,
                // и новые, получившиеся объекты, собираем снова в список
            }try {
                Thread.sleep(1000);//если почты мало - ждем
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        throw new Error("No mail :(");
    }

    public static MailMessage toModelMail (WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), mm.getContent().toString());

        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void start(){
        wiser.start();//запускаем почтовый сервер
    }

    public void stop(){
        wiser.stop();//останавливаем почтовый сервер
    }
}
