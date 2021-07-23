package org.kiteki.smartlibrary.dao;

import org.kiteki.smartlibrary.domain.AuthRole;

public interface AuthRoleDao {
    AuthRole selectAuthRole(String id);
}
