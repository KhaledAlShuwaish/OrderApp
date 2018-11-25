package com.firebaseloginapp.AccountActivity.recycleview;

import android.os.Parcel;
import android.os.Parcelable;

public class list_item implements Parcelable {
    private String head;
    private String disc;
    private String count;


    public list_item(String head, String disc , String  count) {
        this.head = head;
        this.disc = disc;
        this.count=count;

    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.head);
        dest.writeString(this.disc);
        dest.writeString(this.count);
    }

    protected list_item(Parcel in) {
        this.head = in.readString();
        this.disc = in.readString();
        this.count=in.readString();
    }

    public static final Creator<list_item> CREATOR = new Creator<list_item>() {
        @Override
        public list_item createFromParcel(Parcel source) {
            return new list_item(source);
        }

        @Override
        public list_item[] newArray(int size) {
            return new list_item[size];
        }
    };


}
