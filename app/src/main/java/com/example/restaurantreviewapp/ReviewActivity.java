package com.example.restaurantreviewapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        TextView tvTitle = findViewById(R.id.tvReviewTitle);
        ImageView ivReviewer = findViewById(R.id.ivReviewer);
        RatingBar rb = findViewById(R.id.reviewRatingBar);
        Button btnBack = findViewById(R.id.btnBack);

        String name = getIntent().getStringExtra("RESTAURANT_NAME");
        float rating = getIntent().getFloatExtra("RATING", 0f);

        rb.setRating(rating);

        String feedback;
        int imageResId;

        if (rating >= 4.0) {
            feedback = "It's great!";
            imageResId = R.drawable.reuven;
        } else if (rating >= 2.0) {
            feedback = "It's mid.";
            imageResId = R.drawable.michael;
        } else {
            feedback = "It's dogwater.";
            imageResId = R.drawable.amir;
        }

        tvTitle.setText(name + ": " + feedback);
        ivReviewer.setImageResource(imageResId);

        btnBack.setOnClickListener(v -> finish());
    }
}