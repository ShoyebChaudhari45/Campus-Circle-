package com.example.alumini_project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Event_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_, container, false);
        recyclerView = view.findViewById(R.id.rv_event_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create a list of dummy events with speaker images and additional information
        eventList = new ArrayList<>();

        eventList.add(new Event("Alumni Meetup", "2024-10-25", "Join us for an alumni meetup at the college campus.",
                "College Campus", "10:00 AM", "Prof. John Doe", R.drawable.alumni_image4, "Networking, Discussions",
                "contact@college.edu", "Prof. John Doe is a seasoned academician with years of experience in higher education."));

        eventList.add(new Event("Tech Talk", "2024-11-01", "A talk on the latest trends in technology by industry experts.",
                "Main Auditorium", "1:00 PM", "Tech Industry Experts", R.drawable.alumni_image1, "Introduction to new tech",
                "info@techconference.com", "Tech Industry Experts are leading professionals in the field of technology, specializing in AI and Cloud Computing."));

        eventList.add(new Event("Annual Reunion", "2024-12-15", "Come celebrate the annual reunion with your fellow alumni.",
                "Event Hall", "5:00 PM", "Alumni Office", R.drawable.alumni_image6, "Reunion dinner, speeches",
                "alumni@college.edu", "The Alumni Office works to foster connections among graduates through events and outreach programs."));

        eventList.add(new Event("AI & ML Workshop", "2024-10-30", "A workshop on Artificial Intelligence and Machine Learning.",
                "Lab 202", "2:00 PM", "Dr. Jane Smith", R.drawable.alumni_image2, "Hands-on session on AI and ML",
                "workshops@college.edu", "Dr. Jane Smith is an expert in AI and ML, with a focus on neural networks and deep learning."));

        eventList.add(new Event("Career Guidance Webinar", "2024-11-10", "An interactive session on career guidance for recent graduates.",
                "Online", "11:00 AM", "Career Counseling Team", R.drawable.alumni_image3, "Career advice and Q&A",
                "career@college.edu", "The Career Counseling Team provides personalized career advice and helps graduates navigate their professional journey."));

        eventList.add(new Event("Startup Pitch Day", "2024-11-20", "Pitch your startup ideas to venture capitalists and get feedback.",
                "Startup Hub", "9:00 AM", "VC Panel", R.drawable.alumni_image5, "Startup pitches, feedback session",
                "startup@college.edu", "The VC Panel consists of seasoned investors with a passion for supporting innovative startups."));

        eventList.add(new Event("Hackathon 2024", "2024-12-01", "Participate in a 24-hour coding hackathon and solve real-world problems.",
                "Lab 101", "9:00 AM", "Organizing Team", R.drawable.alumni_image4, "Coding marathon",
                "hackathon@college.edu", "The Hackathon Organizing Team ensures a smooth experience for participants and sponsors, promoting teamwork and innovation."));

        eventList.add(new Event("Networking Evening", "2024-12-10", "A casual evening to network with alumni from different industries.",
                "Networking Lounge", "6:00 PM", "Alumni Network", R.drawable.alumni_image6, "Networking, refreshments",
                "network@college.edu", "Alumni Network facilitates connections across different fields to support professional growth and opportunities."));

        eventList.add(new Event("New Year Celebration", "2024-12-31", "Ring in the new year with a fun-filled evening at the campus.",
                "Main Hall", "8:00 PM", "Event Organizers", R.drawable.alumni_image2, "Dinner, games, and celebration",
                "celebrate@college.edu", "Event Organizers ensure a joyful celebration and a memorable experience for everyone."));

        // Set the adapter with the updated constructor that accepts context
        eventAdapter = new EventAdapter(getContext(), eventList);
        recyclerView.setAdapter(eventAdapter);

        return view;
    }
}
