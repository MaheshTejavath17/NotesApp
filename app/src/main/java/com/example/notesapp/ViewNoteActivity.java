package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class ViewNoteActivity extends AppCompatActivity {
    EditText editTitle, editContent;
    ArrayList<Note> notes;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        position = getIntent().getIntExtra("position", -1);
        notes = NoteStorage.getNotes(this);

        if (position >= 0 && position < notes.size()) {
            Note note = notes.get(position);
            editTitle.setText(note.getTitle());
            editContent.setText(note.getContent());
        } else {
            finish();
            return;
        }

        btnUpdate.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String content = editContent.getText().toString().trim();

            if (title.isEmpty()) {
                editTitle.setError("Please enter a title");
                return;
            }

            notes.set(position, new Note(title, content));
            NoteStorage.saveNotes(this, notes);
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnDelete.setOnClickListener(v -> {
            notes.remove(position);
            NoteStorage.saveNotes(this, notes);
            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
