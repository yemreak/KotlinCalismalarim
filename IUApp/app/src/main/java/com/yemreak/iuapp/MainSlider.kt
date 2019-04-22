package com.yemreak.iuapp

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_log_in.view.*
import kotlinx.android.synthetic.main.slider_main.*
import kotlinx.android.synthetic.main.slider_main.view.*

/**
 * Kaydırmalı aktivite için gereken bağlayıcı yapısı
 */
class MainSlider (private val context: Context) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    private val intArr_images = arrayListOf( // Değişecek olan resimlerin dizisi
            R.mipmap.logo_iu,
            R.mipmap.img_me
    )

    private val intArr_rectangles = arrayListOf( // Değişecek olan dörtgenlerin dizisi
            R.mipmap.rectangle1,
            R.mipmap.rectangle1
    )

    private val intArr_textsOfRectangle = arrayListOf( // Değişecek olan metinlerin dizisi
            R.string.text_main_slide1_rectangle,
            R.string.text_main_slide2_rectangle
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view == `object` as RelativeLayout) // ViewPager'ın layoutu (slider_main.xml)
    }

    override fun getCount(): Int {
        return intArr_images.size // ViewPager ile değişecek olan toplam sayfa sayısı
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = layoutInflater.inflate(R.layout.slider_main, container, false)

        view.iv_logo.setImageResource(intArr_images[position])
        view.iv_rectangle.setImageResource(intArr_rectangles[position])
        view.tv_rectagnle.setText(intArr_textsOfRectangle[position])

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout) // Sayfa değişince arkadakileri temizliyoruz.
    }
}