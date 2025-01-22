package com.example.alumini_project;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventDetailActivity extends AppCompatActivity {
    private TextView titleTextView, dateTextView, descriptionTextView, locationTextView, timeTextView, speakerNameTextView, speakerBioTextView, agendaTextView, contactInfoTextView;
    private ImageView speakerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        titleTextView = findViewById(R.id.detail_event_title);
        dateTextView = findViewById(R.id.detail_event_date);
        descriptionTextView = findViewById(R.id.detail_event_description);
        locationTextView = findViewById(R.id.detail_event_location);
        timeTextView = findViewById(R.id.detail_event_time);
        speakerNameTextView = findViewById(R.id.detail_event_speaker);
        speakerBioTextView = findViewById(R.id.detail_event_speaker_bio);
        agendaTextView = findViewById(R.id.detail_event_agenda);
        contactInfoTextView = findViewById(R.id.detail_event_contact_info);
        speakerImageView = findViewById(R.id.detail_event_speaker_image);

        // Get data from the intent
        String title = getIntent().getStringExtra("title");
        String date = getIntent().getStringExtra("date");
        String description = getIntent().getStringExtra("description");
        String location = getIntent().getStringExtra("location");
        String time = getIntent().getStringExtra("time");
        String speakerName = getIntent().getStringExtra("speakerName");
        int speakerImageResId = getIntent().getIntExtra("speakerImageResId", 0);
        String speakerBio = getIntent().getStringExtra("speakerBio");
        String agenda = getIntent().getStringExtra("agenda");
        String contactInfo = getIntent().getStringExtra("contactInfo");

        // Set data to the views
        titleTextView.setText(title);
        dateTextView.setText(date);
        descriptionTextView.setText(description);
        locationTextView.setText(location);
        timeTextView.setText(time);
        speakerNameTextView.setText(speakerName);
        speakerImageView.setImageResource(speakerImageResId); // Set speaker image
        speakerBioTextView.setText(speakerBio);
        agendaTextView.setText(agenda);
        contactInfoTextView.setText(contactInfo);
    }
}
