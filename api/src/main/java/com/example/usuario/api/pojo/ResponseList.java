package com.example.usuario.api.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.usuario.api.repositories.person.pojo.Coincidence;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
@Root(name = "response")
public class ResponseList<T>  implements Parcelable{
    @ElementList(inline = true)
    private List<Coincidence> responses;
    public ResponseList(){}

    public ResponseList(Parcel in) {
        List<Coincidence> responses = new ArrayList<>();
        in.readList(responses, getClass().getClassLoader());
        this.setResponses(responses);
    }

    public List<Coincidence> getResponses() {
        return responses;
    }

    public void setResponses(List<Coincidence> responses) {
        this.responses = responses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(responses);
    }

    public static final Creator<ResponseList> CREATOR = new Creator<ResponseList>() {
        @Override
        public ResponseList createFromParcel(Parcel in) {
            return new ResponseList(in);
        }

        @Override
        public ResponseList[] newArray(int size) {
            return new ResponseList[size];
        }
    };
}
