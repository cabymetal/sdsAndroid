package com.example.usuario.api.pojo;

import com.example.usuario.api.repositories.person.pojo.Coincidence;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
@Root(name = "response")
public class ResponseList<T> {
    @ElementList(inline = true)
    private List<Coincidence> responses;
    public ResponseList(){}

    public List<Coincidence> getResponses() {
        return responses;
    }

    public void setResponses(List<Coincidence> responses) {
        this.responses = responses;
    }
}
