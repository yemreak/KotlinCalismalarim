package com.yemreak.iuapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_follow_lessons.*
import kotlinx.android.synthetic.main.fragment_follow_lessons.view.*

/**
 * Created by yedehrab on 3/13/18.
 */
class FallowLessonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_follow_lessons, container,  false)

        view.fc_1.setOnClickListener { view.fc_1.toggle(false) }

        return view
    }
}