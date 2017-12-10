package com.sayed.site;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private long userId;
    private String username;
    private String name;

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }
}
