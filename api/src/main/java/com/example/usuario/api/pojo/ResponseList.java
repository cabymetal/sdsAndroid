package com.example.usuario.api.pojo;

import java.util.List;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
public class ResponseList<T> {

    public List<T> responses;
    public ResponseList(){}

    public List<T> getResponses() {
        return responses;
    }

    public void setResponses(List<T> responses) {
        this.responses = responses;
    }
}
