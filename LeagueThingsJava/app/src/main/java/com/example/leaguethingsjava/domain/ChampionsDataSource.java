package com.example.leaguethingsjava.domain;

import com.example.leaguethingsjava.R;
import com.example.leaguethingsjava.data.models.Champion;

import java.util.HashSet;

public class ChampionsDataSource {
    public static HashSet<Champion> champions = new HashSet<>();
    public static void fillSet(){
        champions.add( new Champion("Akali", R.raw.akali_icon, R.raw.akali_select,"Mid","Assasin"));
        champions.add( new Champion("Ahri", R.raw.ahri_icon, R.raw.ahri_select, "Mid","Mage"));
        champions.add( new Champion("Azir", R.raw.azir_icon,R.raw.azir_select,"Mid","Mage"));
        champions.add( new Champion("Ezreal", R.raw.ezreal_icon,R.raw.ezreal_select,"ADC","Marksman"));
        champions.add( new Champion("Kha`zix", R.raw.khazix_icon, R.raw.khazix_select,"Jungler","Assasin"));
        champions.add( new Champion("Graves", R.raw.graves_icon, R.raw.graves_select,"Jungler","Warrior"));
        champions.add( new Champion("Lulu", R.raw.lulu_icon, R.raw.lulu_select,"Support","Utility"));
        champions.add( new Champion("Maokai", R.raw.maokai_icon, R.raw.maokai_select,"Top","Tank"));
        champions.add( new Champion("Morgana", R.raw.morgana_icon, R.raw.morgana_select,"Support","Mage"));
        champions.add( new Champion("Rakan", R.raw.rakan_icon, R.raw.rakan_select,"Support","Utility"));
        champions.add( new Champion("Vel`koz", R.raw.velkoz_icon, R.raw.velkoz_select,"Mid","Mage"));
        champions.add( new Champion("Senna", R.raw.senna_icon, R.raw.senna_select,"Support","Marksman"));
        champions.add( new Champion("Zoe", R.raw.zoe_icon, R.raw.zoe_select,"Mid","Mage"));
    }

}
