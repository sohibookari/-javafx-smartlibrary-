import org.junit.Test;
import org.kiteki.smartlibrary.dao.AuthDao;
import org.kiteki.smartlibrary.dao.AuthDaoImpl;
import org.kiteki.smartlibrary.dao.BookDaoImpl;
import org.kiteki.smartlibrary.domain.auth.Role;
import org.kiteki.smartlibrary.domain.auth.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AuthDaoTest {
    private AuthDaoImpl authDao;

    public AuthDaoTest() {
        // init dao.
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        authDao = context.getBean("authDaoImpl", AuthDaoImpl.class);
    }

    @Test
    public void selectUserTest() {
        User testUser = authDao.selectUser("6fd0a2bdec1b11ebae0a00155db2f5a6");
        testUser.getName();
    }

    @Test
    public void selectUserByUserNameAndPasswdTest() {
        User userInfo = new User();
        userInfo.setName("test_user");
        userInfo.setPasswd("test_passwd");
        User testUser = authDao.selectUserByUserNameAndPasswd(userInfo);
        testUser.getName();
    }

    @Test
    public void selectRoleTest() {
        Role testRole = authDao.selectRole("3a82ecc6ec1b11ebae0a00155db2f5a6");
        testRole.getName();
    }

    @Test
    public void selectRoleByUserTest() {
        User testUser = authDao.selectUser("6fd0a2bdec1b11ebae0a00155db2f5a6");
        Role testRole = authDao.selectRoleByUser(testUser);
        testRole.getName();
    }
}
