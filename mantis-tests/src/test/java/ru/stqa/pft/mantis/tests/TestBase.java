package ru.stqa.pft.mantis.tests;

import com.google.protobuf.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.print.DocFlavor;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();

    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }


}
    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
               MantisConnectPortType mc = getMantisConnect();
                IssueData issue =mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"),
                        BigInteger.valueOf(issueId));
                ObjectRef status = issue.getStatus();
                return !status.getName().equals("closed");
            }

           public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
                if (isIssueOpen(issueId)) {
                    throw new SkipException("Ignored because of issue " + issueId);
                }
            }

            private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
                return new MantisConnectLocator()
                        .getMantisConnectPort(new DocFlavor.URL(app.getProperty("mantis.SoapConnUrl")));
            }
        }