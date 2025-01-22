package com.example.alumini_project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List; // Import List

public class FAQActivity extends AppCompatActivity {

    private RecyclerView faqRecyclerView;
    private FAQAdapter faqAdapter;
    private List<FAQ> faqList; // Declare List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        faqRecyclerView = findViewById(R.id.faqRecyclerView);
        faqRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Populate FAQ list with data
        faqList = new ArrayList<>();
        faqList.add(new FAQ("What is Campus Circle?", "Campus Circle is an app that connects alumni and students for networking, mentorship, and more."));
        faqList.add(new FAQ("How do I register?", "You can register by clicking on the 'Sign Up' button on the home page and entering your details."));
        faqList.add(new FAQ("How can I request mentorship?", "You can request mentorship by filling out the 'Mentorship' form available in the menu."));
        faqList.add(new FAQ("How can I provide feedback?", "You can provide feedback by navigating to the 'Feedback' section in the menu."));
        faqList.add(new FAQ("How can I contact support?", "You can contact support by clicking on the 'Contact Us' section in the app menu."));

        // Set the adapter
        faqAdapter = new FAQAdapter(faqList);
        faqRecyclerView.setAdapter(faqAdapter);
    }
}
