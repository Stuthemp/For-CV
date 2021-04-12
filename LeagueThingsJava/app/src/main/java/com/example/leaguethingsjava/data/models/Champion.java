package com.example.leaguethingsjava.data.models;

import java.util.Set;

public class Champion {
    private String name;
    private int iconId;
    private int phraseId = NO_SOUND_PROVIDED;
    private String role;
    private String subRole;


    //private static final int NO_IMAGE_PROVIDED = -1;
    private static final int NO_SOUND_PROVIDED = -1;

    public Champion(String name, int iconId, int phraseId, String role, String subRole){
        this.name = name;
        this.iconId = iconId;
        this.phraseId = phraseId;
        this.role = role;
        this.subRole = subRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(int phraseId) {
        this.phraseId = phraseId;
    }

    public String getRoles(){
        return this.role;
    }

    public String getSubRole() {
        return subRole;
    }

//    public boolean hasImage(){
//        return iconId!= NO_IMAGE_PROVIDED;
//    }

    public boolean hasSound(){
        return phraseId!= NO_SOUND_PROVIDED;
    }
}
