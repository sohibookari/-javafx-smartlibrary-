package org.kiteki.smartlibrary.domain.auth;

public class User {
    String id;
    String name;
    String created_time;
    String passwd;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

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
