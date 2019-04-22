package com.yemreak.iuapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ramotion.circlemenu.CircleMenuView
import kotlinx.android.synthetic.main.activity_department.*
import android.widget.Toast


class DepartmentAct : AppCompatActivity() {
    private var isSelected = arrayListOf(false, false, false, false, false)
    private var departmentNo : Int? = null
    private val departmentName = arrayListOf("Bilgisayar Muh.", "Endüstri Müh.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department)

        bindEvents()
    }

    private fun bindEvents(){
        cm_department.eventListener = CircleMenuListener()
        ib_pre.setOnClickListener { buttonOnClick(0) }
        ib_one.setOnClickListener { buttonOnClick(1) }
        ib_two.setOnClickListener { buttonOnClick(2) }
        ib_three.setOnClickListener { buttonOnClick(3) }
        ib_four.setOnClickListener { buttonOnClick(4) }
    }

    private fun buttonOnClick(index : Int){
        when (index){
            0 -> {
                if (isSelected[index]) {
                    ib_pre.setImageResource(R.mipmap.ic_pre_1)
                    isSelected[index] = false
                    return
                } else {
                    ib_pre.setImageResource(R.mipmap.ic_pre_2)
                    isSelected[index] = true
                    return
                }
            }
            1 -> {
                if (isSelected[index]) {
                    ib_one.setImageResource(R.mipmap.ic_one_1)
                    isSelected[index] = false
                    return
                } else {
                    ib_one.setImageResource(R.mipmap.ic_one_2)
                    isSelected[index] = true
                    return
                }
            }
            2 -> {
                if (isSelected[index]) {
                    ib_two.setImageResource(R.mipmap.ic_two_1)
                    isSelected[index] = false
                    return
                } else {
                    ib_two.setImageResource(R.mipmap.ic_two_2)
                    isSelected[index] = true
                    return
                }
            }
            3 -> {
                if (isSelected[index]) {
                    ib_three.setImageResource(R.mipmap.ic_three_1)
                    isSelected[index] = false
                    return
                } else {
                    ib_three.setImageResource(R.mipmap.ic_three_2)
                    isSelected[index] = true
                    return
                }
            }
            4 -> {
                if (isSelected[index]) {
                    ib_four.setImageResource(R.mipmap.ic_four_2)
                    isSelected[index] = false
                    return
                } else {
                    ib_four.setImageResource(R.mipmap.ic_four_1)
                    isSelected[index] = true
                    return
                }
            }
        }
    }

    inner class CircleMenuListener : CircleMenuView.EventListener() {
        override fun onMenuOpenAnimationStart(view: CircleMenuView) {

        }

        override fun onMenuOpenAnimationEnd(view: CircleMenuView) {

        }

        override fun onMenuCloseAnimationStart(view: CircleMenuView) {

        }

        override fun onMenuCloseAnimationEnd(view: CircleMenuView) {

        }

        override fun onButtonClickAnimationStart(view: CircleMenuView, index: Int) {
            departmentNo = index

        }

        override fun onButtonClickAnimationEnd(view: CircleMenuView, index: Int) {
            startActivity(Intent(this@DepartmentAct, Main2Activity::class.java))
        }

    }
}
