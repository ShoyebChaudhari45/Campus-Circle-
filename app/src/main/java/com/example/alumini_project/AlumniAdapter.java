package com.example.alumini_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlumniAdapter extends RecyclerView.Adapter<AlumniAdapter.AlumniViewHolder> {

    private List<Alumini> alumniList;

    public AlumniAdapter(List<Alumini> alumniList) {
        this.alumniList = alumniList;
    }

    @NonNull
    @Override
    public AlumniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumni_item, parent, false);
        return new AlumniViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumniViewHolder holder, int position) {
        Alumini alumni = alumniList.get(position);
        holder.alumniImage.setImageResource(alumni.getImageResId());
        holder.alumniName.setText(alumni.getName());
        holder.alumniPassoutYear.setText("Passout Year: " + alumni.getPassoutYear());
        holder.alumniGithubId.setText("GitHub: " + alumni.getGithubId());
        holder.alumniJobPosition.setText(alumni.getJobPosition());
        holder.alumniCompany.setText("Company: " + alumni.getCompany());
    }

    @Override
    public int getItemCount() {
        return alumniList.size();
    }

    static class AlumniViewHolder extends RecyclerView.ViewHolder {
        ImageView alumniImage;
        TextView alumniName, alumniPassoutYear, alumniGithubId, alumniJobPosition, alumniCompany;

        public AlumniViewHolder(@NonNull View itemView) {
            super(itemView);
            alumniImage = itemView.findViewById(R.id.alumni_image);
            alumniName = itemView.findViewById(R.id.alumni_name);
            alumniPassoutYear = itemView.findViewById(R.id.alumni_passout_year);
            alumniGithubId = itemView.findViewById(R.id.alumni_github_id);
            alumniJobPosition = itemView.findViewById(R.id.alumni_job_position);
            alumniCompany = itemView.findViewById(R.id.alumni_company);
        }
    }
}
