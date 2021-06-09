package com.xq.tabbottomg.view.tab.common;

import androidx.annotation.NonNull;
import androidx.annotation.Px;

/**
 * Desc：
 * author：Christiano
 * gitee:
 * time：2021/06/08 17:58
 */
public interface ITabG<D> extends ITabLayout.OnTabSelectedListener<D> {

    void setTabInfoG(@NonNull D data);
    /**
     * 动态修改某个item的大小
     *
     * @param height
     */
    void resetHeight(@Px int height);

}

