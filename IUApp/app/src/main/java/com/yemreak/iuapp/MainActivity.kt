package com.yemreak.iuapp

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Pair
import android.view.View
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.slider_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = MainSlider(this)
    private lateinit var dots: Array<ImageView?>
    private var currentPage: Int = 0
    private var new = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setProperties()
        binEvents()

    }

    private fun setProperties() {
        vp_main.adapter = adapter

        disableButton(btn_prev)
        addDotIndicator(0)
    }

    private fun binEvents() {
        vp_main.addOnPageChangeListener(OnPageChangeListener())
        btn_prev.setOnClickListener { btnPrevOnClick() }
        btn_next.setOnClickListener { btnNextOnClick() }

    }

    private fun disableButton(btn: Button) {
        btn.visibility = View.INVISIBLE
        btn.isEnabled = false
    }

    private fun enableButton(btn: Button) {
        btn.visibility = View.VISIBLE
        btn.isEnabled = true
    }

    /**
     * ViewPager'a uyumlu olan bir nokta göstergesi.
     */
    private fun addDotIndicator(position: Int) {
        if (new) {
            dots = arrayOfNulls(adapter.count)
            new = false

            for (i in 0 until adapter.count) {
                dots[i] = ImageView(this)
                dots[i]!!.setImageResource(R.mipmap.ic_dot)
                dots[i]!!.setPadding(13, 0, 0, 0)
                ll_dots.addView(dots[i])
            }
        }

        for (i in 0 until adapter.count)
            dots[i]!!.setImageResource(R.mipmap.ic_dot)

        dots[position]!!.setImageResource(R.mipmap.ic_selected_dot)


    }

    private fun btnNextOnClick() {
        if (currentPage < adapter.count - 1)
            vp_main.currentItem = currentPage + 1
        else {
            val intent = Intent(this@MainActivity, LogInActivity::class.java)

            val pairs = arrayOf(
                    Pair<View, String>(iv_logo, "ivUserTransition"),
                    Pair<View, String>(slider_rectangle, "rectangleTransition")
            )

            val options = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, *pairs)
            startActivity(intent, options.toBundle())
        }
    }

    private fun btnPrevOnClick() {
        if (currentPage > 0)
            vp_main.currentItem = currentPage - 1
    }

    inner class OnPageChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            addDotIndicator(position)
        }

        override fun onPageSelected(position: Int) {
            currentPage = position

            if (position == 0) {
                disableButton(btn_prev)
                enableButton(btn_next)

                btn_next.text = getString(R.string.text_next)
            } else {
                enableButton(btn_prev)
                btn_next.text = getString(R.string.text_next)

                if (position == adapter.count - 1)
                    btn_next.text = getString(R.string.text_start)
            }
        }
    }
}

