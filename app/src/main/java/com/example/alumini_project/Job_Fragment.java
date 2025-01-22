package com.example.alumini_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Job_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private List<JobListing> jobList; // Updated to use JobListing

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_, container, false);

        recyclerView = view.findViewById(R.id.rv_job_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy job data
        jobList = new ArrayList<>();
        jobList.add(new JobListing("Android Developer", "Tech Solutions", "Remote", "https://example.com/apply/android"));
        jobList.add(new JobListing("Web Developer", "Web Innovators", "New York, NY", "https://example.com/apply/web"));
        jobList.add(new JobListing("Intern - Software Engineer", "Startup XYZ", "San Francisco, CA", "https://example.com/apply/intern"));
        jobList.add(new JobListing("Quality Engineer", "Quality Corp", "Austin, TX", "https://example.com/apply/quality"));

        jobAdapter = new JobAdapter(getContext(), jobList);
        recyclerView.setAdapter(jobAdapter);

        return view;
    }
}
