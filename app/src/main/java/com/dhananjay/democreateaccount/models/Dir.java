package com.dhananjay.democreateaccount.models;

import com.dhananjay.democreateaccount.R;
import com.dhananjay.democreateaccount.interfaces.LayoutItemType;

public class Dir implements LayoutItemType {
    public String dirName;

    public Dir(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dir;
    }
}
