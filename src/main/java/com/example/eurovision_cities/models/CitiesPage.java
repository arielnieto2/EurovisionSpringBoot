package com.example.eurovision_cities.models;

import java.util.List;

public class CitiesPage {
    private List<City> content;
    private int totalPages;
    private long totalElements;

    public CitiesPage(List<City> content, int totalPages, long totalElements){
        this.content =content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public CitiesPage() {
    }

    @Override
    public String toString() {
        return "CitiesPage{" +
                "content=" + content +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                '}';
    }
}
