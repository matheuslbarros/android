package com.example.sala01.gugolquipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoteListActivity extends BaseActivity {

    private NotesAdapter adapter;
    private ValueEventListener valueEventListener;
    private DatabaseReference notesReference;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        if (adapter == null) {
            adapter = new NotesAdapter(this, new ArrayList<Note>());

            listView = (ListView) findViewById(R.id.notes);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Note note = (Note) parent.getItemAtPosition(position);
                    editar(note.id);
                }
            });

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot children : dataSnapshot.getChildren()) {
                        Note note = children.getValue(Note.class);

                        if (getUid().equals(note.user)) {
                            note.id = children.getKey();
                            adapter.add(note);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };

            notesReference = FirebaseDatabase.getInstance().getReference().child("notes");
        }
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
        adapter.notifyDataSetChanged();
        if (notesReference != null) {
            notesReference.addValueEventListener(valueEventListener);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        adapter.clear();
        if (notesReference != null) {
            notesReference.removeEventListener(valueEventListener);
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        adapter.clear();
        if (notesReference != null) {
            notesReference.removeEventListener(valueEventListener);
        }
        super.onStop();
    }
}
