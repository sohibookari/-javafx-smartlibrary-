package org.kiteki.smartlibrary.domain;

public class AuthUser {
    String id;
    String name;
    String created_time;
    String passwd;

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
