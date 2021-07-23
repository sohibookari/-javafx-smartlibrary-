package org.kiteki.smartlibrary.domain.auth;

public class Role {
    String id;
    String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AuthRole{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
