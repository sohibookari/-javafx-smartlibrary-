package org.kiteki.smartlibrary.domain.auth;

public class User {
    String id;
    String name;
    String created_time;
    String passwd;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getPasswd() {
        return passwd;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", created_time='" + created_time + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
