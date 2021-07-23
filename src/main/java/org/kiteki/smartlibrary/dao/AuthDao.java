package org.kiteki.smartlibrary.dao;

import org.kiteki.smartlibrary.domain.auth.Role;
import org.kiteki.smartlibrary.domain.auth.User;

public interface AuthDao {
    User selectUser(String id);
    Role selectRole(String id);
    User selectUserByUserNameAndPasswd(String name, String passwd);
    Role selectRoleByUser(User user);
}
