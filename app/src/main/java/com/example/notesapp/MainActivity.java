package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewNotes;
    Button btnAddNote;
    ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNotes = findViewById(R.id.listViewNotes);
        btnAddNote = findViewById(R.id.btnAddNote);

        btnAddNote.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class)));

        listViewNotes.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, ViewNoteActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        notes = NoteStorage.getNotes(this);
        ArrayList<String> titles = new ArrayList<>();

        for (Note note : notes) {
            titles.add(note.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, titles);
        listViewNotes.setAdapter(adapter);
    }
}
