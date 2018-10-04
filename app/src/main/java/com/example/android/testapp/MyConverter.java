package com.example.android.testapp;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

public class MyConverter {

    @TypeConverter
    public String fromName(Person.Name name){
        return name.getFirst() + " " + name.getLast();
    }
    @TypeConverter
    public Person.Name toNameFirst(String name){
        Person.Name clssName = new Person.Name();
        clssName.setFirst(name);
        return clssName;
    }

    @TypeConverter()
    public String fromPicture(Person.Picture pictureClass){
        String picture = pictureClass.getLarge();
        return picture;
    }

    @TypeConverter()
    public Person.Picture toPicture(String picture){
        Person.Picture pictureClass = new Person.Picture();
        pictureClass.setLarge(picture);
        return pictureClass;
    }

    @TypeConverter
    public String fromId(Person.Id id){
        String value = id.getValue();
        return value;
    }
    @TypeConverter
    public Person.Id toId(String value) {
        return new Person.Id(value);
    }

    @TypeConverter
    public String fromRegistered(Person.Registered registered){
        return registered.getDate();
    }
    @TypeConverter
    public Person.Registered toRegistered(String date){
        Person.Registered registered = new Person.Registered();
        registered.setDate(date);
        return registered;
    }
}
