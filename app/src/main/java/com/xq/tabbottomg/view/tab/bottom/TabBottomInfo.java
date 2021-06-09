package com.xq.tabbottomg.view.tab.bottom;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

/**
 * Desc:
 */
public class TabBottomInfo<Color> {

    public enum TabType {
        BITMAP, ICON
    }

    public Class<? extends Fragment> fragment;
    public String name;
    public Bitmap defaultBitmap;
    public Bitmap selectedBitmap;
    public String iconFont; //阿里矢量图

    public Drawable defaultIcon;
    public Drawable selectedIcon;

    public int defaultResId;
    public int selectedResId;

    /**
     * Tips：在Java代码中直接设置iconfont字符串无效，需要定义在string.xml
     */
    public String defaultIconName;
    public String selectedIconName;
    public Color defaultColor;
    public Color tintColor;
    public TabType tabType;

    public TabBottomInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.tabType = TabType.BITMAP;
    }

    public TabBottomInfo(String name, @DrawableRes int defaultResId,@DrawableRes int selectedResId) {
        this.name = name;
        this.defaultResId = defaultResId;
        this.selectedResId = selectedResId;
        this.tabType = TabType.BITMAP;
    }

    public TabBottomInfo(String name, String iconFont, String defaultIconName, String selectedIconName, Color defaultColor, Color tintColor) {
        this.name = name;
        this.iconFont = iconFont;
        this.defaultIconName = defaultIconName;
        this.selectedIconName = selectedIconName;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.ICON;
    }

    public TabBottomInfo(String name, @DrawableRes int defaultResId,@DrawableRes int selectedResId, String defaultIconName, String selectedIconName, Color defaultColor, Color tintColor) {
        this.name = name;
        //this.defaultIcon = defaultIcon;
        //this.selectedIcon = selectedIcon;
        //Drawable defaultIcon,Drawable selectedIcon,
        this.defaultResId = defaultResId;
        this.selectedResId = selectedResId;

        this.defaultIconName = defaultIconName;
        this.selectedIconName = selectedIconName;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.ICON;
    }


}
