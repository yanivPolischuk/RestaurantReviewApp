package com.example.restaurantreviewapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = findViewById(R.id.etRestaurantName);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final Button btnPublish = findViewById(R.id.btnPublish);
        final TextView tvTitle = findViewById(R.id.textViewTitle);

        // Initialize the ImageViews
        final ImageView imgReuven = findViewById(R.id.imageViewReuven);
        final ImageView imgMichael = findViewById(R.id.imageViewMichael);
        final ImageView imgAmir = findViewById(R.id.imageViewAmir);

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                float rating = ratingBar.getRating();

                if (name.isEmpty() || rating == 0) {
                    Toast.makeText(MainActivity.this, "Please enter a name and a rating!", Toast.LENGTH_SHORT).show();
                    return;
                }

                imgReuven.setVisibility(View.GONE);
                imgMichael.setVisibility(View.GONE);
                imgAmir.setVisibility(View.GONE);

                String feedback;
                ImageView reviewerToFlash;

                if (rating >= 4.0) {
                    feedback = "It's great!";
                    reviewerToFlash = findViewById(R.id.imageViewReuven);
                } else if (rating >= 2.0) {
                    feedback = "It's mid.";
                    reviewerToFlash = findViewById(R.id.imageViewMichael);
                } else {
                    feedback = "It's dogwater.";
                    reviewerToFlash = findViewById(R.id.imageViewAmir);
                }

                tvTitle.setText(name + ": " + feedback);
                triggerFadeAnimation(tvTitle);
                triggerFadeAnimation(reviewerToFlash); // Flash the specific reviewer

                Toast.makeText(MainActivity.this, "Review Published!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //בדומה לפרוייקט עם לוי
    private void triggerFadeAnimation(final View view) {
        view.setVisibility(View.VISIBLE);
        final AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(200);
        fadeIn.setFillAfter(true);
        final AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(200);
        fadeOut.setFillAfter(true);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
        });
        view.startAnimation(fadeIn);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.startAnimation(fadeOut);
            }
        }, 500);
    }
}