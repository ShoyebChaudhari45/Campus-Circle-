package com.example.alumini_project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {
    private ImageView upcomingEventsCard, jobStatisticsCard, alumniMeetupCard, contributeCard;
    private RecyclerView recyclerViewNews;
    private NewsAdapter newsAdapter;
    private List<News> newsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        // Initialize RecyclerView
        recyclerViewNews = view.findViewById(R.id.recycler_view_news); // Ensure this ID matches your layout
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create dummy data for news
        newsList = new ArrayList<>();
        newsList.add(new News("Alumni Success Story", "John Doe got a promotion at Google.", "22nd October 2024"));
        newsList.add(new News("Event Update", "Don't miss the upcoming alumni meetup!", "20th October 2024"));
        newsList.add(new News("Alumni Achievement", "Jane Smith became the CTO of a leading tech company.", "18th October 2024"));

        // Initialize adapter after newsList is created
        newsAdapter = new NewsAdapter(newsList, getContext());
        recyclerViewNews.setAdapter(newsAdapter);

        // Initialize cards
        upcomingEventsCard = view.findViewById(R.id.events_image);
        jobStatisticsCard = view.findViewById(R.id.jobs_image);
        alumniMeetupCard = view.findViewById(R.id.meetup_image);
        contributeCard = view.findViewById(R.id.contribute_image);

        // Set click listener for Upcoming Events
        upcomingEventsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to EventsFragment
                Fragment eventsFragment = new Event_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, eventsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Set click listener for Alumni Meetup
        alumniMeetupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AlumniMeetupFragment
                Fragment alumniMeetupFragment = new AlumniMeetupFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, alumniMeetupFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Set click listener for Contribute
        contributeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment contributeFragment = new Contribute_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, contributeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Set click listener for Job Statistics
        jobStatisticsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to JobStatisticsFragment
                Fragment jobStatisticsFragment = new JobStatisticsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, jobStatisticsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
