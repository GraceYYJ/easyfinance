package com.gracyya.model;

public class Myuser {
    private Long id;

    private String name;

    private String password;

    private Long authority;

    public Myuser(Long id, String name, String password, Long authority) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.authority = authority;
    }

    public Myuser() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getAuthority() {
        return authority;
    }

    public void setAuthority(Long authority) {
        this.authority = authority;
    }
}