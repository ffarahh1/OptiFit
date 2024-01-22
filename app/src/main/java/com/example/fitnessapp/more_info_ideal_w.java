package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class more_info_ideal_w extends AppCompatActivity {
    TextView tv_e; //Extra_info_id;
    EditText te;
    Button btn;
    String StringnumberOfHours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_ideal_w);

        tv_e=findViewById(R.id.Extra_info_id);
        te= findViewById(R.id.hoursOfExc_text);
        btn = findViewById(R.id.hoursOfExc_btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringnumberOfHours = String.valueOf(te.getText());
                if (StringnumberOfHours.equals("")) {
                    Toast.makeText(more_info_ideal_w.this, "Enter Number of Hours", Toast.LENGTH_SHORT).show();
                } else {

                    double numberOfHours = Double.valueOf(StringnumberOfHours);

                    String H = getIntent().getStringExtra("H");
                    String W = getIntent().getStringExtra("W");
                    String AGE = getIntent().getStringExtra("AGE");
                    String Gender = getIntent().getStringExtra("Gender");
                    String ActivityLevel = getIntent().getStringExtra("ACTIVITY");
                    String Moretext = getIntent().getStringExtra("MoreInfo");

                    System.out.println(H + W + AGE + Gender + ActivityLevel);


                    double IH = Float.parseFloat(H);
                    double IW = Float.parseFloat(W);
                    double IAGE = Float.parseFloat(AGE);
                    double IActivityLevel = Float.parseFloat(ActivityLevel);

                    double[] cal_time = CalorieCalculator(IH, IW, IAGE, IActivityLevel, Gender);
                    double H_m = IH;
                    if (IH > 3) {
                        H_m = IH / 100;
                    }
                    double days = fuzzylogic_calculator.calculateDays(Gender, IH, (IW * (H_m * H_m)), numberOfHours);

                    tv_e.setText("\nRecommended daily calorie intake: " +
                            (int) cal_time[0] + " calories \n\n" +
                            "Time to achieve ideal weight: " +
                            (int) days +
                            " days \n\n" + Moretext);

                }
            }});
    }




        public static double[] CalorieCalculator(double height,double weight,double age, double activityLevel ,String gender ) {

            double bmr; //basic metabolic rate
            if (gender.equals("male")) {
                bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
            } else {
                bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
            }
            double dailyCalorieIntake = bmr * activityLevel;
            //System.out.println("Recommended daily calorie intake: " + dailyCalorieIntake + " calories");
            double idealWeight = 22 * Math.pow(height / 100, 2); // using BMI 22 as ideal
            double weightDifference = weight - idealWeight;
            double timeToIdealWeight = weightDifference * 7700 / dailyCalorieIntake; // 7700 calories to lose 1 kg
            //System.out.println("Time to achieve ideal weight: " + timeToIdealWeight + " days");

            return new double[]{dailyCalorieIntake, timeToIdealWeight};
        }


}