package com.example.alumini_project;


public class Blog {
    private String alumniName;
    private String blogContent;
    private int alumniPhoto; // Resource ID for the photo
    private String dateTime; // New field for date and time
    private boolean isLiked; // New field for liked state

    public Blog(String alumniName, String blogContent, int alumniPhoto, String dateTime) {
        this.alumniName = alumniName;
        this.blogContent = blogContent;
        this.alumniPhoto = alumniPhoto;
        this.dateTime = dateTime;
        this.isLiked = false; // Default state
    }

    public String getAlumniName() {
        return alumniName;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public int getAlumniPhoto() {
        return alumniPhoto;
    }

    public String getDateTime() {
        return dateTime; // New getter for date and time
    }

    public boolean isLiked() {
        return isLiked; // Getter for liked state
    }

    public void setLiked(boolean liked) {
        isLiked = liked; // Setter for liked state
    }
}
