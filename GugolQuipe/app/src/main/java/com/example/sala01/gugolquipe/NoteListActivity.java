package com.example.sala01.gugolquipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoteListActivity extends BaseActivity {

    private NotesAdapter adapter;
    private ValueEventListener valueEventListener;
    private DatabaseReference notesReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        ArrayList<Note> notes = new ArrayList<Note>();
        adapter = new NotesAdapter(this, notes);
        ListView listView = (ListView) findViewById(R.id.notes);
        listView.setAdapter(adapter);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    Note note = children.getValue(Note.class);
                    note.setId(children.getKey());
                    adapter.add(note);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        notesReference = FirebaseDatabase.getInstance().getReference().child("notes");
        notesReference.addValueEventListener(valueEventListener);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = (Note) parent.getItemAtPosition(position);

                editar(note.getId());
            }
        });
    }

    public void novo(View view) {
        Intent intent = new Intent(this, NoteEditActivity.class);
        startActivity(intent);
    }

    public void editar(String id) {
        Intent intent = new Intent(this, NoteEditActivity.class);
        intent.putExtra(NoteEditActivity.EXTRA_NOTE_KEY, id);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        if (notesReference != null) {
            notesReference.removeEventListener(valueEventListener);
            notesReference.addValueEventListener(valueEventListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.clear();
        if (notesReference != null) {
            notesReference.removeEventListener(valueEventListener);
        }
    }
}
