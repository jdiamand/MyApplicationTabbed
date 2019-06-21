package com.digiota.myapplicationtabbed

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity

import com.digiota.myapplicationtabbed.ui.main.SectionsPagerAdapter
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import android.R.attr.right
import android.R.attr.left
import android.animation.IntArrayEvaluator
import android.view.View





class MainActivity : AppCompatActivity() {
    override fun onResume() {
        setAppBarHeight() ;
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        /*val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
       // setAppBarHeight() ;
    }

    private fun setAppBarHeight() {
        val appBarLayout: AppBarLayout = findViewById(R.id.appBar)
        val appBarTabs: TabLayout = findViewById(R.id.tabs)
        val offset = getStatusBarHeight() + dpToPx(48 + 56) * 2
        appBarLayout.setLayoutParams(
            CoordinatorLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                offset

            )
        )

         val outLocation : IntArray = intArrayOf(0,0)
        appBarTabs.getLocationOnScreen(outLocation)

        val params = AppBarLayout.LayoutParams(
            AppBarLayout.LayoutParams.MATCH_PARENT,
            AppBarLayout.LayoutParams.WRAP_CONTENT
        )
        var top = getRelativeTop(appBarTabs)

        params.setMargins(0, 374, 0, 0)
        appBarTabs.setLayoutParams(params)

        top = getRelativeTop(appBarTabs)
        top = getRelativeTop(appBarTabs)
    }

    private fun getRelativeTop(myView: View): Int {
        return if (myView.parent === myView.rootView)
            myView.top
        else
            myView.top + getRelativeTop(myView.parent as View)
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources
            .displayMetrics
            .density
        return Math.round(dp.toFloat() * density)
    }

}