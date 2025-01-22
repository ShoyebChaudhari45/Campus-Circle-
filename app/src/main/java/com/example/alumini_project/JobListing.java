package com.example.alumini_project;

public class JobListing { // Renamed the class
    private String title;
    private String company;
    private String location;
    private String applyLink;

    public JobListing(String title, String company, String location, String applyLink) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.applyLink = applyLink;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getApplyLink() {
        return applyLink;
    }
}
