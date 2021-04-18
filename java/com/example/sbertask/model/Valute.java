package com.example.sbertask.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "valutes")
public class Valute {



    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "valute_id")
    @NonNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ColumnInfo(name = "valute_name")
    private String name;

    @ColumnInfo(name = "valute_code")
    private String charCode;

    @ColumnInfo(name = "valute_nominal")
    private String nominal;

    @ColumnInfo(name = "valute_rate")
    private String rate;

//    public Valute(long id, String name, String charCode, String nominal, String rate){
//        this.id = id;
//        this.name= name;
//        this.charCode = charCode;
//        this.nominal = nominal;
//        this.rate = rate;
//    }

    public Valute(){};

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName(){
        return name;
    }
    public String getCharCode(){
        return charCode;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCharCode(String charCode){
        this.charCode = charCode;
    }
    public String toString(){
        return  "Валюта: " + id + " - " + name + " ( " + charCode + " )" + "\n" + nominal + ":" + rate;
    }
}

