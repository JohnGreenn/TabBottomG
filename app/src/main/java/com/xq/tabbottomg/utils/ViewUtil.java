package com.xq.tabbottomg.utils;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Desc：
 * author：Christiano
 */
public class ViewUtil {
    /**
     * 获取指定类型的子View
     *
     * @param group viewGroup
     * @param cls   如：RecyclerView.class
     * @param <T>
     * @return 指定类型的View
     */
    public static <T> T findTypeView(@Nullable ViewGroup group, Class<T> cls) {
        if (group == null) {
            return null;
        }
        //双向队列
        Deque<View> deque = new ArrayDeque<>();
        deque.add(group);
        while (!deque.isEmpty()) {
            //取第一个元素
            View node = deque.removeFirst();
            if (cls.isInstance(node)) {
                return cls.cast(node);
            } else if (node instanceof ViewGroup) {
                ViewGroup container = (ViewGroup) node;
                for (int i = 0, count = container.getChildCount(); i < count; i++) {
                    //添加到队尾
                    deque.add(container.getChildAt(i));
                }
            }
        }
        return null;
    }
}
