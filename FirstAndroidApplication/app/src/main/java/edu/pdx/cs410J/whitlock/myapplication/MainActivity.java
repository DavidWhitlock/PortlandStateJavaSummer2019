package edu.pdx.cs410J.whitlock.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import edu.pdx.cs410J.AbstractAppointment;

public class MainActivity extends AppCompatActivity  {

    private static final int DOUBLE_NUMBER = 1;

    private int count;
    private String messageToDisplayAfterResume;
    private Integer doubled = null;

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
        message += " " + new AbstractAppointment() {

            @Override
            public String getBeginTimeString() {
                return "now";
            }

            @Override
            public String getEndTimeString() {
                return "later";
            }

            @Override
            public String getDescription() {
                return "Do stuff";
            }
        }.toString();
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
            startActivityForResult(intent, DOUBLE_NUMBER);

        } catch (NumberFormatException ex) {
            toast("Bad number: " + numberAsString);
        }
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == DOUBLE_NUMBER) {
            if (resultCode == RESULT_OK) {
                int number = data.getIntExtra("number", 0);
                doubled = data.getIntExtra("doubled", 0);
                messageToDisplayAfterResume = String.format("%d doubled is %d", number, doubled);
                // Changing the state of the widgets here will have no effect because the
                // activity is "paused"
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (messageToDisplayAfterResume != null) {
            setMessage(messageToDisplayAfterResume);
        }

        if (doubled != null) {
            EditText number = findViewById(R.id.number);
            number.setText(String.valueOf(doubled));
        }
    }
}
