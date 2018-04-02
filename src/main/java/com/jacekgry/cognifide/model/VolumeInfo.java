package com.jacekgry.cognifide.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {

    private String infoLink;

    private String printType;

    private String allowAnonLogging;

    public String getPublisher() {
        return publisher;
    }

    private String publisher;

    private String[] authors;

    private String canonicalVolumeLink;

    public String getTitle() {
        return title;
    }

    private String title;

    private String previewLink;

    private String description;

    private ImageLinks imageLinks;

    private String subtitle;

    public String getSubtitle() {
        return subtitle;
    }

    private String contentVersion;

    private String[] categories;

    private String language;

    private String publishedDate;

    private IndustryIdentifiers[] industryIdentifiers;

    private String maturityRating;


    public IndustryIdentifiers[] getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(IndustryIdentifiers[] industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }
}
