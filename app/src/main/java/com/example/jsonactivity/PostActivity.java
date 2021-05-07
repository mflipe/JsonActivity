package com.example.jsonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/*
    A programação na classe PostActivity deve implementar o processo de exibição dos dados do
    Post nos componentes TextView. Para isso deve ser recuperado do Intent o Post passado na
    chamada da Activity. Esse processo deve ser implementado no método "onCreate".
 */

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Post post = (Post) getIntent().getSerializableExtra("post");
        ((TextView) findViewById(R.id.edId)).setText(String.valueOf(post.getId()));
        ((TextView) findViewById(R.id.edUserId)).setText(String.valueOf(post.getUserId()));
        ((TextView) findViewById(R.id.edTitle)).setText(post.getTitle());
        ((TextView) findViewById(R.id.edBody)).setText(post.getBody());

    }
}