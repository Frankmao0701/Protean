package com.frank.protean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Frankmao on 2018/3/22.
 */

public class Vetify implements Parcelable {
    private String id;
    private String token;

    public Vetify() {

    }

    protected Vetify(Parcel in) {
        id = in.readString();
        token = in.readString();
    }

    public static final Creator<Vetify> CREATOR = new Creator<Vetify>() {
        @Override
        public Vetify createFromParcel(Parcel in) {
            return new Vetify(in);
        }

        @Override
        public Vetify[] newArray(int size) {
            return new Vetify[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(token);
    }

    public void readFromParcel(Parcel dest) {
        id = dest.readString();
        token = dest.readString();
    }
}
