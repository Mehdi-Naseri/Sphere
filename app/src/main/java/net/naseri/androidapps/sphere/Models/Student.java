package net.naseri.androidapps.sphere.Models;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by Mehdi on 9/24/2017.
 */

public class Student implements Serializable {
    public int id;
    public String name;
    public String family;
    public int phonenumber;

    public Student(String name,String family,int phonenumber)
    {
        this.name = name;
        this.family = family;
        this.phonenumber = phonenumber;
    }

    public Student(int id, String name, String family, int phonenumber) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return id + " " + name +" "+family+" "+phonenumber;
    }

    public ContentValues toContentValues() {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("name", name);
        contentvalues.put("family", family);
        contentvalues.put("phonenumber", phonenumber);
        return contentvalues;
    }
}
