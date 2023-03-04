package com.driver.model;

import javax.persistence.*;


// Note: Do not write @Enumerated annotation above CountryName in this model.
@Table
@Entity
public class Country{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private CountryName countryName;
    private String code;
    @OneToOne
    @JoinColumn
    private User user;
    @ManyToMany
    @JoinColumn
    private ServiceProvider serviceProvider;

    public Country() {
    }

    public Country(int id, CountryName countryName, String code) {
        this.id = id;
        this.countryName = countryName;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
