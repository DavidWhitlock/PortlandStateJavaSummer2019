package edu.pdx.cs410J.whitlock.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DoubleNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_number);

        Intent intent = getIntent();
        int number = intent.getIntExtra("number", 0);

        TextView displayText = findViewById(R.id.displayText);
        displayText.setText(String.format("Double of %d is %d", number, number * 2));
    }


    public void goBack(View view) {
        this.finish();
    }
}
