package com.xq.tabbottomg.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.xq.tabbottomg.R
import com.xq.tabbottomg.fragment.CategoryFragment
import com.xq.tabbottomg.fragment.HomeFragment
import com.xq.tabbottomg.fragment.LikeFragment
import com.xq.tabbottomg.fragment.MyFragment
import com.xq.tabbottomg.utils.DisplayUtil
import com.xq.tabbottomg.view.tab.bottom.TabBottomInfo
import com.xq.tabbottomg.view.tab.bottom.TabBottomLayoutG
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTabBottom()
    }

    private fun initTabBottom() {
        val tabBottomLayout: TabBottomLayoutG = findViewById(R.id.tablayoutg)
        tabBottomLayout.setTabAlpha(1f)
        val bottomInfoList: MutableList<TabBottomInfo<*>> = ArrayList()
        //删除mipmap-anydpi-v26 xml @https://hymane.itscoder.com/bitmap-factory-decode-return-null/
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//            HiTabInfo info = new HiTabInfo("tab" + i, bitmap, bitmap);
        val homeInfo = TabBottomInfo(
            "首页",
            "fonts/iconfont.ttf",
            getString(R.string.if_home),
            null,
            "#ff656667",
            "#ffd44949"
        )
        val infoRecommend = TabBottomInfo(
            "收藏",
            "fonts/iconfont.ttf",
            getString(R.string.if_favorite),
            null,
            "#ff656667",
            "#ffd44949"
        )

//        val infoCategory = HiTabInfo(
//            "分类",
//            "fonts/iconfont.ttf",
//            getString(R.string.if_category),
//            null,
//            "#ff656667",
//            "#ffd44949"
//        )

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.luoxi, null)

        /*val infoCategory = TabBottomInfo<String>(
            "分类",
            bitmap,
            bitmap
        )*/
        val infoCategory = TabBottomInfo<String>(
            "分类",
            R.drawable.luoxi,
            R.drawable.luoxi
        )
        val infoChat = TabBottomInfo(
            "推荐",
            "fonts/iconfont.ttf",
            getString(R.string.if_recommend),
            null,
            "#ff656667",
            "#ffd44949"
        )
        val infoProfile = TabBottomInfo(
            "我的",
            "fonts/iconfont.ttf",
            getString(R.string.if_profile),
            null,
            "#ff656667",
            "#ffd44949"
        )
        bottomInfoList.add(homeInfo)
        bottomInfoList.add(infoRecommend)
        bottomInfoList.add(infoCategory)
        bottomInfoList.add(infoChat)
        bottomInfoList.add(infoProfile)
        tabBottomLayout.inflateInfo(bottomInfoList)
//        Handler().postDelayed(Runnable {
//            infoList.removeAt(1)
//            hiTabBottomLayout.inflateInfo(infoList)
//            hiTabBottomLayout.defaultSelected(homeInfo)
//        },2000)
        tabBottomLayout.addTabSelectedChangeListener { _, _, nextInfo ->
            Toast.makeText(this@MainActivity, nextInfo.name, Toast.LENGTH_SHORT).show()
        }
        tabBottomLayout.defaultSelected(homeInfo)


        //        改变某个tab的高度
        val tabBottom = tabBottomLayout.findTab(bottomInfoList[2])
        tabBottom?.apply { resetHeight(DisplayUtil.dp2px(66f, resources)) }
    }


}