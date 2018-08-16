package com.bankex.pay.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.annotations.PrimaryKey;

public class Wallet implements Parcelable {

    public static final String ETH_WALLET_NAME = "ETH Wallet Name";

    @PrimaryKey
    public final String address;
    public String name;

    public Wallet(String address) {
        this.address = address;
        this.name = ETH_WALLET_NAME;
    }

    public Wallet(String address, String name) {
        this.address = address;
        this.name = name;
    }

    private Wallet(Parcel in) {
        address = in.readString();
        name = in.readString();
    }

    public static final Creator<Wallet> CREATOR = new Creator<Wallet>() {
        @Override
        public Wallet createFromParcel(Parcel in) {
            return new Wallet(in);
        }

        @Override
        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };

    public boolean sameAddress(String address) {
        return this.address.equals(address);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeString(name);
    }
}
