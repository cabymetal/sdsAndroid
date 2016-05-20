package com.example.usuario.api.repositories.person.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Carlos Murillo on 12/05/2016.
 * Personal ASUS
 */
@Root(name = "telefono")
public class Telephone  implements Parcelable{
    @Element(name="item", required = false, type = String.class)
    private String item="";

    public Telephone(){

    }

    public Telephone(Parcel in) {
        item = in.readString();
    }

    public static final Creator<Telephone> CREATOR = new Creator<Telephone>() {
        @Override
        public Telephone createFromParcel(Parcel in) {
            return new Telephone(in);
        }

        @Override
        public Telephone[] newArray(int size) {
            return new Telephone[size];
        }
    };

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.item);
    }
}
