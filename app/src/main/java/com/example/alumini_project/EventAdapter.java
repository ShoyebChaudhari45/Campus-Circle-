package com.example.alumini_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventList;
    private Context context;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);

        holder.titleTextView.setText(event.getTitle());
        holder.dateTextView.setText(event.getDate());
        holder.descriptionTextView.setText(event.getDescription());
        holder.speakerNameTextView.setText(event.getSpeakerName());
        holder.speakerImageView.setImageResource(event.getSpeakerImageResId()); // Set speaker image from drawable

        // Initially hide the speaker image
        holder.speakerImageView.setVisibility(View.GONE);

        // Handle item click to open detailed event activity and toggle visibility of speaker image
        holder.itemView.setOnClickListener(v -> {
            // Toggle visibility of the speaker image on card click
            if (holder.speakerImageView.getVisibility() == View.GONE) {
                holder.speakerImageView.setVisibility(View.VISIBLE);
            } else {
                holder.speakerImageView.setVisibility(View.GONE);
            }

            // Optionally, if you want to pass the full event details to a new activity
            Intent intent = new Intent(context, EventDetailActivity.class);
            intent.putExtra("title", event.getTitle());
            intent.putExtra("date", event.getDate());
            intent.putExtra("description", event.getDescription());
            intent.putExtra("location", event.getLocation());
            intent.putExtra("time", event.getTime());
            intent.putExtra("speakerName", event.getSpeakerName());
            intent.putExtra("speakerImageResId", event.getSpeakerImageResId()); // Pass image ID
            intent.putExtra("speakerBio", event.getSpeakerBio());
            intent.putExtra("agenda", event.getAgenda());
            intent.putExtra("contactInfo", event.getContactInfo());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView dateTextView;
        TextView descriptionTextView;
        TextView speakerNameTextView;
        ImageView speakerImageView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_event_title);
            dateTextView = itemView.findViewById(R.id.tv_event_date);
            descriptionTextView = itemView.findViewById(R.id.tv_event_description);
            speakerNameTextView = itemView.findViewById(R.id.speaker_name);
            speakerImageView = itemView.findViewById(R.id.speaker_image);
        }
    }
}
