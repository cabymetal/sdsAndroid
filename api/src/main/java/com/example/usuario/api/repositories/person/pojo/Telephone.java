package com.example.usuario.api.repositories.person.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Carlos Murillo on 12/05/2016.
 * Personal ASUS
 */
@Root(name="telefono")
public class Telephone {
    @Element(name="item", required = false)
    private String item;

    public Telephone(){

    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
