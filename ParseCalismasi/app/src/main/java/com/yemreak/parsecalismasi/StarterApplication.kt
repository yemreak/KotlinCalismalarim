package com.yemreak.parsecalismasi

import android.app.Application
import com.parse.Parse
import com.parse.ParseACL
import com.parse.ParseUser

class StarterApplication : Application() { // Olası sorunda app'i sil yeniden başlat.
    override fun onCreate() {
        super.onCreate()

        Parse.initialize(Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.parse_app_id))
                .server(getString(R.string.parse_server))
                .clientKey(getString(R.string.parse_master_key))
                .enableLocalDataStore()
                .build())

        ParseUser.enableAutomaticUser()

        val defaultACL = ParseACL()
        defaultACL.publicWriteAccess = true
        defaultACL.publicReadAccess = true
        ParseACL.setDefaultACL(defaultACL, true)
    }
}