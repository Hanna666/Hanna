package ru.stqa.pft.mantis.model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.util.Objects;

//import com.thoughtworks.xstream.annotations.XStreamAlias;
//import com.thoughtworks.xstream.annotations.XStreamOmitField;
@Entity
@Table(name = "mantis_user_table")
public class UserData {

            @Column(name = "access_level")
    @Transient
    private int access_level;
    @Column(name = "cookie_string")
    @Transient
    private String cookie_string;
    @Column(name = "date_created")
    @Transient
    private int date_created;
    @Column(name = "email")
    private String email;
    @Column(name = "enabled")
    @Transient
    private String enabled;
    @Column(name = "failed_login_count")
    @Transient
    private String flc;
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "last_visit")
    @Transient
    private int last_visit;
    @Column(name = "login_count")
    @Transient
    private int login_count;
    @Column(name = "lost_password_request_count")
    @Transient
    private int passcount;
    @Column(name = "password")
    @Transient
    private String password;
    @Column(name = "protected")
    @Transient
    private String prot;
    @Column(name = "realname")
    @Transient
    private String realname;
    @Column(name = "username")
    private String username;

            public int getId() {
                return id;
            }

            public String getUsername() {
                return username;
            }

            public String getEmail() {
                return email;
            }

            @Override
    public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                UserData userData = (UserData) o;
                return id == userData.id &&
                                Objects.equals(username, userData.username) &&
                                Objects.equals(email, userData.email);
            }

            @Override
    public int hashCode() { return Objects.hash(id, username, email); }
}