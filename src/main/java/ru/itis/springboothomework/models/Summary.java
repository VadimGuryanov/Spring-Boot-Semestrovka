package ru.itis.springboothomework.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "summaries")
public class Summary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition="VARCHAR(10000)")
    private String description;

    @Column(nullable = true)
    private String country;

    @Column(nullable = true)
    private String sity;


    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "summaries_categories",
            joinColumns = @JoinColumn(name = "summary_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> summaryCategories;

    @Column(nullable = true)
    private String cost;

    @Column(nullable = false, columnDefinition="VARCHAR(1000)")
    private String experience;

    @Column(nullable = false)
    private String work;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<Category> getSummaryCategories() {
        return summaryCategories;
    }

    public void setSummaryCategories(Set<Category> summaryCategories) {
        this.summaryCategories = summaryCategories;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary = (Summary) o;
        return id == summary.id &&
                Objects.equals(user, summary.user) &&
                Objects.equals(title, summary.title) &&
                Objects.equals(description, summary.description) &&
                Objects.equals(country, summary.country) &&
                Objects.equals(sity, summary.sity) &&
                Objects.equals(summaryCategories, summary.summaryCategories) &&
                Objects.equals(cost, summary.cost) &&
                Objects.equals(experience, summary.experience) &&
                Objects.equals(work, summary.work);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, user, title, description, country, sity, summaryCategories, cost, experience, work);
//    }
}
