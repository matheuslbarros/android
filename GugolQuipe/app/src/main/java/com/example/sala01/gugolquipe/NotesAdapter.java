package com.example.sala01.gugolquipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends ArrayAdapter<Note> {

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
        }

        TextView textTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView textDescription = (TextView) convertView.findViewById(R.id.textDescription);

        textTitle.setText(note.getTitle());
        textDescription.setText(note.getDescription());

        return convertView;
    }

}
