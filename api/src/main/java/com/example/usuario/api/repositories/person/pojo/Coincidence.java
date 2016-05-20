package com.example.usuario.api.repositories.person.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
@Root(name = "coincidence")
public class Coincidence<T> implements Parcelable{

    @Element (name = "id")
    private String id;

    @Element(name = "nombre")
    private String name;

    @Element(name = "nacionalidad", required = false)
    private String nacionality="";

    @ElementList(name="telefono",entry="item", required = false)
    private List<Telephone> phoneNumbers;

    public Coincidence(){
    }

    public Coincidence(Parcel in) {
        id = in.readString();
        name = in.readString();
        nacionality = in.readString();
        phoneNumbers = new ArrayList<>();
        in.readList(phoneNumbers, getClass().getClassLoader());
        this.setPhoneNumbers(phoneNumbers);
    }

    public static final Creator<Coincidence> CREATOR = new Creator<Coincidence>() {
        @Override
        public Coincidence createFromParcel(Parcel in) {
            return new Coincidence(in);
        }

        @Override
        public Coincidence[] newArray(int size) {
            return new Coincidence[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.nacionality);
        dest.writeList(this.phoneNumbers);
    }
}
