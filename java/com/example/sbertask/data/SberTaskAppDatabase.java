package com.example.sbertask.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sbertask.model.Valute;

@Database(entities = {Valute.class}, version = 3, exportSchema = false)
public abstract class SberTaskAppDatabase extends RoomDatabase {


    public abstract ValuteDAO getValuteDAO();

}
