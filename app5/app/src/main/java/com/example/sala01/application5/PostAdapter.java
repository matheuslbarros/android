package com.example.sala01.application5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {

    private ViewHolder viewHolder;

    class ViewHolder {
        TextView userName, local, description;
        ImageView userPhoto, photo;
    }

    public PostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.userName = (TextView) convertView.findViewById(R.id.textUserName);
            viewHolder.local = (TextView) convertView.findViewById(R.id.textLocal);
            viewHolder.description = (TextView) convertView.findViewById(R.id.textDescription);
            viewHolder.userPhoto = (ImageView) convertView.findViewById(R.id.imageUserPhoto);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.imagePhoto);
        }

        Post post = getItem(position);

        viewHolder.userName.setText(post.getUser().getName());
        viewHolder.userPhoto.setTag(post.getUser().getPhoto());

        viewHolder.local.setText(post.getLocal());
        viewHolder.description.setText(post.getDescription());
        viewHolder.photo.setTag(post.getPhoto());

        new ImageDownload().execute(viewHolder.photo);
        new ImageDownload().execute(viewHolder.userPhoto);

        return convertView;
    }

}
