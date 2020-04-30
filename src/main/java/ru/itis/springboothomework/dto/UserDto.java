package ru.itis.springboothomework.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDto {

    @NotNull
    @Length(min = 8, max = 30)
    private String login;

    @NotNull
    @Length(min = 8, max = 20)
    private String password;

    @NotNull
    @Length(min = 8, max = 20)
    private String passwordRepeat;

    @NotNull
    @Length(min = 1, max = 30)
    private String name;

    @NotNull
    @Length(min = 1, max = 30)
    private String lastName;

    @NotNull
    private String country;

    @NotNull
    private String sity;

    @NotNull
    private String gender;

    @NotNull
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(login, userDto.login) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(passwordRepeat, userDto.passwordRepeat) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(country, userDto.country) &&
                Objects.equals(sity, userDto.sity) &&
                Objects.equals(gender, userDto.gender) &&
                Objects.equals(age, userDto.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, passwordRepeat, name, lastName, country, sity, gender, age);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
