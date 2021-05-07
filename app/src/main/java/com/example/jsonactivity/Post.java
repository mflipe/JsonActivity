package com.example.jsonactivity;

import java.io.Serializable;

/*
    A classe Post.java deve ser criada de maneira que contenha os dados id, userId, title e body, com
    seus respectivos métodos "get" e "set", dois métodos construtores, sendo um sem parâmetros de
    entrada e outro com parâmetros referentes a todos os atributos da classe, além da sobrescrita do
    método "toString". A classe com a sua implementação deve ficar como a figura abaixo.
*/

public class Post implements Serializable {
    private int id;
    private int userId;
    private String title;
    private String body;

    public Post() {

    }

    public Post(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }
}
