package com.example.alumini_project;

public class Event {
    private String title;
    private String date;
    private String description;
    private String location;
    private String time;
    private String speakerName;
    private int speakerImageResId;
    private String agenda;
    private String contactInfo;
    private String speakerBio; // Add speakerBio field

    // Constructor with speakerBio
    public Event(String title, String date, String description, String location, String time, String speakerName,
                 int speakerImageResId, String agenda, String contactInfo, String speakerBio) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.location = location;
        this.time = time;
        this.speakerName = speakerName;
        this.speakerImageResId = speakerImageResId;
        this.agenda = agenda;
        this.contactInfo = contactInfo;
        this.speakerBio = speakerBio; // Initialize speakerBio
    }

    // Getters and Setters for all fields
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public int getSpeakerImageResId() {
        return speakerImageResId;
    }

    public String getAgenda() {
        return agenda;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getSpeakerBio() {  // Add this getter method
        return speakerBio;
    }
}
