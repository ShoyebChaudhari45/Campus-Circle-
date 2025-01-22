package com.example.alumini_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity {

    private static final String TAG = "HomePageActivity";
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Check if the user is logged in
        boolean isLoggedIn = preferences.getBoolean(KEY_IS_LOGGED_IN, false);
        Log.d(TAG, "Is user logged in: " + isLoggedIn);
        if (!isLoggedIn) {
            Intent i = new Intent(HomePageActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
            return;
        }

        setContentView(R.layout.activity_home);

        // Initialize BottomNavigationView and Toolbar
        bottomNavigationView = findViewById(R.id.bottomNav);
        toolbar = findViewById(R.id.toolbar);

        // Set up toolbar
        setSupportActionBar(toolbar);
        Log.d(TAG, "Toolbar set");

        // Set bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.homep) {
                selectedFragment = new Home_Fragment();
            } else if (item.getItemId() == R.id.eventsp) {
                selectedFragment = new Event_Fragment();
            } else if (item.getItemId() == R.id.profilep) {
                selectedFragment = new Profile_Fragment();
            } else if (item.getItemId() == R.id.jobp) {
                selectedFragment = new Job_Fragment();
            } else if (item.getItemId() == R.id.blogp) {
                selectedFragment = new Blog_Fragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });

        // Load the default fragment (e.g., Home_Fragment)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Home_Fragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu with your sidemenu resource
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sidemenu, menu); // R.menu.sidemenu contains your menu XML
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Handle each menu item selection
        if (id == R.id.logout_menu) {
            logout(); // Show logout confirmation dialog
            return true;

        } else if (id == R.id.contact_menu) {
            // Open Contact Us Activity
            startActivity(new Intent(this, ContactActivity.class));
            return true;

        } else if (id == R.id.about_menu) {
            // Open About Us Activity
            startActivity(new Intent(this, AboutActivity.class));
            return true;

        } else if (id == R.id.feedback_menu) {
            // Open Feedback Activity
            startActivity(new Intent(this, FeedbackActivity.class));
            return true;
        }
        else if (id == R.id.home_menu) {
            // Open Home Fragment
            Fragment homeFragment = new Home_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment) // Assuming R.id.fragment_container is the container for the fragment
                    .commit();
            return true;
        }
        else if (id == R.id.events_menu) {
            // Open Events Fragment
            Fragment eventFragment = new Event_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, eventFragment) // Assuming R.id.fragment_container is the container for the fragment
                    .commit();
            return true;
        }


        else if (id == R.id.settings_menu) {
            // Open Feedback Activity
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        else if (id == R.id.faq_menu) {
            // Open Feedback Activity
            startActivity(new Intent(this, FAQActivity.class));
            return true;
        }
            else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Logout functionality (you may show a confirmation dialog before logout)


    // Method to show logout confirmation dialog
    private void logout() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Cancel", (dialog, which) -> dialog.cancel())
                .setNegativeButton("Logout", (dialog, which) -> {
                    // Set the login state to false
                    preferences.edit().putBoolean(KEY_IS_LOGGED_IN, false).apply();
                    Log.d(TAG, "Login state set to false");

                    Intent i = new Intent(HomePageActivity.this, LoginActivity.class);
                    // Redirect to login screen
                    startActivity(i);
                    finish();
                })
                .create()
                .show();
    }
}
