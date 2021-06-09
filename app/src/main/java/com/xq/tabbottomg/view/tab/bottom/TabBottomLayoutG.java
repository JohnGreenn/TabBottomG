package com.xq.tabbottomg.view.tab.bottom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xq.tabbottomg.R;
import com.xq.tabbottomg.utils.DisplayUtil;
import com.xq.tabbottomg.view.tab.common.ITabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Desc：
 * author：Christiano
 * 1. 透明度和底部透出，列表可渲染高度问题
 * 2. 中间高度超过，凸起布局
 */
public class TabBottomLayoutG extends FrameLayout implements ITabLayout<TabBottomG,TabBottomInfo<?>> {
    private List<OnTabSelectedListener<TabBottomInfo<?>>> tabSelectedChangeListeners = new ArrayList<>();
    private TabBottomInfo<?> selectedInfo;
    private float bottomAlpha = 1f;
    //TabBottom高度
    private static float tabBottomHeight = 50;
    //TabBottom的头部线条高度
    private float bottomLineHeight = 0.5f;
    //TabBottom的头部线条颜色
    private String bottomLineColor = "#dfe0e1";
    private List<TabBottomInfo<?>> infoList;

    private static final String TAG_TAB_BOTTOM = "TAG_TAB_BOTTOM";

    public TabBottomLayoutG(@NonNull Context context) {
        this(context, null);
    }

    public TabBottomLayoutG(@NonNull Context context,@Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabBottomLayoutG(@NonNull Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }


    @Override
    public void inflateInfo(@NonNull @NotNull List<TabBottomInfo<?>> infoList) {
        if (infoList.isEmpty()) {
            return;
        }
        this.infoList = infoList;
        // 移除之前已经添加的View
        for (int i = getChildCount() - 1; i > 0; i--) {
            removeViewAt(i);
        }
        selectedInfo = null;
        addBackground();
        //清除之前添加的HiTabBottom listener，Tips：Java foreach remove问题
        Iterator<OnTabSelectedListener<TabBottomInfo<?>>> iterator = tabSelectedChangeListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof TabBottomG) {
                iterator.remove();
            }
        }
        int height = DisplayUtil.dp2px(tabBottomHeight, getResources());
        FrameLayout ll = new FrameLayout(getContext());
        int width = DisplayUtil.getDisplayWidthInPx(getContext()) / infoList.size();
        ll.setTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < infoList.size(); i++) {
            final TabBottomInfo<?> info = infoList.get(i);
            //Tips：为何不用LinearLayout：当动态改变child大小后Gravity.BOTTOM会失效
            LayoutParams params = new LayoutParams(width, height);
            params.gravity = Gravity.BOTTOM;
            params.leftMargin = i * width;

            TabBottomG tabBottom = new TabBottomG(getContext());
            tabSelectedChangeListeners.add(tabBottom);
            tabBottom.setTabInfoG(info);
            ll.addView(tabBottom, params);
            tabBottom.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelected(info);
                }
            });
        }
        LayoutParams flPrams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        flPrams.gravity = Gravity.BOTTOM;
        addBottomLine();
        addView(ll, flPrams);

    }

    @Override
    public void addTabSelectedChangeListener(OnTabSelectedListener<TabBottomInfo<?>> listener) {
        tabSelectedChangeListeners.add(listener);
    }

    public void setTabAlpha(float alpha) {
        this.bottomAlpha = alpha;
    }

    public void setTabHeight(float tabHeight) {
        this.tabBottomHeight = tabHeight;
    }

    public void setBottomLineHeight(float bottomLineHeight) {
        this.bottomLineHeight = bottomLineHeight;
    }

    public void setBottomLineColor(String bottomLineColor) {
        this.bottomLineColor = bottomLineColor;
    }

    @Override
    public TabBottomG findTab(@NonNull @NotNull TabBottomInfo<?> info) {
        ViewGroup ll = findViewWithTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < ll.getChildCount(); i++) {
            View child = ll.getChildAt(i);
            if (child instanceof TabBottomG) {
                TabBottomG tab = (TabBottomG) child;
                if (tab.getTabInfo() == info) {
                    return tab;
                }
            }
        }
        return null;
    }

    @Override
    public void defaultSelected(@NonNull @NotNull TabBottomInfo<?> defaultInfo) {
        onSelected(defaultInfo);
    }

    private void onSelected(@NonNull TabBottomInfo<?> nextInfo) {
        for (OnTabSelectedListener<TabBottomInfo<?>> listener : tabSelectedChangeListeners) {
            listener.onTabSelectedChange(infoList.indexOf(nextInfo), selectedInfo, nextInfo);
        }
        this.selectedInfo = nextInfo;
    }

    private void addBottomLine() {
        View bottomLine = new View(getContext());
        bottomLine.setBackgroundColor(Color.parseColor(bottomLineColor));

        LayoutParams bottomLineParams =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(bottomLineHeight, getResources()));
        bottomLineParams.gravity = Gravity.BOTTOM;
        bottomLineParams.bottomMargin = DisplayUtil.dp2px(tabBottomHeight - bottomLineHeight, getResources());
        addView(bottomLine, bottomLineParams);
        bottomLine.setAlpha(bottomAlpha);
    }

    private void addBackground() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_layout_bg, null);

        LayoutParams params =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(tabBottomHeight, getResources()));
        params.gravity = Gravity.BOTTOM;
        addView(view, params);
        view.setAlpha(bottomAlpha);
        //view.setBackgroundColor(Color.BLUE);
    }

}
