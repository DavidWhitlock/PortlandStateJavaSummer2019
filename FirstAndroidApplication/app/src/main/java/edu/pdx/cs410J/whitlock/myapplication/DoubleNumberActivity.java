package edu.pdx.cs410J.whitlock.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DoubleNumberActivity extends AppCompatActivity {

    private int number;
    private int doubled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_number);

        Intent intent = getIntent();
        number = intent.getIntExtra("number", 0);

        TextView displayText = findViewById(R.id.displayText);
        doubled = number * 2;
        displayText.setText(String.format("Double of %d is %d", number, doubled));
    }


    public void goBack(View view) {
        Uri uri = Uri.fromParts("number", String.valueOf(this.number), null);
        Intent result = new Intent("Doubled Number", uri);
        result.putExtra("number", number);
        result.putExtra("doubled", doubled);
        setResult(RESULT_OK, result);

        this.finish();
    }
}
