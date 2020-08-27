package com.epam.afshop.entity;

public class UserType {
    private int id;
    private String name;
    private int languageId;

    public UserType(int id, String name, int languageId) {
        this.id = id;
        this.name = name;
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
