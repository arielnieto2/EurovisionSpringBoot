package com.example.eurovision_cities.models;

import javax.persistence.*;

@Entity
@Table(name="cities")
public class City implements Comparable<City>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="name")
    private String cityName;

    public City() {
    }

    public String getCityName(){
        return this.cityName;
    }

    public Long getId(){
        return this.id;
    }

    @Override
    public int compareTo(City city) {
        return this.cityName.compareTo(city.getCityName());
    }
}
