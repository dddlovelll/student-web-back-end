package com.qmkl.MyCompares;

import com.qmkl.entity.Files;

import java.util.Comparator;

public class MyCompar implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String str1 = PinYingUtil.getStringPinYin(o1);
        String str2 = PinYingUtil.getStringPinYin(o2);
        return str1.compareToIgnoreCase(str2);
    }
}
