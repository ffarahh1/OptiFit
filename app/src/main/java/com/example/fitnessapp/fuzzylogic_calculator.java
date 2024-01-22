package com.example.fitnessapp;
import com.fuzzylite.*;
import com.fuzzylite.defuzzifier.*;
import com.fuzzylite.factory.*;
import com.fuzzylite.imex.*;
import com.fuzzylite.norm.*;
import com.fuzzylite.norm.s.*;
import com.fuzzylite.norm.t.*;
import com.fuzzylite.rule.*;
import com.fuzzylite.term.*;
import com.fuzzylite.variable.*;



import java.util.HashMap;
import java.util.Random;
import java.util.Map;


public class fuzzylogic_calculator {
    public static double Cdays(String height ,String bmi ,String exercise,double heightMembership ,double bmiMembership ,double exerciseMembership){
        double days;
        Map<String, Double> baseValues = new HashMap<>();
        baseValues.put("ShortLowLow", 30.0);
        baseValues.put("ShortLowMedium", 35.0);
        baseValues.put("ShortLowHigh", 40.0);
        baseValues.put("ShortNormalLow", 30.0);
        baseValues.put("ShortNormalMedium", 25.0);
        baseValues.put("ShortNormalHigh", 20.0);
        baseValues.put("ShortHighLow", 150.0);
        baseValues.put("ShortHighMedium", 160.0);
        baseValues.put("ShortHighHigh", 170.0);
        baseValues.put("ShortVeryHighLow", 200.0);
        baseValues.put("ShortVeryHighMedium", 180.0);
        baseValues.put("ShortVeryHighHigh", 170.0);
        //####
        baseValues.put("AverageLowLow", 20.0);
        baseValues.put("AverageLowMedium", 30.0);
        baseValues.put("AverageLowHigh", 40.0);
        baseValues.put("AverageNormalLow", 30.0);
        baseValues.put("AverageNormalMedium", 25.0);
        baseValues.put("AverageNormalHigh", 20.0);
        baseValues.put("AverageHighLow", 130.0);
        baseValues.put("AverageHighMedium", 140.0);
        baseValues.put("AverageHighHigh", 150.0);
        baseValues.put("AverageVeryHighLow", 190.0);
        baseValues.put("AverageVeryHighMedium", 170.0);
        baseValues.put("AverageVeryHighHigh", 150.0);
        //####
        baseValues.put("TallLowLow", 40.0);
        baseValues.put("TallLowMedium", 50.0);
        baseValues.put("TallLowHigh", 60.0);
        baseValues.put("TallNormalLow", 30.0);
        baseValues.put("TallNormalMedium", 25.0);
        baseValues.put("TallNormalHigh", 20.0);
        baseValues.put("TallHighLow", 100.0);
        baseValues.put("TallHighMedium", 110.0);
        baseValues.put("TallHighHigh", 120.0);
        baseValues.put("TallVeryHighLow", 160.0);
        baseValues.put("TallVeryHighMedium", 140.0);
        baseValues.put("TallVeryHighHigh", 120.0);
        String key = height + bmi + exercise;
        if (baseValues.containsKey(key)) {
            days = baseValues.get(key) * heightMembership * bmiMembership * exerciseMembership;
        } else {
            days = 5000.0;
        }
        return days;

    }

    public static double calculateDays(String gender, double inputHeight, double inputBmi, double inputExercise) {

        String height;
        double heightMembership;
        if (gender.equals("Female")){
            if (inputHeight <= 155.0) {
                height = "Short";
                heightMembership = 1.0;
            } else if (inputHeight < 160.0) {
                height = "Short";
                heightMembership = (160.0 - inputHeight) / 5.0;
            } else if (inputHeight < 165.0) {
                height = "Average";
                heightMembership = (inputHeight - 155.0) / 10.0;
            } else if (inputHeight < 170.0) {
                height = "Average";
                heightMembership = 1.0;
            } else {
                height = "Tall";
                heightMembership = 1.0;
            }}
        else{
            if (inputHeight <= 165.0) {
                height = "Short";
                heightMembership = 1.0;
            } else if (inputHeight < 170.0) {
                height = "Short";
                heightMembership = (170.0 - inputHeight) / 5.0;
            } else if (inputHeight < 180.0) {
                height = "Average";
                heightMembership = (inputHeight - 165.0) / 15.0;
            } else if (inputHeight < 185.0) {
                height = "Average";
                heightMembership = 1.0;
            } else {
                height = "Tall";
                heightMembership = 1.0;
            }}

        String bmi;
        double bmiMembership;
        if (inputBmi <= 17.0) {
            bmi = "Low";
            bmiMembership = 1.0;
        } else if (inputBmi <= 19.0) {
            bmi = "Low";
            bmiMembership = (19.0 - inputBmi) / 2.0;
        } else if (inputBmi <= 21.0) {
            bmi = "Normal";
            bmiMembership = (inputBmi - 17.0) / 4.0;
        } else if (inputBmi <= 24.0) {
            bmi = "Normal";
            bmiMembership = 1.0;
        } else if (inputBmi <= 27.0) {
            bmi = "High";
            bmiMembership = (inputBmi - 21.0) / 6.0;
        } else if (inputBmi <= 29.0) {
            bmi = "VeryHigh";
            bmiMembership = (29.0 - inputBmi) / 2.0; // Decreasing linearly from 1.0 to 0.0
        } else {
            bmi = "VeryHigh";
            bmiMembership = 1.0;
        }

        // Exercise
        String exercise;
        double exerciseMembership;
        if (inputExercise <= 1.0) {
            exercise = "Low";
            exerciseMembership = 1.0;
        } else if (inputExercise < 2) {
            exercise = "Low";
            exerciseMembership = (2 - inputExercise) / 1.0; // Decreasing linearly from 1.0 to 0.0
        } else if (inputExercise < 3.5) {
            exercise = "Medium";
            exerciseMembership = (inputExercise - 1.0) / 1.5;
        } else if (inputExercise < 4.0) {
            exercise = "High";
            exerciseMembership = (4.0 - inputExercise) / 0.5; // Decreasing linearly from 1.0 to 0.0
        } else {
            exercise = "High";
            exerciseMembership = 1.0;
        }
        double d = Cdays(height , bmi , exercise,heightMembership , bmiMembership , exerciseMembership);
        return d;
    }
//    public static double calculateDays(String gender, double inputHeight, double inputBmi, double inputExercise) {
//            double days = 50.0;
//            String height;
//            double heightMembership;
//            if (gender.equals("Female")){
//                if (inputHeight <= 155.0) {
//                    height = "Short";
//                    heightMembership = 1.0;
//                } else if (inputHeight < 160.0) {
//                    height = "Short";
//                    heightMembership = (160.0 - inputHeight) / 5.0;
//                } else if (inputHeight < 165.0) {
//                    height = "Average";
//                    heightMembership = (inputHeight - 155.0) / 10.0;
//                } else if (inputHeight < 170.0) {
//                    height = "Average";
//                    heightMembership = 1.0;
//                } else {
//                    height = "Tall";
//                    heightMembership = 1.0;
//                }}
//            else{
//                if (inputHeight <= 165.0) {
//                    height = "Short";
//                    heightMembership = 1.0;
//                } else if (inputHeight < 170.0) {
//                    height = "Short";
//                    heightMembership = (170.0 - inputHeight) / 5.0;
//                } else if (inputHeight < 180.0) {
//                    height = "Average";
//                    heightMembership = (inputHeight - 165.0) / 15.0;
//                } else if (inputHeight < 185.0) {
//                    height = "Average";
//                    heightMembership = 1.0;
//                } else {
//                    height = "Tall";
//                    heightMembership = 1.0;
//                }}
//
//            String bmi;
//            double bmiMembership;
//            if (inputBmi <= 17.0) {
//                bmi = "Low";
//                bmiMembership = 1.0;
//            } else if (inputBmi <= 19.0) {
//                bmi = "Low";
//                bmiMembership = (19.0 - inputBmi) / 2.0;
//            } else if (inputBmi <= 21.0) {
//                bmi = "Normal";
//                bmiMembership = (inputBmi - 17.0) / 4.0;
//            } else if (inputBmi <= 24.0) {
//                bmi = "Normal";
//                bmiMembership = 1.0;
//            } else if (inputBmi <= 27.0) {
//                bmi = "High";
//                bmiMembership = (inputBmi - 21.0) / 6.0;
//            } else if (inputBmi <= 29.0) {
//                bmi = "VeryHigh";
//                bmiMembership = (29.0 - inputBmi) / 2.0; // Decreasing linearly from 1.0 to 0.0
//            } else {
//                bmi = "VeryHigh";
//                bmiMembership = 1.0;
//            }
//
//            // Exercise
//            String exercise;
//            double exerciseMembership;
//            if (inputExercise < 1.0) {
//                exercise = "Low";
//                exerciseMembership = 1.0;
//            } else if (inputExercise < 1.5) {
//                exercise = "Low";
//                exerciseMembership = (1.5 - inputExercise) / 0.5; // Decreasing linearly from 1.0 to 0.0
//            } else if (inputExercise < 3.5) {
//                exercise = "Medium";
//                exerciseMembership = (inputExercise - 1.0) / 2.5;
//            } else if (inputExercise < 4.0) {
//                exercise = "High";
//                exerciseMembership = (4.0 - inputExercise) / 0.5; // Decreasing linearly from 1.0 to 0.0
//            } else {
//                exercise = "High";
//                exerciseMembership = 1.0;
//            }
//
//
//            if (height.equals("Short") && bmi.equals("Low") && exercise.equals("Low")) {
//                days = 60.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Short") && bmi.equals("Low") && exercise.equals("Medium")) {
//                days = 50.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Short") && bmi.equals("Low") && exercise.equals("High")) {
//                days = 40.0 * heightMembership * bmiMembership * exerciseMembership;
//
//            } else if (height.equals("Short") && bmi.equals("Normal") && exercise.equals("Low")) {
//                days = 70.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Short") && bmi.equals("Normal") && exercise.equals("Medium")) {
//                days = 60.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Short") && bmi.equals("Normal") && exercise.equals("High")) {
//                days = 50.0 * heightMembership * bmiMembership * exerciseMembership;
//
//            } else if (height.equals("Short") && bmi.equals("High") && exercise.equals("Low")) {
//                days = 90.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Short") && bmi.equals("High") && exercise.equals("Medium")) {
//                days = 80.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Short") && bmi.equals("High") && exercise.equals("High")) {
//                days = 70.0 * heightMembership * bmiMembership * exerciseMembership;
//
//            } else if (height.equals("Short") && bmi.equals("VeryHigh") && exercise.equals("Low")) {
//                days = 110.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Short") && bmi.equals("VeryHigh") && exercise.equals("Medium")) {
//                days = 100.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Short") && bmi.equals("VeryHigh") && exercise.equals("High")) {
//                days = 90.0 * heightMembership * bmiMembership * exerciseMembership; }
//
//            // #####################################
//
//            else if (height.equals("Average") && bmi.equals("Low") && exercise.equals("Low")) {
//                days = 50.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Average") && bmi.equals("Low") && exercise.equals("Medium")) {
//                days = 40.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Average") && bmi.equals("Low") && exercise.equals("High")) {
//                days = 30.0 * heightMembership * bmiMembership * exerciseMembership;
//
//            } else if (height.equals("Average") && bmi.equals("Normal") && exercise.equals("Low")) {
//                days = 70.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Average") && bmi.equals("Normal") && exercise.equals("Medium")) {
//                days = 60.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Average") && bmi.equals("Normal") && exercise.equals("High")) {
//                days = 50.0 * heightMembership * bmiMembership * exerciseMembership;
//
//            } else if (height.equals("Average") && bmi.equals("High") && exercise.equals("Low")) {
//                days = 90.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Average") && bmi.equals("High") && exercise.equals("Medium")) {
//                days = 80.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Average") && bmi.equals("High") && exercise.equals("High")) {
//                days = 70.0 * heightMembership * bmiMembership * exerciseMembership;
//
//            } else if (height.equals("Average") && bmi.equals("VeryHigh") && exercise.equals("Low")) {
//                days = 120.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Average") && bmi.equals("VeryHigh") && exercise.equals("Medium")) {
//                days = 110.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Average") && bmi.equals("VeryHigh") && exercise.equals("High")) {
//                days = 100.0 * heightMembership * bmiMembership * exerciseMembership; }
//
//            // ############################################
//
//            else if (height.equals("Tall") && bmi.equals("Low") && exercise.equals("Low")) {
//                days = 40.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Tall") && bmi.equals("Low") && exercise.equals("Medium")) {
//                days = 30.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Tall") && bmi.equals("Low") && exercise.equals("High")) {
//                days = 20.0 * heightMembership * bmiMembership * exerciseMembership;
//
//            } else if (height.equals("Tall") && bmi.equals("Normal") && exercise.equals("Low")) {
//                days = 60.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Tall") && bmi.equals("Normal") && exercise.equals("Medium")) {
//                days = 50.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Tall") && bmi.equals("Normal") && exercise.equals("High")) {
//                days = 40.0 * heightMembership * bmiMembership * exerciseMembership;
//
//            } else if (height.equals("Tall") && bmi.equals("High") && exercise.equals("Low")) {
//                days = 80.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Tall") && bmi.equals("High") && exercise.equals("Medium")) {
//                days = 70.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Tall") && bmi.equals("High") && exercise.equals("High")) {
//                days = 60.0 * heightMembership * bmiMembership * exerciseMembership;
//
//            } else if (height.equals("Tall") && bmi.equals("VeryHigh") && exercise.equals("Low")) {
//                days = 110.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Tall") && bmi.equals("VeryHigh") && exercise.equals("Medium")) {
//                days = 100.0 * heightMembership * bmiMembership * exerciseMembership;
//            } else if (height.equals("Tall") && bmi.equals("VeryHigh") && exercise.equals("High")) {
//                days = 90.0 * heightMembership * bmiMembership * exerciseMembership; }
//            else{days = 5000;}
//
//
//            return days;
//        }



}



