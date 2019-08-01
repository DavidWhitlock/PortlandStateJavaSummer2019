package edu.pdx.cs410J.whitlock.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");

        } else {
            count = 0;
        }

        displayCount();

    }

    private void displayCount() {
        TextView text = findViewById(R.id.text);
        text.setText("Hello" + count);
    }

    @Override
    protected void onStart() {
        super.onStart();

        displayCount();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        this.count++;
        outState.putInt("count", this.count);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            this.count = savedInstanceState.getInt("count");
        }
    }
}
