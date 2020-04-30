package ru.itis.springboothomework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String hashPassword;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String sity;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = true)
    private String study;

    @Column(nullable = true)
    private String work;

    @Column(nullable = true)
    private String mobile;

    @Column(nullable = true)
    private String social;

    @Column(nullable = true)
    private String photoUrl;

    @Column(nullable = true, columnDefinition="VARCHAR(10000)")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Summary> summaries = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                age == user.age &&
                Objects.equals(login, user.login) &&
                Objects.equals(hashPassword, user.hashPassword) &&
                Objects.equals(country, user.country) &&
                Objects.equals(sity, user.sity) &&
                Objects.equals(name, user.name) &&
                Objects.equals(last_name, user.last_name) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(study, user.study) &&
                Objects.equals(work, user.work) &&
                Objects.equals(mobile, user.mobile) &&
                Objects.equals(social, user.social) &&
                Objects.equals(photoUrl, user.photoUrl) &&
                Objects.equals(description, user.description) &&
                Objects.equals(summaries, user.summaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, hashPassword, country, sity, name, last_name, gender, age, study, work, mobile, social, photoUrl, description, summaries);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSity() {
        return sity;
    }

    public void setSity(String sity) {
        this.sity = sity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<Summary> summaries) {
        this.summaries = summaries;
    }
}
