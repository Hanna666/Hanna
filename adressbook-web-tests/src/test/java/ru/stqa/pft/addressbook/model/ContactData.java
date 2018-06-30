package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private  int id = Integer.MAX_VALUE;
    private  String firstname;
    private  String lastname;
    private  String mobile;
    private  String email;

    public ContactData whisFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstname, lastname, mobile, email);
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;

    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
public ContactData  withId (int id){
        this.id = id;
        return this;
}


    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() { return lastname; }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {return id;}

    }


