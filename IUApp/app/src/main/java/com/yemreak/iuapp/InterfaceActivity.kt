package com.yemreak.iuapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_interface.*

class InterfaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interface)

        val classes = intent.extras["classes"] as ArrayList<Boolean>
        val depertmentName = intent.extras["departmentName"] as String

        textView.text = "$depertmentName ${if(classes[0]) "0" else "other"}"
    }
}
