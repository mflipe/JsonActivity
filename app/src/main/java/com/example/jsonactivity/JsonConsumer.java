package com.example.jsonactivity;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
    A classe JsonConsumer.java deve ser criada para tratar de maneira assíncrona o acesso ao
    servidor de informações. Nesta classe estará implementado o processo de acesso web e de
    tratamento das informações recebidos do servidor. Essa classe deve estender da classe
    AsynkTask, que é uma classe abstrata para criação de tarefas assíncronas (que deve ser usado no
    Android). Como uma classe abstrata exige que faça a implementação dos métodos abstratos, que
    no caso será apenas o método "doInBackground()", método responsável por determinar qual
    tarefa deve ser executada em "background". Além desse método que será responsável por acessar
    a web, serão implementados também um método construtor, contendo como parâmetros o
    contexto da aplicação e a lista (ListView) onde serão apresentados os dados e o método
    "getPosts()", responsável por tratar as informações no formato JSON. Por fim, serão sobrescritos
    dois métodos: "onPreExecute()", responsável pelo pré-processamento; e "onPostExecute()",
    responsável pelo pós-processo, onde será implementado o processo de exibição dos dados na
    lista de Posts (ListView).
 */
public class JsonConsumer extends AsyncTask<String, Void, List<Post>> {

    private ProgressDialog dialog;
    private Context context;
    private ListView listView;

    public JsonConsumer(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = ProgressDialog.show(context, "Aguarde", "Baixando JSON, Por Favor Aguarde...");
    }


    @Override
    protected void onPostExecute(List<Post> posts) {
        super.onPostExecute(posts);

        dialog.dismiss();
        if (posts.size() > 0) {
            ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(
                    context,
                    android.R.layout.simple_list_item_1,
                    posts);
            listView.setAdapter(adapter);
        } else {
            AlertDialog.Builder adapter = new AlertDialog.Builder(context)
                    .setTitle("Atenção")
                    .setMessage("Não foi possível acessar essas informações...")
                    .setPositiveButton("OK", null);
        }
    }

    private List<Post> getPosts(String jsonString) {
        List<Post> posts = new ArrayList<>();

        try {
            JSONArray postsList = new JSONArray(jsonString);
            JSONObject postJson;

            for (int i = 0; i < postsList.length(); i++) {
                postJson = new JSONObject(postsList.getString(i));

                Post post = new Post();
                post.setId(postJson.getInt("id"));
                post.setUserId(postJson.getInt("userId"));
                post.setTitle(postJson.getString("title"));
                post.setBody(postJson.getString("body"));
                posts.add(post);
            }
        } catch (JSONException e) {

        }

        return posts;
    }

    @Override
    protected List<Post> doInBackground(String... params) {
        String urlString = params[0];

        try {
            URL url = new URL(urlString);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            BufferedInputStream input = new BufferedInputStream(http.getInputStream());
            StringBuilder builder = new StringBuilder();
            int size;
            byte[] bytes = new byte[1024];
            while ((size = input.read(bytes)) > 0) {
                builder.append(new String(bytes, 0, size));
            }
            String dados = builder.toString();
            http.disconnect();

            return getPosts(dados);

        } catch (IOException e) {
        }

        return null;
    }
}
