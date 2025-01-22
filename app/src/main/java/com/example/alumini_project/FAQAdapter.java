package com.example.alumini_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {

    private List<FAQ> faqList;

    // Constructor for the adapter
    public FAQAdapter(List<FAQ> faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each FAQ item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new FAQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        FAQ faq = faqList.get(position);
        // Bind the data (question and answer) to the view holder
        holder.questionText.setText(faq.getQuestion());
        holder.answerText.setText(faq.getAnswer());
    }

    @Override
    public int getItemCount() {
        // Return the total number of FAQ items
        return faqList.size();
    }

    // ViewHolder class for binding FAQ data
    public static class FAQViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        TextView answerText;

        // Constructor for the ViewHolder
        public FAQViewHolder(View itemView) {
            super(itemView);
            // Initialize the TextViews from the layout
            questionText = itemView.findViewById(R.id.faq_question);
            answerText = itemView.findViewById(R.id.faq_answer);
        }
    }
}
