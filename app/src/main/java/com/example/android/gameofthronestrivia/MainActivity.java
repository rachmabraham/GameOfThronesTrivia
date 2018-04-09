package com.example.android.gameofthronestrivia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {

    //Define variables that will be used and changed throughout the app

    int numberCorrect = 0;
    String finalAnswer;
    StringBuilder runningCountofWrongAnswers = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This section will be for methods that evaluate whether or not each question is correct.

    public void evaluateOne () {
        RadioButton correctAnswerOne = findViewById(R.id.Q1A);
        if (correctAnswerOne.isChecked()) {
            numberCorrect+=1;
        }
        else {
            runningCountofWrongAnswers.append("1");
        }
    }

    public void evaluateTwo () {
        RadioButton correctAnswerTwo = findViewById(R.id.Q2B);
        if (correctAnswerTwo.isChecked()) {
            numberCorrect+=1;
        }
        else {
            runningCountofWrongAnswers.append("2");
        }
    }

    public void evaluateThree () {
        RadioButton correctAnswerThree = findViewById(R.id.Q3C);
        if (correctAnswerThree.isChecked()) {
            numberCorrect+=1;
        }
        else {
            runningCountofWrongAnswers.append("3");
        }
    }

    public void evaluateFour () {
        CheckBox correctAnswerFour1 = findViewById(R.id.Q4B);
        CheckBox correctAnswerFour2 = findViewById(R.id.Q4C);
        if (correctAnswerFour1.isChecked() && correctAnswerFour2.isChecked()) {
            numberCorrect+=1;
        }
        else {
            runningCountofWrongAnswers.append("4");
        }
    }

    public void evaluateFive () {
        RadioButton correctAnswerFive = findViewById(R.id.Q5A);
        if (correctAnswerFive.isChecked()) {
            numberCorrect+=1;
        }
        else {
            runningCountofWrongAnswers.append("5");
        }
    }

    public void evaluateSix () {
        RadioButton correctAnswerSix = findViewById(R.id.Q6A);
        if (correctAnswerSix.isChecked()) {
            numberCorrect+=1;
        }
        else {
            runningCountofWrongAnswers.append("6");
        }
    }

    public void evaluateSeven () {
        EditText eTforSeven = findViewById(R.id.Q7Answer);
        String answerToSeven = eTforSeven.getText().toString().toUpperCase();
        if (answerToSeven.equals("DIREWOLF") || answerToSeven.equals("DIRE WOLF")) {
            numberCorrect+=1;
        }
        else {
            runningCountofWrongAnswers.append("7");
        }
    }

    //This method is for evaluating every question.

    public void getAnswers () {
        evaluateOne();
        evaluateTwo();
        evaluateThree();
        evaluateFour();
        evaluateFive();
        evaluateSix();
        evaluateSeven();
    }

    //This method is for creating the toast that will tell the user how they did.

    public void createToast () {

        LayoutInflater inflater = getLayoutInflater();
        View customToastLayout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = customToastLayout.findViewById(R.id.text);
        ImageView image = customToastLayout.findViewById(R.id.imageViewGOT);
        Toast howYouDid = new Toast(getApplicationContext());
        howYouDid.setDuration(Toast.LENGTH_LONG);
        howYouDid.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        howYouDid.setView(customToastLayout);

        if (numberCorrect >= 5) {
            String goodJobText = finalAnswer + getString(R.string.veryWellString);
            text.setText(goodJobText);
            image.setImageResource(R.drawable.tyriona);
            howYouDid.show();

        }
        else if (numberCorrect >= 3) {
            String averageJobText = finalAnswer + getString(R.string.averageString);
            text.setText(averageJobText);
            image.setImageResource(R.drawable.jonsnowa);
            howYouDid.show();
        }
        else {
            String badJobText = finalAnswer + getString(R.string.notWellString);
            text.setText(badJobText);
            image.setImageResource(R.drawable.joffreya);
            howYouDid.show();
        }



        numberCorrect = 0;
        runningCountofWrongAnswers.setLength(0);
    }

    //This method is called when you click the "Get Results" button. It accesses many other important messages.

    public void getResults(View view){
            getAnswers();

            String runningCountofWrongAnswersString = runningCountofWrongAnswers.toString();
            StringBuilder finalCountWrongAnswers = new StringBuilder();
            for (char c: runningCountofWrongAnswersString.toCharArray()) {
                if (c != runningCountofWrongAnswersString.toCharArray()[runningCountofWrongAnswersString.length() - 1]) {
                    finalCountWrongAnswers.append(c).append(", ");
                }
                else {
                    finalCountWrongAnswers.append(c);
                }
            }
            String finalCountWrongAnswersString = finalCountWrongAnswers.toString();

            StringBuilder overallMessageStringBuilder = new StringBuilder();
            String stringAnswersRight = "You got " + numberCorrect + " questions right!\n";
            if (finalCountWrongAnswersString.length()>0) {
                overallMessageStringBuilder.append(stringAnswersRight + "\nYou got the following questions wrong: " + finalCountWrongAnswersString + "\n\n");
            }
            else {
                overallMessageStringBuilder.append(stringAnswersRight);
            }
            finalAnswer = overallMessageStringBuilder.toString();
            createToast();
    }
}
