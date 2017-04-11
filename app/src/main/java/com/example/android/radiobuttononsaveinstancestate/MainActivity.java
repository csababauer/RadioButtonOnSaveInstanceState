package com.example.android.radiobuttononsaveinstancestate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int score = 0;
    public boolean flag = true;
    public int checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //submit button
    public void submit(View view) {

        //it executes if flag is true, than flag is set to false to avoid clicking again
        if (flag) {
            RadioButton rb = (RadioButton) findViewById(R.id.b2);
            Boolean checked2 = rb.isChecked();
            if (checked2) {
                score += 1;
            }
            showscore();

            //disable submit button to avoid second click
            flag = false;
        }
    }

    public void showscore() {
        TextView result = (TextView) findViewById(R.id.score);
        result.setText("" + score);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //save score
        savedInstanceState.putInt("scoreS", score);
        //save submit button
        savedInstanceState.putBoolean("flagS", flag);

        //save checked radio button
        RadioGroup rgS = (RadioGroup) findViewById(R.id.rg);
        int checked = rgS.getCheckedRadioButtonId();
        savedInstanceState.putInt("checkedS", checked);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("scoreS");
            flag = savedInstanceState.getBoolean("flagS");

            //restore radio button
            checked = savedInstanceState.getInt("checkedS");
            // Apply the restored value
            RadioGroup rgS = (RadioGroup) findViewById(R.id.rg);
            rgS.check(checked);


            showscore();

        }
    }

    //cancel button clears score and button states
    public void cancel (View view){
        score = 0;
        flag = true;
        showscore();
    }
}


