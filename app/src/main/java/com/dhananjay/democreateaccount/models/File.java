package com.dhananjay.democreateaccount.models;

import com.dhananjay.democreateaccount.R;
import com.dhananjay.democreateaccount.interfaces.LayoutItemType;

public class File implements LayoutItemType {
    public String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_file;
    }
}
