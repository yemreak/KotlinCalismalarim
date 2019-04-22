package com.yemreak.iuapp

import android.app.Application
import com.parse.Parse
import com.parse.ParseConfig
import com.parse.ParseUser

/**
 * Uygulama ilk açıldığında çalışan class
 */
class StarterParse : Application() {

    override fun onCreate() {
        super.onCreate()

        parseOperation()

    }

    /**
     * Parse'i uygulmaya bağlamak için gereken kodlar.
     */
    private fun parseOperation() {
        Parse.enableLocalDatastore(this)

        Parse.initialize(Parse.Configuration.Builder(applicationContext)
                .applicationId(getString(R.string.parse_app_id))
                .server(getString(R.string.parse_server))
                .clientKey(getString(R.string.parse_master_key))
                .build())

        ParseUser.enableAutomaticUser()
    }

}