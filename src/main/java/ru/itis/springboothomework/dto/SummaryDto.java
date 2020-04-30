package ru.itis.springboothomework.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class SummaryDto {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String country;

    @NotNull
    private String sity;

    private List<String> summaryCategories;

    private String customCategory;

    private String cost;

    @NotNull
    private String experience;

    @NotNull
    private String work;

    private String cost_val;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummaryDto that = (SummaryDto) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(country, that.country) &&
                Objects.equals(sity, that.sity) &&
                Objects.equals(summaryCategories, that.summaryCategories) &&
                Objects.equals(customCategory, that.customCategory) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(experience, that.experience) &&
                Objects.equals(work, that.work) &&
                Objects.equals(cost_val, that.cost_val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, country, sity, summaryCategories, customCategory, cost, experience, work, cost_val);
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

    public List<String> getSummaryCategories() {
        return summaryCategories;
    }

    public void setSummaryCategories(List<String> summaryCategories) {
        this.summaryCategories = summaryCategories;
    }

    public String getCustomCategory() {
        return customCategory;
    }

    public void setCustomCategory(String customCategory) {
        this.customCategory = customCategory;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public String getCost_val() {
        return cost_val;
    }

    public void setCost_val(String cost_val) {
        this.cost_val = cost_val;
    }
}
