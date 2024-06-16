package com.bharat.mall;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import android.widget.Toast;
import android.view.LayoutInflater;






import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


import androidx.viewpager2.widget.ViewPager2;

import android.widget.Button;

import com.bharat.mall.MainActivity;


public class OnboardingActivity extends AppCompatActivity {





    private ViewPager2 viewPager;
    private Button buttonNext;
    private int[] onboardingImageIds = {R.drawable.onboarding_image_1, R.drawable.onboarding_image_2, R.drawable.onboarding_image_3};
    // private String[] onboardingTexts = {"Onboarding screen 1", "Onboarding screen 2", "Onboarding screen 3"};
    private int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        // Activity has not yet been opened, so proceed with launching it



        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        buttonNext = findViewById(R.id.buttonNext);

        OnboardingPagerAdapter adapter = new OnboardingPagerAdapter(onboardingImageIds);//
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentItem = position;
                updateButtonNext();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentItem < onboardingImageIds.length - 1) {
                    currentItem++;
                    viewPager.setCurrentItem(currentItem);
                } else {
                    finishOnboarding();
                }
            }
        });

        updateButtonNext();
    }

    private void updateButtonNext() {
        if (currentItem == onboardingImageIds.length - 1) {
            buttonNext.setText("Finish");
        } else {
            buttonNext.setText("Next");
        }
    }

    private void finishOnboarding() {

        // You can add any actions you want to perform after the user finishes the onboarding
        Toast.makeText(this, "Onboarding finished", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent1=new Intent(OnboardingActivity.this, MainActivity.class);
        startActivity(intent1);
    }

    private static class OnboardingPagerAdapter extends RecyclerView.Adapter<OnboardingViewHolder> {

        private int[] onboardingImageIds;
        // private String[] onboardingTexts;

        public OnboardingPagerAdapter(int[] onboardingImageIds) {
            this.onboardingImageIds = onboardingImageIds;
            // this.onboardingTexts = onboardingTexts;
        }

        @NonNull
        @Override
        public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onboarding, parent, false);
            return new OnboardingViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
            holder.imageView.setImageResource(onboardingImageIds[position]);
            // holder.textView.setText(onboardingTexts[position]);
        }

        @Override
        public int getItemCount() {
            return onboardingImageIds.length;
        }
    }

    private static class OnboardingViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        // private TextView textView;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            // textView = itemView.findViewById(R.id.textView);
        }
    }



}
