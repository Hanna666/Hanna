package ru.stqa.pft.mantis.appmanager;


import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app){
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup);//удаляем предыдущую резервную копию
        ftp.rename(target, backup);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }

    public void restore(String backup, String target) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));//устанавливается соединение удаленной машиной по ftp
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);//удаляется файл, который загрузили в upload
        ftp.rename(backup, target);//восстанавливается оригинальный файл из резервной копии
        ftp.disconnect();//разрыв соединения
    }
}
