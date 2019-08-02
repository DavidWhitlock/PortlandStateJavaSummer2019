package edu.pdx.cs410J.whitlock.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

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

        ListView listView = findViewById(R.id.listView);
        String[] daysOfTheWeek = getResources().getStringArray(R.array.days_of_the_week);
        listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, daysOfTheWeek));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementCount();
                displayCount();
            }
        });
    }

    private void displayCount() {
        String message = "The count is " + count;
        setMessage(message);
    }

    private void setMessage(CharSequence message) {
        TextView text = findViewById(R.id.text);
        text.setText(message);
    }

    @Override
    protected void onStart() {
        super.onStart();

        displayCount();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        incrementCount();
        outState.putInt("count", this.count);

        super.onSaveInstanceState(outState);
    }

    private void incrementCount() {
        this.count++;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            this.count = savedInstanceState.getInt("count");
        }
    }

    public void displayCurrentTimeInEditText(View view) {
        TextClock clock = (TextClock) view;
        setMessage(clock.getText());
    }

    public void doubleNumber(View view) {
        EditText numberToDouble = findViewById(R.id.number);
        String numberAsString = numberToDouble.getText().toString();
        try {
            int number = Integer.parseInt(numberAsString);
            Uri uri = Uri.fromParts("number", String.valueOf(number), null);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri, this, DoubleNumberActivity.class);
            intent.putExtra("number", number);
            startActivity(intent);

        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Bad number: " + numberAsString, Toast.LENGTH_SHORT).show();
        }
    }
}
