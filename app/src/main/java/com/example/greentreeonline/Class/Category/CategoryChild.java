package com.example.greentreeonline.Class.Category;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;


@IgnoreExtraProperties
public class CategoryChild implements Serializable {
    private String id;
    private String name;
    private String idcat;

    public CategoryChild(){

    }
    public CategoryChild(String id, String name, String idcat){
        this.id = id;
        this.name = name;
        this.idcat = idcat;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcat() {
        return idcat;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }
}
