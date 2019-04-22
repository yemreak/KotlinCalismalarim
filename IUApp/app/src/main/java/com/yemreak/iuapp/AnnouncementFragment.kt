package com.yemreak.iuapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_announcements.*
import android.R.id.toggle
import kotlinx.android.synthetic.main.fragment_announcements.view.*


/**
 * Created by yedehrab on 3/6/18.
 */
class AnnouncementFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // OnClick eklemek için layout'u inflate etmemiz lazım
        val view = inflater.inflate(R.layout.fragment_announcements, container, false)

        view.fc_announcements2.setOnClickListener { view.fc_announcements2.toggle(false) }
        view.fc_announcements.setOnClickListener { view.fc_announcements.toggle(false) }
        return view
    }
}