package ru.itis.springboothomework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CovidData {

    @JsonProperty("updated")
    private long updated;

    @JsonProperty("country")
    private String country;

    @JsonProperty("countryInfo")
    private CountryInfo countryInfo;

    @JsonProperty("cases")
    private long cases;

    @JsonProperty("todayCases")
    private long todayCases;

    @JsonProperty("deaths")
    private long deaths;

    @JsonProperty("todayDeaths")
    private long todayDeaths;

    @JsonProperty("recovered")
    private long recovered;

    @JsonProperty("active")
    private long active;

    @JsonProperty("critical")
    private long critical;

    @JsonProperty("casesPerOneMillion")
    private long casesPerOneMillion;

    @JsonProperty("deathsPerOneMillion")
    private long deathsPerOneMillion;

    @JsonProperty("tests")
    private long tests;

    @JsonProperty("testsPerOneMillion")
    private long testsPerOneMillion;

    @JsonProperty("continent")
    private String continent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CovidData covidData = (CovidData) o;
        return updated == covidData.updated &&
                cases == covidData.cases &&
                todayCases == covidData.todayCases &&
                deaths == covidData.deaths &&
                todayDeaths == covidData.todayDeaths &&
                recovered == covidData.recovered &&
                active == covidData.active &&
                critical == covidData.critical &&
                casesPerOneMillion == covidData.casesPerOneMillion &&
                deathsPerOneMillion == covidData.deathsPerOneMillion &&
                tests == covidData.tests &&
                testsPerOneMillion == covidData.testsPerOneMillion &&
                Objects.equals(country, covidData.country) &&
                Objects.equals(countryInfo, covidData.countryInfo) &&
                Objects.equals(continent, covidData.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updated, country, countryInfo, cases, todayCases, deaths, todayDeaths, recovered, active, critical, casesPerOneMillion, deathsPerOneMillion, tests, testsPerOneMillion, continent);
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }

    public long getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(long todayCases) {
        this.todayCases = todayCases;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(long todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public long getCritical() {
        return critical;
    }

    public void setCritical(long critical) {
        this.critical = critical;
    }

    public long getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(long casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public long getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(long deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public long getTests() {
        return tests;
    }

    public void setTests(long tests) {
        this.tests = tests;
    }

    public long getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(long testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}

class CountryInfo {

    @JsonProperty("_id")
    private long _id;

    @JsonProperty("iso2")
    private String iso2;

    @JsonProperty("iso3")
    private String iso3;

    @JsonProperty("lat")
    private long lat;

    @JsonProperty("long")
    private int _long;

    @JsonProperty("flag")
    private String flag;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryInfo that = (CountryInfo) o;
        return _id == that._id &&
                lat == that.lat &&
                _long == that._long &&
                Objects.equals(iso2, that.iso2) &&
                Objects.equals(iso3, that.iso3) &&
                Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, iso2, iso3, lat, _long, flag);
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public int get_long() {
        return _long;
    }

    public void set_long(int _long) {
        this._long = _long;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

