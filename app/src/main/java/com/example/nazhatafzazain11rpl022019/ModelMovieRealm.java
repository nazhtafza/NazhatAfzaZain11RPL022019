package com.example.nazhatafzazain11rpl022019;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ModelMovieRealm extends RealmObject {

    @PrimaryKey
     Integer id;
     String judul;
     String desc;
     String releaseDate;
     String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

