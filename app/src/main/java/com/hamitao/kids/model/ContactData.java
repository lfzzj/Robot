package com.hamitao.kids.model;

public class ContactData {
    public String id;
    public String name;
    public  String number;

    public ContactData() {
    }

    public ContactData(String id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public void setId(String idValue) {
        id = idValue;
    }

    public void setContactName(String contactName) {
        name = contactName;
    }

    public void setNumber(String phoneNumber) {
        number = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getContactName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

}
