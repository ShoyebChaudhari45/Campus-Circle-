package com.example.alumini_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Register_Page extends AppCompatActivity {

    EditText et_name, et_email, et_phone, et_password;
    Button btn_register;
    Spinner userTypeSpinner;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        userTypeSpinner = findViewById(R.id.spinner_user_type);
        btn_register = findViewById(R.id.btn_register);
        db = FirebaseFirestore.getInstance();
        ImageView passwordToggle = findViewById(R.id.passwordToggle);
        boolean[] isPasswordVisible = {false};

        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    // Hide the password
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordToggle.setImageResource(R.drawable.ic_visibility_off);  // Set to visibility off icon
                } else {
                    // Show the password
                    et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordToggle.setImageResource(R.drawable.ic_visibility);  // Set to visibility icon
                }
                isPasswordVisible[0] = !isPasswordVisible[0];
                et_password.setSelection(et_password.getText().length());  // Move cursor to the end
            }
        });


        // Set up Spinner with user types
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_type_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Other fields initialization
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                // Handle spinner value safely
                if (userTypeSpinner.getSelectedItem() == null) {
                    Toast.makeText(Register_Page.this, "Please select a user type", Toast.LENGTH_SHORT).show();
                    return;
                }

                String userType = userTypeSpinner.getSelectedItem().toString();

                // Proceed with the registration process
                if (!validateInput(name, email, phone, password)) {
                    return; // Stop further execution if validation fails
                }

                checkIfEmailOrPhoneExists(email, phone, new FirestoreCallback() {
                    @Override
                    public void onCallback(boolean emailExists, boolean phoneExists) {
                        if (emailExists) {
                            et_email.setError("Email already exists");
                            et_email.requestFocus();
                        } else if (phoneExists) {
                            et_phone.setError("Phone number already exists");
                            et_phone.requestFocus();
                        } else {
                            // Proceed with registration
                            registerUser(name, email, phone, password, userType);
                        }
                    }
                });
            }
        });


    }

    // Function to validate inputs
    private boolean validateInput(String name, String email, String phone, String password) {
        // Name validation
        if (name.isEmpty()) {
            et_name.setError("Name is required");
            et_name.requestFocus();
            return false;
        }

        // Email validation (checks for "@" and a general format)
        if (email.isEmpty()) {
            et_email.setError("Email is required");
            et_email.requestFocus();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Please enter a valid email");
            et_email.requestFocus();
            return false;
        }

        // Phone number validation (checks if the length is exactly 10 digits)
        if (phone.isEmpty()) {
            et_phone.setError("Phone number is required");
            et_phone.requestFocus();
            return false;
        }
        if (phone.length() != 10) {
            et_phone.setError("Phone number must be 10 digits");
            et_phone.requestFocus();
            return false;
        }

        // Password validation (checks if the password is at least 8 characters long)
        if (password.isEmpty()) {
            et_password.setError("Password is required");
            et_password.requestFocus();
            return false;
        }
        if (password.length() < 8) {
            et_password.setError("Password must be at least 8 characters long");
            et_password.requestFocus();
            return false;
        }

        // Spinner validation (checks if user type is selected)
        String userType = userTypeSpinner.getSelectedItem().toString(); // Get the selected item
        if (userType.equals("Select User Type")) {
            // Assuming the first item is "Select User Type"
            Toast.makeText(this, "Please select a valid user type", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    // Function to check if email or phone exists in Firestore
    private void checkIfEmailOrPhoneExists(String email, String phone, FirestoreCallback callback) {
        db.collection("user")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean emailExists = task.isSuccessful() && !task.getResult().isEmpty();
                        if (emailExists) {
                            callback.onCallback(true, false);
                        } else {
                            // If email does not exist, check the phone number
                            db.collection("user")
                                    .whereEqualTo("phone", phone)
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            boolean phoneExists = task.isSuccessful() && !task.getResult().isEmpty();
                                            callback.onCallback(false, phoneExists);
                                        }
                                    });
                        }
                    }
                });
    }

    // Function to register user
    private void registerUser(String name, String email, String phone, String password, String userType) {
        UserModel userModel = new UserModel(name, email, phone, password, userType);

        db.collection("user").add(userModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Register_Page.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register_Page.this, HomePageActivity.class);
                    startActivity(intent);
                    finish(); // Optionally close the registration page
                } else {
                    Toast.makeText(Register_Page.this, "Error during registration", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Callback interface for Firestore results
    private interface FirestoreCallback {
        void onCallback(boolean emailExists, boolean phoneExists);
    }
}