package com.example.practica1.Clases;

import android.graphics.Bitmap;

public class Song {
    private String songLink;
    private String songName;
    private String groupName;
    private Bitmap imgLink;


    public Song(String songLink,String songName, String groupName,Bitmap imgLink) {
        this.songLink = songLink;
        this.songName = songName;
        this.groupName = groupName;
        this.imgLink = imgLink;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songNname) {
        this.songName = songName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Bitmap getImgLink() {
        return imgLink;
    }

    public void setImgLink(Bitmap imgLink) {
        this.imgLink = imgLink;
    }
}
