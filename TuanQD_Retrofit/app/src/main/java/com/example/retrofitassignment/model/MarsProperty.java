package com.example.retrofitassignment.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class MarsProperty implements Serializable {
    public String id;
    public String type;
    public @Json(name = "img_src")
    String imgSrcUrl;
    public Double price;

    public String getImgSrcUrl() {
        return imgSrcUrl;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }
}
