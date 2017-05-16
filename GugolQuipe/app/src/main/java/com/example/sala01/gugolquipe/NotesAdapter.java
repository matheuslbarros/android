package com.example.sala01.gugolquipe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NotesAdapter extends ArrayAdapter<Note> {

    private ViewHolder viewHolder;

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title, description;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.textTitle);
            description = (TextView) itemView.findViewById(R.id.textDescription);
        }

        public void bind(Note note) {
            title.setText(note.title);
            description.setText(note.description);
        }
    }

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
            viewHolder = new ViewHolder(convertView);
        }

        Note note = getItem(position);
        viewHolder.bind(note);

        return convertView;
    }

}
