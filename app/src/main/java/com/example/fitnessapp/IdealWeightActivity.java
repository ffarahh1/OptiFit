package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class IdealWeightActivity extends AppCompatActivity {
    Button btn, btnwmn, moreInfo;
    TextView tv1, tv2, sbtxt,calstext;
    EditText height, weight, AGE;
    double activityLevel = 1.55;
    double BMR;
    double requiredCals;
    String GlobalGender ="";

    String seektext = "Moderately Active (exercise 3-5 days/week)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_weight);
        btnwmn = findViewById(R.id.Ideal_weight_btn_wmn);
        btn = findViewById(R.id.Ideal_weight_btn);
        moreInfo = findViewById(R.id.btn_more_info);
        height = findViewById(R.id.TE_ID_H);
        weight = findViewById(R.id.TE_ID_W);
        AGE = findViewById(R.id.TE_ID_age);
        tv1 = findViewById(R.id.Ideal_weight_tv);
        tv2 = findViewById(R.id.BMI_val);
        calstext =findViewById(R.id.cals_text);
        sbtxt = findViewById(R.id.seekbar_text);
        SeekBar activitySeekbar = findViewById(R.id.activity_level_seekbar);
        activitySeekbar.setProgress(35);


        activitySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double adjustedProgress = progress + 120; // Adjust the progress value to be in the range 120-190
                activityLevel = adjustedProgress / 100;
                System.out.println(activityLevel);

                if (activityLevel < 1.3) {
                    seektext = "Sedentary (work a desk job)";
                } else if (activityLevel < 1.50) {
                    seektext = "Lightly Active (exercise 1-3 days/week)";
                } else if (activityLevel < 1.70) {
                    seektext = "Moderately Active (exercise 3-5 days/week)";
                } else if (activityLevel < 1.8) {
                    seektext = "Very Active (exercise 6-7 days/week)";
                } else if (activityLevel > 1.8) {
                    seektext = " Extremely Active (strenuous training 2x/day)";
                }
                sbtxt.setText(seektext);
                if (GlobalGender.equals("male")){
                    String height_text = height.getText().toString();
                    String weight_text = weight.getText().toString();
                    int age = Integer.valueOf(AGE.getText().toString());
                    if (height_text.length() == 0 | weight_text.length() == 0) {
                        Toast.makeText(IdealWeightActivity.this, "Enter your Hight and Weight", Toast.LENGTH_SHORT).show();
                    } else {
                        double w = Float.parseFloat(weight_text);
                        double h = Float.parseFloat(height_text);
                        if (h > 3) {
                            h = h / 100;
                        }
                        double IdealWeight = 22 * (h * h);
                        tv2.setText("    |||   BMI = " + String.valueOf(w / (h * h)).substring(0, 4));
                        tv1.setText("Ideal Weight = " + String.valueOf(IdealWeight).substring(0, 4) + " ~Kg");
                        BMR = (10 * w) + (6.25 * h * 100) - (5 * age) + 5;
                        double AMR = BMR * activityLevel;
                        if (IdealWeight > w) {
                            requiredCals = AMR + (AMR * (5 / 100));
                        } else if (IdealWeight < w) {
                            requiredCals = AMR - (AMR * (10 / 100));
                        } else {
                            requiredCals = AMR;
                        }
                        String mealCalories = distributeCalories(requiredCals);
                        GlobalGender = "male";
                        calstext.setText(mealCalories);

                    }
                }else if (GlobalGender.equals("female")){
                    String height_text = height.getText().toString();
                    String weight_text = weight.getText().toString();
                    int age = Integer.valueOf(AGE.getText().toString());
                    if (height_text.length() == 0 | weight_text.length() == 0) {
                        Toast.makeText(IdealWeightActivity.this, "Enter your Hight and Weight", Toast.LENGTH_SHORT).show();

                    } else {
                        double w = Float.parseFloat(weight_text);
                        double h = Float.parseFloat(height_text);
                        if (h > 3) {
                            h = h / 100;
                        }

                        tv2.setText("    |||   BMI = " + String.valueOf(w / (h * h)).substring(0, 4));
                        h = h - 0.1;
                        double IdealWeight = 22 * (h * h);
                        tv1.setText("Ideal Weight = " + String.valueOf(IdealWeight).substring(0, 4) + " ~Kg");
                        BMR = (10 * w) + (6.25 * (h + 0.1) * 100) - (5 * age) - 161;
                        double AMR = BMR * activityLevel;
                        if (IdealWeight > w) {
                            requiredCals = AMR + (AMR * (5 / 100));
                        } else if (IdealWeight < w) {
                            requiredCals = AMR - (AMR * (10 / 100));
                        } else {
                            requiredCals = AMR;
                        }
                        String mealCalories = distributeCalories(requiredCals);
                        GlobalGender="female";
                        calstext.setText(mealCalories);


                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height_text = height.getText().toString();
                String weight_text = weight.getText().toString();
                int age = Integer.valueOf(AGE.getText().toString());
                if (height_text.length() == 0 | weight_text.length() == 0) {
                    Toast.makeText(IdealWeightActivity.this, "Enter your Hight and Weight", Toast.LENGTH_SHORT).show();

                } else {
                    double w = Float.parseFloat(weight_text);
                    double h = Float.parseFloat(height_text);
                    if (h > 3) {
                        h = h / 100;
                    }
                    double IdealWeight = 22 * (h * h);
                    tv2.setText("    |||   BMI = " + String.valueOf(w / (h * h)).substring(0, 4));
                    tv1.setText("Ideal Weight = " + String.valueOf(IdealWeight).substring(0, 4) + " ~Kg");
                    BMR = (10 * w) + (6.25 * h * 100) - (5 * age) + 5;
                    double AMR = BMR * activityLevel;
                    if (IdealWeight > w) {
                        requiredCals = AMR + (AMR * (5 / 100));
                    } else if (IdealWeight < w) {
                        requiredCals = AMR - (AMR * (10 / 100));
                    } else {
                        requiredCals = AMR;
                    }
                    String mealCalories = distributeCalories(requiredCals);
                    GlobalGender = "male";
                    calstext.setText(mealCalories);
                    moreInfo.setVisibility(View.VISIBLE);

                }

            }
        });
        btnwmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height_text = height.getText().toString();
                String weight_text = weight.getText().toString();
                int age = Integer.valueOf(AGE.getText().toString());
                if (height_text.length() == 0 | weight_text.length() == 0) {
                    Toast.makeText(IdealWeightActivity.this, "Enter your Hight and Weight", Toast.LENGTH_SHORT).show();

                } else {
                    double w = Float.parseFloat(weight_text);
                    double h = Float.parseFloat(height_text);
                    if (h > 3) {
                        h = h / 100;
                    }

                    tv2.setText("    |||   BMI = " + String.valueOf(w / (h * h)).substring(0, 4));
                    h = h - 0.1;
                    double IdealWeight = 22 * (h * h);
                    tv1.setText("Ideal Weight = " + String.valueOf(IdealWeight).substring(0, 4) + " ~Kg");
                    BMR = (10 * w) + (6.25 * (h + 0.1) * 100) - (5 * age) - 161;
                    double AMR = BMR * activityLevel;
                    if (IdealWeight > w) {
                        requiredCals = AMR + (AMR * (5 / 100));
                    } else if (IdealWeight < w) {
                        requiredCals = AMR - (AMR * (10 / 100));
                    } else {
                        requiredCals = AMR;
                    }
                    String mealCalories = distributeCalories(requiredCals);
                    GlobalGender="female";
                    calstext.setText(mealCalories);
                    moreInfo.setVisibility(View.VISIBLE);


                }
            }
        });

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdealWeightActivity.this, more_info_ideal_w.class);
                intent.putExtra("AGE", AGE.getText().toString());
                intent.putExtra("W", weight.getText().toString());
                intent.putExtra("H", height.getText().toString());
                intent.putExtra("Gender", GlobalGender);
                intent.putExtra("ACTIVITY",String.valueOf(activityLevel));
                intent.putExtra("MoreInfo", calstext.getText());
                startActivity(intent);
            }
        });

    }

    public static String distributeCalories(double requiredCals) {
        int[] mc = new int[5];
        mc[0] = (int) (requiredCals * 0.25); // Breakfast
        mc[1] = (int) (requiredCals * 0.31); // Lunch
        mc[2] = (int) (requiredCals * 0.29); // Dinner
        mc[3] = (int) (requiredCals * 0.08); // Snack 1
        mc[4] = (int) (requiredCals * 0.07); // Snack 2
//        String txt = "Required Calories per Day: " + String.valueOf((int) requiredCals);
        String txt=String.format("Required Cals/Day to reach ideal weight : %d \nBreakfast: %d Calories\nSnack: %d Calories\nLunch: %d Calories\nSnack: %d Calories\nDinner: %d Calories",
                (int)requiredCals,mc[0],mc[3],mc[1],mc[4],mc[2]);
        return txt;
    }
}