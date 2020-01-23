package com.dhananjay.democreateaccount.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Data;

@Data
public class AccountResponse {

    @SerializedName("accountList")
    private ArrayList<Account> account;

}
