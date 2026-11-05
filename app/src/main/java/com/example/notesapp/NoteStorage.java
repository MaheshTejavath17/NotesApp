package com.example.notesapp;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class NoteStorage {
    private static final String PREF_NAME = "notes_pref";
    private static final String KEY_NOTES = "notes";

    public static ArrayList<Note> getNotes(Context context) {
        ArrayList<Note> notes = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_NOTES, "[]");

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                notes.add(new Note(obj.getString("title"), obj.getString("content")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }

    public static void saveNotes(Context context, ArrayList<Note> notes) {
        JSONArray array = new JSONArray();
        try {
            for (Note note : notes) {
                JSONObject obj = new JSONObject();
                obj.put("title", note.getTitle());
                obj.put("content", note.getContent());
                array.put(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_NOTES, array.toString())
                .apply();
    }
}
