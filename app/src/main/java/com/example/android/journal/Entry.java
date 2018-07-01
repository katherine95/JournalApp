package com.example.android.journal;

/**
 * Created by katherine on 7/1/18.
 */

public class Entry {
    private String entry;

    public Entry(){}
    public Entry(String entry){
        this.entry = entry;
    }
    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}

