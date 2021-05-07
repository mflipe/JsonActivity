package com.example.jsonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
            O método "onCreate" deve ser alterado para tratar a instanciação de um novo JsonConsumer, que
            irá executar a chamada do servidor de informações da web, e a vinculação entre a ação de clicar
            no item da lista e o método "openView".
         */

        new JsonConsumer(
                MainActivity.this,
                (ListView) findViewById(R.id.listview)
        ).execute("http://jsonplaceholder.typicode.com/posts");

        ((ListView) findViewById(R.id.listview)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openView((Post) parent.getItemAtPosition(position));
            }
        });
    }

    /*
        Criação de um método chamado "openView", que irá implementar o processo para chamada
        da PostActivity, passando o Post que foi clicado. Este método deve ter um parâmetro de entrada
        do tipo Post. Este método será vinculado ao clicar dos itens da lista de Posts;
     */
    private void openView(Post post) {
        Intent intent = new Intent(getApplicationContext(), PostActivity.class);
        intent.putExtra("post", post);
        startActivity(intent);
    }
}