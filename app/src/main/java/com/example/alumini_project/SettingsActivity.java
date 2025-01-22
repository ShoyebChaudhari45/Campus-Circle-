package com.example.alumini_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    private Switch notificationSwitch;
    private Spinner languageSpinner;
    private Switch themeSwitch;
    private Button helpButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notificationSwitch = findViewById(R.id.notificationSwitch);
        languageSpinner = findViewById(R.id.languageSpinner);
        themeSwitch = findViewById(R.id.themeSwitch);
        helpButton = findViewById(R.id.helpButton);

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);

        // Load previously saved settings
        boolean isNotificationsEnabled = sharedPreferences.getBoolean("notificationsEnabled", true);
        notificationSwitch.setChecked(isNotificationsEnabled);

        // Load theme preference
        boolean isDarkMode = sharedPreferences.getBoolean("darkMode", false);
        themeSwitch.setChecked(isDarkMode);
        setAppTheme(isDarkMode);  // Apply the theme at the start

        // Set listeners to save preferences
        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notificationsEnabled", isChecked);
            editor.apply();
        });

        // Theme switch listener
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("darkMode", isChecked);
            editor.apply();
            setAppTheme(isChecked);  // Change theme immediately
        });

        // Language selection logic (you can populate this spinner with languages)
        // languageSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, languages));

        // Help button logic
        helpButton.setOnClickListener(v -> {
            // Open help section or redirect to FAQ or help activity
            // For example, open FAQActivity
            startActivity(new Intent(SettingsActivity.this, FAQActivity.class));
        });
    }

    // Method to apply the theme based on user selection
    private void setAppTheme(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
