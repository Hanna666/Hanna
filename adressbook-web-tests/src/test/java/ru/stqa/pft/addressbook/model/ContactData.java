package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table (name = "addressbook")
public class ContactData {
    @XStreamOmitField

    @Id
    @Column (name = "id")
    private int id = Integer.MAX_VALUE;

    @Column (name = "firstname")
    private String firstname;

    @Column (name = "lastname")
    private String lastname;

    @Column (name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Column (name = "home")
    @Type(type = "text")
    private String homePhone;

    @Column (name = "work")
    @Type(type = "text")
    private String worckPhone;

    @Column (name = "email")
    @Type(type = "text")
    private String email;

    @Column (name = "email2")
    @Type(type = "text")
    private String email2;

    @Column (name = "email3")
    @Type(type = "text")
    private String email3;

    @Column (name = "address")
    @Type(type = "text")
    private String address;

    @Transient
    private String allPhones;

    @Transient
    private String allEmail;

    @Column (name = "photo")
    @Type(type = "text")
    private String photo;


    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getAllEmail() {
        return allEmail;
    }

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getWorkPhone() {
        return worckPhone;
    }
    public ContactData withWorkPhone (String workPhone){
        this.worckPhone = workPhone;
        return this;
    }

    public String getEmail2() {
        return email2;
    }
    public ContactData withEmail2 (String email2){
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }
    public ContactData withEmail3 (String email3){
        this.email3 = email3;
        return this;
    }


    public String getHomePhone() {
        return homePhone;
    }

    public ContactData withHomePhone (String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public String getAddress() {
        return address;
    }
    public ContactData withAddress (String address){
        this.address = address;
        return this;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstname, lastname);
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;

    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
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

    public String getMobilePhone() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {return id;}


}


