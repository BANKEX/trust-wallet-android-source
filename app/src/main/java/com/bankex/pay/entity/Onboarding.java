package com.bankex.pay.entity;

/**
 * @author Denis Anisimov.
 */
public class Onboarding {

    int id;
    int title;
    int desc;

    public Onboarding(int id, int title, int desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public int getTitle() {
        return title;
    }

    public int getDesc() {
        return desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }
}
