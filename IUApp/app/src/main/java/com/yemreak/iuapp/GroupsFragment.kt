package com.yemreak.iuapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_groups_main2.view.*

/**
 * Created by yedehrab on 3/6/18.
 */
class GroupsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_groups_main2, container, false)

        view.fc_gruop1.setOnClickListener { view.fc_gruop1.toggle(false) }
        return view
    }
}