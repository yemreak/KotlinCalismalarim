package com.yemreak.snakegame

import android.app.Activity
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Display

class SnakeActivity : Activity() {

    // Snake engine adlı değişkeni oluşturuyoruz.
    lateinit var snakeEngine: SnakeEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        // Ekranın pixel boyutlarını alıyoruz
        val display = windowManager.defaultDisplay

        // Sonucu Point objesine tanımlıyoruz
        val size = Point()
        display.getSize(size)

        // Oyunumuzun motorunu çalıştırıyoruz
        snakeEngine = SnakeEngine(this, size)

        // SnakeEngine'i activity'mizin view'i yapıyoruz
        setContentView(snakeEngine)
    }

    override fun onResume() {
        super.onResume()
        snakeEngine.resume()
    }

    override fun onPause() {
        super.onPause()
        snakeEngine.pause()
    }
}
