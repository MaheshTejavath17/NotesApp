package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class AddNoteActivity extends AppCompatActivity {
    EditText editTitle, editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String content = editContent.getText().toString().trim();

            if (title.isEmpty()) {
                editTitle.setError("Please enter a title");
                return;
            }

            ArrayList<Note> notes = NoteStorage.getNotes(this);
            notes.add(new Note(title, content));
            NoteStorage.saveNotes(this, notes);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
