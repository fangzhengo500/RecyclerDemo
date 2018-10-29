package com.loosu.recyclerdemo.utils;

import com.loosu.recyclerdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ResouceUtil {
    private static int[] imgResIds = {
            R.mipmap.img_1,
            R.mipmap.img_2,
            R.mipmap.img_3,
            R.mipmap.img_4,
            R.mipmap.img_5,
            R.mipmap.img_6,
            R.mipmap.img_7,
            R.mipmap.img_8,
            R.mipmap.img_9,
            R.mipmap.img_10,
            R.mipmap.img_11,
            R.mipmap.img_12,
            R.mipmap.img_13,
            R.mipmap.img_14,
            R.mipmap.img_15,
            R.mipmap.img_16,
            R.mipmap.img_17,
            R.mipmap.img_18,
            R.mipmap.img_19,
            R.mipmap.img_20,
    };

    public static int[] getImagesAsArray() {
        return imgResIds;
    }

    public static List<Integer> getImagesAsList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int imgResId : imgResIds) {
            list.add(imgResId);
        }
        return list;
    }
}
