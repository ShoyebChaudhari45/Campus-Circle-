package com.example.alumini_project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
    private List<Blog> blogs;

    public BlogAdapter(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Blog blog = blogs.get(position);
        holder.alumniName.setText(blog.getAlumniName());
        holder.blogContent.setText(blog.getBlogContent());
        holder.alumniPhoto.setImageResource(blog.getAlumniPhoto());
        holder.blogDateTime.setText(blog.getDateTime());

        // Set like icon based on the blog's liked state
        if (blog.isLiked()) {
            holder.likeIcon.setColorFilter(Color.RED); // Change to your desired color for liked
        } else {
            holder.likeIcon.setColorFilter(Color.GRAY); // Default color for not liked
        }

        // Handle click event for like icon
        holder.likeIcon.setOnClickListener(v -> {
            boolean newState = !blog.isLiked(); // Toggle the liked state
            blog.setLiked(newState); // Update the state in the Blog object

            // Update icon color based on the new state
            if (newState) {
                holder.likeIcon.setColorFilter(Color.RED); // Change to desired color
            } else {
                holder.likeIcon.clearColorFilter(); // Reset to default color
            }
        });
    }


    @Override
    public int getItemCount() {
        return blogs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView likeIcon;
        TextView blogDateTime;
        TextView alumniName;
        TextView blogContent;
        ImageView alumniPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            alumniName = itemView.findViewById(R.id.alumni_name);
            blogContent = itemView.findViewById(R.id.blog_content);
            alumniPhoto = itemView.findViewById(R.id.alumni_photo);
            blogDateTime = itemView.findViewById(R.id.blog_date_time); // Ensure this is initialized
            likeIcon = itemView.findViewById(R.id.like_icon); // Ensure this is initialized
        }
    }
}
