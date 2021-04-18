package com.example.sbertask.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sbertask.model.Valute;

import java.util.List;

@Dao
public interface ValuteDAO {

    @Insert
    public long addValute(Valute valute);

    @Update
    public void updateValute(Valute valute);

    @Delete
    public void deleteValute(Valute valute);

    @Query("select * from valutes")
    public List<Valute> getAllValutes();

    @Query("select * from valutes where valute_id==:valuteId ")
    public Valute getValute(long valuteId);


}
