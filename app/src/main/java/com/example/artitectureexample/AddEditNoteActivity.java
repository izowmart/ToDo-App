package com.example.artitectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Objects;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.example.artitectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.artitectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.artitectureexample.EXTRA_PRIORITY";
    public static final String EXTRA_ID =
            "com.example.artitectureexample.EXTRA_ID";

    private EditText edit_title, edit_description;
    private NumberPicker numPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edit_title = findViewById(R.id.title);
        edit_description = findViewById(R.id.description);
        numPicker = findViewById(R.id.num_picker_priority);

        numPicker.setMinValue(1);
        numPicker.setMaxValue(10);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
//        check if our intent has id in it because we only set id when we rae updating an existing record
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            edit_title.setText(intent.getStringExtra(EXTRA_TITLE));
            edit_description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
//            we must set the default value since int integer are not nullable
            numPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }

    }

    private void SaveNote() {
        String title = edit_title.getText().toString();
        String description = edit_description.getText().toString();
        int priority = numPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

//        we set the default value to -1 since no item can have -ve in our database.
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                SaveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
