package org.kiteki.smartlibrary.service;

import org.kiteki.smartlibrary.dao.AuthDaoImpl;
import org.kiteki.smartlibrary.domain.auth.Role;
import org.kiteki.smartlibrary.domain.auth.User;
import org.kiteki.smartlibrary.domain.session.UserSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SessionService {
    private User user;
    private Role role;
    AuthDaoImpl authDao;

    public SessionService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        authDao = context.getBean("authDaoImpl", AuthDaoImpl.class);
    }

    public void login(String username, String passwd) {
        User userinfo = new User();
        userinfo.setName(username);
        userinfo.setPasswd(passwd);
        user = authDao.selectUserByUserNameAndPasswd(userinfo);
        role = authDao.selectRoleByUser(user);
    }

    public void checkSessionStatus() {
        System.out.println("user = " + user);
        System.out.println("role = " + role);
    }

    public UserSession getUserSession() {
        UserSession userSession = new UserSession();
        userSession.setUserId(user.getId());
        userSession.setUserName(user.getName());
        userSession.setRoleId(role.getId());
        userSession.setRoleName(role.getName());
        return userSession;
    }
}
