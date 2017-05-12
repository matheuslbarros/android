package com.example.sala01.application5;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {

    public PostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item, parent, false);
        }

        TextView textUserName = (TextView) convertView.findViewById(R.id.textUserName);
        ImageView imageUserPhoto = (ImageView) convertView.findViewById(R.id.imageUserPhoto);

        TextView textLocal = (TextView) convertView.findViewById(R.id.textLocal);
        TextView textDescription = (TextView) convertView.findViewById(R.id.textDescription);
        ImageView imagePhoto = (ImageView) convertView.findViewById(R.id.imagePhoto);

        textUserName.setText(post.getUser().getName());
        imageUserPhoto.setTag(post.getUser().getPhoto());

        textLocal.setText(post.getLocal());
        textDescription.setText(post.getDescription());
        imagePhoto.setTag(post.getPhoto());

        new ImageDownload().execute(imagePhoto);
        new ImageDownload().execute(imageUserPhoto);

        return convertView;
    }

}
