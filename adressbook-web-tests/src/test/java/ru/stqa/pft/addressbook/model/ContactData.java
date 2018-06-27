package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String id;
    private final String firstname;
    private final String lastname;
    private final String mobile;
    private final String email;


    public ContactData (String id, String firstname, String lastname, String mobile, String email) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstname, lastname, mobile, email);
    }

    public ContactData (String firstname, String lastname, String mobile, String email) {

        this.id = null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.email = email;

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

    public int getId() {return Integer.MAX_VALUE;}

    }


