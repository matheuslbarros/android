package com.example.sala01.gugolquipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NotesAdapter extends ArrayAdapter<Note> {

    private ViewHolder viewHolder;

    class ViewHolder {
        public TextView title, description;
    }

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.textTitle);
            viewHolder.description = (TextView) convertView.findViewById(R.id.textDescription);
        }

        Note note = getItem(position);

        viewHolder.title.setText(note.title);
        viewHolder.description.setText(note.description);

        return convertView;
    }

}
