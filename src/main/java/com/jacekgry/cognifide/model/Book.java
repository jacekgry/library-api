package com.jacekgry.cognifide.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jacekgry.cognifide.utils.JsonUtils;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private Long publishedDate;
    private String description;
    private Integer pageCount = null;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private Double averageRating;
    private List<String> authors;
    private List<String> categories;


    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public Long getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public String getIsbn() {
        return isbn;
    }

    @JsonProperty("volumeInfo")
    public void unpackNested(Map<String, Object> volumeInfo) {

        try {
            List<LinkedHashMap<String, String>> industryIdentifiers = (ArrayList<LinkedHashMap<String, String>>) volumeInfo.get("industryIdentifiers");
            this.isbn = industryIdentifiers.stream().filter(industryIdentifier -> industryIdentifier.get("type").equals("ISBN_13")).findFirst().get().get("identifier");
        } catch (Exception e) {
            this.isbn = null;
        }

        this.title = (String) volumeInfo.get("title");
        this.subtitle = (String) volumeInfo.get("subtitle");
        this.publisher = (String) volumeInfo.get("publisher");

        Date date = null;
        try {
            date = JsonUtils.tryToParseDate((String) volumeInfo.get("publishedDate"));
            this.publishedDate = date.getTime();
        } catch (Exception e) {
        }

        this.description = (String) volumeInfo.get("description");

        this.pageCount = (Integer) volumeInfo.get("pageCount");
        Map<String, String> imageLinksMap = (Map<String, String>) volumeInfo.get("imageLinks");
        this.thumbnailUrl = imageLinksMap.get("thumbnail");

        this.language = (String) volumeInfo.get("language");
        this.previewLink = (String) volumeInfo.get("previewLink");
        this.averageRating = (Double) volumeInfo.get("averageRating");
        this.authors = (List<String>) volumeInfo.get("authors");
        this.categories = (List<String>) volumeInfo.get("categories");
    }


}
