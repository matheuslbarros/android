package com.example.sala01.gugolquipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NoteEditActivity extends BaseActivity {

    public static final String EXTRA_NOTE_KEY = "note_key";

    private DatabaseReference NoteReference;

    private ViewHolder viewHolder;

    private String noteKey;

    class ViewHolder {
        EditText title, description;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        viewHolder = new ViewHolder();
        viewHolder.title = (EditText) findViewById(R.id.editTitle);
        viewHolder.description = (EditText) findViewById(R.id.editDescription);

        noteKey = getIntent().getStringExtra(EXTRA_NOTE_KEY);
        if (noteKey == null) {
            NoteReference = FirebaseDatabase.getInstance().getReference().child("notes").push();
        } else {
            NoteReference = FirebaseDatabase.getInstance().getReference().child("notes").child(noteKey);
            NoteReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Note note = dataSnapshot.getValue(Note.class);

                    viewHolder.title.setText(note.title);
                    viewHolder.description.setText(note.description);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void salvar(View view) {
        Note note = new Note();
        note.user = getUid();
        note.title = viewHolder.title.getText().toString();
        note.description = viewHolder.description.getText().toString();
        NoteReference.setValue(note.toMap());

        // NoteReference.child("title").setValue(viewHolder.title.getText().toString());
        // NoteReference.child("description").setValue(viewHolder.description.getText().toString());
        finish();
    }
}
