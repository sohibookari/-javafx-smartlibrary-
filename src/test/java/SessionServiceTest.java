import org.junit.Before;
import org.junit.Test;
import org.kiteki.smartlibrary.service.SessionService;

public class SessionServiceTest {
    private SessionService sessionService;

    public SessionServiceTest() {
        sessionService = new SessionService();
    }

    @Before
    public void sessionLoginTest() {
        String name = "test_user";
        String passwd = "test_passwd";
        sessionService.login(name, passwd);
    }

    @Test
    public void sessionStatusPrinterTest() {
        sessionService.checkSessionStatus();
    }

    @Test
    public void sessionGetterTest() {
        String id = sessionService.getUserSession().getRoleId();
    }
}
