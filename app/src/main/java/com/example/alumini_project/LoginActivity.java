package com.example.alumini_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    EditText et_login_email, et_login_password;
    Button btn_login;
    FirebaseFirestore db;
    SharedPreferences sharedPreferences;

    private static final String TAG = "LoginActivity";
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Check if user is already logged in
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
        Log.d(TAG, "Is user logged in: " + isLoggedIn);
        if (isLoggedIn) {
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
            return; // Exit the onCreate method if already logged in
        }

        et_login_email = findViewById(R.id.et_login_email);
        et_login_password = findViewById(R.id.et_login_password);
        btn_login = findViewById(R.id.btn_login);
        db = FirebaseFirestore.getInstance();

        ImageView tvSignUp = findViewById(R.id.tv_sign_up);
        tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Register_Page.class);
            startActivity(intent);
        });

        btn_login.setOnClickListener(view -> {
            String email = et_login_email.getText().toString();
            String password = et_login_password.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Query Firestore for a user with the provided email
            db.collection("user")
                    .whereEqualTo("email", email)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            DocumentSnapshot document = task.getResult().getDocuments().get(0);
                            String storedPassword = document.getString("password");

                            // Check if the entered password matches the stored password
                            if (storedPassword != null && storedPassword.equals(password)) {
                                // Save login state
                                sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply();
                                Log.d(TAG, "Login state set to true");

                                // Proceed to the next activity
                                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, HomePageActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Email not registered", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
