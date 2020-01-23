package com.dhananjay.democreateaccount.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Account {

    @SerializedName("title")
    private String title;

    @SerializedName("subTitle")
    private String subTitle;

    @SerializedName("imageUrl")
    private String imageUrl;

}
