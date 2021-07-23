package org.kiteki.smartlibrary.domain;

public class UserRoleRela {
    Integer id;
    String user_id;
    String role_id;

    @Override
    public String toString() {
        return "UserRoleRela{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", role_id='" + role_id + '\'' +
                '}';
    }
}
