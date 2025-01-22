package com.example.alumini_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

public class Profile_Fragment extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private TextView nameTextView, emailTextView, phoneTextView, userTypeTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        // Initialize Firebase Firestore and Firebase Auth
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // Initialize TextViews
        nameTextView = view.findViewById(R.id.nameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);
        userTypeTextView = view.findViewById(R.id.userTypeTextView);  // Added userType TextView

        // Fetch the current user's email
        String currentUserEmail = auth.getCurrentUser() != null ? auth.getCurrentUser().getEmail() : null;

        if (currentUserEmail != null) {
            // Fetch the user's data from Firestore using the email
            db.collection("user")
                    .whereEqualTo("email", currentUserEmail)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                // Get the first document (should only be one, based on email)
                                DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                                UserModel user = document.toObject(UserModel.class);
                                if (user != null) {
                                    // Set user data to TextViews
                                    nameTextView.setText("Name: " + user.getName());
                                    emailTextView.setText("Email: " + user.getEmail());
                                    phoneTextView.setText("Phone: " + user.getPhone());
                                    userTypeTextView.setText("User Type: " + user.getUserType());  // Show userType
                                } else {
                                    Toast.makeText(getActivity(), "User data not found", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error fetching user data", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Handle the case when the user is not logged in

        }

        return view;
    }
}
