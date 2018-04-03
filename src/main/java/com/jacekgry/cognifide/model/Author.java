package com.jacekgry.cognifide.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {

    @JsonProperty("author")
    private String name;
    private Double averageRating;
    @JsonIgnore
    private int numOfRatedBooks = 0;
    @JsonIgnore
    private Double totalRating = 0.0;

    public Author(String authorName, Double firstRate) {
        this.name = authorName;
        this.averageRating = firstRate;
        this.numOfRatedBooks = 1;
    }

    public String getName() {
        return name;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public int getNumOfRatedBooks() {
        return numOfRatedBooks;
    }

    public void addRatedBook(Double rate) {
        this.numOfRatedBooks++;
        this.totalRating += rate;
        this.averageRating = this.totalRating / this.numOfRatedBooks;
    }


}
