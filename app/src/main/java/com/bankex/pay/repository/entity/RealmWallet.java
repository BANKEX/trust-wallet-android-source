package com.bankex.pay.repository.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Denis Anisimov.
 */
public class RealmWallet extends RealmObject {

    @PrimaryKey
    private String address;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
