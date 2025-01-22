package com.example.alumini_project;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Blog_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private BlogAdapter adapter;
    private List<Blog> blogs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_, container, false); // Make sure the layout name matches

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the blogs list
        blogs = new ArrayList<>();
        // Initialize the blogs list
        blogs = new ArrayList<>();
        blogs.add(new Blog("John Doe",
                "This is a lengthy blog about AI. In this blog, I will discuss how AI is transforming industries, the ethical considerations we need to keep in mind, and the future of AI technology in our daily lives. I hope you find it insightful!",
                R.drawable.alumni_image2,
                "2024-10-24 10:00 AM"));

        blogs.add(new Blog("Jane Smith",
                "Here's my experience with Data Science. Data Science is a field that combines statistics, mathematics, and programming to extract insights from structured and unstructured data. In this blog, I will share some real-world applications and how you can get started!",
                R.drawable.alumni_image3,
                "2024-10-23 11:00 AM"));

        blogs.add(new Blog("Emily Johnson",
                "Web searching has evolved significantly over the years. In this blog, I will explore advanced techniques for effective web searching, the importance of understanding search algorithms, and how to find reliable information online.",
                R.drawable.alumni_image1,
                "2024-10-22 9:30 AM"));

        blogs.add(new Blog("Michael Brown",
                "As an alumni working in the tech industry, I want to share my journey into the world of machine learning. This blog outlines my experiences, the challenges I faced, and tips for those looking to enter this exciting field.",
                R.drawable.alumni_image2,
                "2024-10-21 8:15 AM"));

        blogs.add(new Blog("Sarah Davis",
                "In this blog, I'll discuss the impact of AI on society, the various applications of AI in our daily lives, and the ethical implications we must consider as technology continues to advance.",
                R.drawable.alumni_image1,
                "2024-10-20 3:45 PM"));


        // Set up the adapter
        adapter = new BlogAdapter(blogs);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

