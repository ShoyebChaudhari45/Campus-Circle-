package com.example.alumini_project;

public class Alumini {
    private int imageResId;
    private String name;
    private String passoutYear;
    private String githubId;
    private String jobPosition;
    private String company;

    public Alumini(int imageResId, String name, String passoutYear, String githubId, String jobPosition, String company) {
        this.imageResId = imageResId;
        this.name = name;
        this.passoutYear = passoutYear;
        this.githubId = githubId;
        this.jobPosition = jobPosition;
        this.company = company;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public String getPassoutYear() {
        return passoutYear;
    }

    public String getGithubId() {
        return githubId;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public String getCompany() {
        return company;
    }
}
