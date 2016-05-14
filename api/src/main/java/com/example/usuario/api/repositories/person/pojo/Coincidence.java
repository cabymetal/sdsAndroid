package com.example.usuario.api.repositories.person.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
@Root(name = "coincidence")
public class Coincidence{

    @Element (name = "id")
    private int id;

    @Element(name = "nombre")
    private String name;

    @Element(name = "nacionalidad", required = false)
    private String nacionality;

    @ElementList(name = "telefono", required = false)
    private List<Telephone> phoneNumbers;



    public Coincidence(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public List<Telephone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<Telephone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
