import org.kiteki.smartlibrary.dao.AuthRoleDaoImpl;
import org.kiteki.smartlibrary.domain.AuthRole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAuthRoleImpl {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AuthRoleDaoImpl authRoleDao = context.getBean("authRoleDaoImpl", AuthRoleDaoImpl.class);
        AuthRole authRole = authRoleDao.selectAuthRole("0");
        System.out.println(authRole);
    }
}
