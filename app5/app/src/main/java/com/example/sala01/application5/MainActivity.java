package com.example.sala01.application5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Post> notes = new ArrayList<Post>();
        adapter = new PostAdapter(this, notes);
        ListView listView = (ListView) findViewById(R.id.posts);
        listView.setAdapter(adapter);

        User user = new User();
        user.setName("Mister Bin");
        user.setPhoto("https://s-media-cache-ak0.pinimg.com/originals/56/4b/d5/564bd55d1394ffa35c4f8587c2a7eb75.jpg");

        Post post = new Post();
        post.setUser(user);
        post.setPhoto("http://s2.glbimg.com/yS92kAcO8yW_ORwWzcxbRWkOffQ=/top/s.glbimg.com/jo/g1/f/original/2015/09/04/rowan-atkinson-mr-bean-mini2.jpg");
        post.setLocal("Centro de treinamento da targettrust");
        post.setDescription("Humorista posou sentado em poltrona presta sobre o teto do veículo. Série de TV com o personagem faz 25 anos em 2015.");

        adapter.add(post);
        adapter.add(post);
        adapter.add(post);
        adapter.add(post);
    }
}
