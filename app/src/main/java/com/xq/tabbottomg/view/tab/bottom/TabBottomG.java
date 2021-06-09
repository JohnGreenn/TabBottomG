package com.xq.tabbottomg.view.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;

import com.xq.tabbottomg.R;
import com.xq.tabbottomg.view.tab.common.ITabG;
import com.xq.tabbottomg.view.tab.common.ITabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Desc：
 * author：Christiano
 * git:https://github.com/JohnGreenn/TabBottomG.git
 */
public class TabBottomG extends RelativeLayout implements ITabG<TabBottomInfo<?>> {

    private TabBottomInfo<?> tabInfo;
    private ImageView tabImageView;
    private TextView tabIconView;
    private TextView tabNameView;

    public TabBottomG(Context context) {
        this(context,null);
    }

    public TabBottomG(Context context,AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TabBottomG(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.tab_bottom_g, this);
        tabImageView = findViewById(R.id.iv_image);
        tabIconView = findViewById(R.id.tv_icon);
        tabNameView = findViewById(R.id.tv_name);
    }


    @Override
    public void setTabInfoG(@NonNull @NotNull TabBottomInfo<?> tabBottomInfo) {
        this.tabInfo = tabBottomInfo;
        inflateInfo(false, true);
    }

    public TabBottomInfo getTabInfo() {
        return tabInfo;
    }

    public ImageView getTabImageView() {
        return tabImageView;
    }

    public TextView getTabIconView() {
        return tabIconView;
    }

    public TextView getTabNameView() {
        return tabNameView;
    }

    /**
     * 改变某个tab的高度
     *
     * @param height
     */
    @Override
    public void resetHeight(@Px int height) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        getTabNameView().setVisibility(View.GONE);
    }

    private void inflateInfo(boolean selected, boolean init) {
        if (tabInfo.tabType == TabBottomInfo.TabType.ICON) {
            if (init) {
                tabImageView.setVisibility(GONE);
                tabIconView.setVisibility(VISIBLE);
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), tabInfo.iconFont);
                tabIconView.setTypeface(typeface);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }
            }

            if (selected) {
                tabIconView.setText(TextUtils.isEmpty(tabInfo.selectedIconName) ? tabInfo.defaultIconName : tabInfo.selectedIconName);
                tabIconView.setTextColor(getTextColor(tabInfo.tintColor));
                tabNameView.setTextColor(getTextColor(tabInfo.tintColor));
            } else {
                tabIconView.setText(tabInfo.defaultIconName);
                tabIconView.setTextColor(getTextColor(tabInfo.defaultColor));
                tabNameView.setTextColor(getTextColor(tabInfo.defaultColor));
            }

        } else if (tabInfo.tabType == TabBottomInfo.TabType.BITMAP) {
            if (init) {
                tabImageView.setVisibility(VISIBLE);
                tabIconView.setVisibility(GONE);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }
            }
            if (selected) {
                //tabImageView.setImageBitmap(tabInfo.selectedBitmap);
                //tabImageView.setImageResource(R.drawable.luoxi);
                tabImageView.setImageResource(tabInfo.selectedResId);
            } else {
                //tabImageView.setImageBitmap(tabInfo.defaultBitmap);
                tabImageView.setImageResource(tabInfo.defaultResId);
            }
        }
    }

    @Override
    public void onTabSelectedChange(int index,@Nullable @org.jetbrains.annotations.Nullable TabBottomInfo<?> prevInfo,@NonNull @NotNull TabBottomInfo<?> nextInfo) {
        if (prevInfo != tabInfo && nextInfo != tabInfo || prevInfo == nextInfo) {
            return;
        }
        if (prevInfo == tabInfo) {
            inflateInfo(false, false);
        } else {
            inflateInfo(true, false);
        }
    }

    /**
     * color可传string或int的方法
     */
    @ColorInt
    private int getTextColor(Object color) {
        if (color instanceof String) {
            return Color.parseColor((String) color);
        } else {
            return (int) color;
        }
    }
}
