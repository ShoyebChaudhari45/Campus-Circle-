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

public class AlumniMeetupFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlumniAdapter alumniAdapter;
    private List<Alumini> alumniList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alumni_meetup, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_alumni);
        recyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Initialize Alumni List
        alumniList = new ArrayList<>();
        alumniList.add(new Alumini(R.drawable.alumni_image1, "John Doe", "2020", "github.com/johndoe", "Software Engineer", "Google"));
        alumniList.add(new Alumini(R.drawable.alumni_image2, "Jane Smith", "2019", "github.com/janesmith", "Data Scientist", "Facebook"));
        alumniList.add(new Alumini(R.drawable.alumni_image3, "Mike Johnson", "2018", "github.com/mikejohnson", "AI Researcher", "OpenAI"));

        // Add three more alumni
        alumniList.add(new Alumini(R.drawable.alumni_image4, "Alice Brown", "2021", "github.com/alicebrown", "Product Manager", "Amazon"));
        alumniList.add(new Alumini(R.drawable.alumni_image5, "David Wilson", "2022", "github.com/davidwilson", "UX Designer", "Adobe"));
        alumniList.add(new Alumini(R.drawable.alumni_image6, "Sophia Lee", "2023", "github.com/sophialee", "Full Stack Developer", "Microsoft"));

        // Set Adapter
        alumniAdapter = new AlumniAdapter(alumniList);
        recyclerView.setAdapter(alumniAdapter);

        return view;
    }
}
