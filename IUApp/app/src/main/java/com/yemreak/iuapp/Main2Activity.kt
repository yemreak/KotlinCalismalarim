package com.yemreak.iuapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main2.*

class Main2Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        replaceFragment() // Ana içeriği ekrana yerleştirme


        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.text_nav_drawer_open, R.string.text_nav_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        replaceFragment(item)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * Başlığın ve içeriğin (frame'in) değiştirilmesi
     */
    private fun replaceFragment(item: MenuItem) {
        val fragment = when (item.itemId) {
            R.id.nav_announcements -> {
                title = getString(R.string.title_announcements_main2_drawer)
                AnnouncementFragment()
            }
            R.id.nav_groups -> {
                title = getString(R.string.title_groups_main2_drawer)
                GroupsFragment()
            }
            R.id.nav_lessons -> {
                title = getString(R.string.title_lessons_main2_drawer)
                LessonsFragment()
            }
            R.id.nav_follow_lessons -> {
                title = getString(R.string.title_follow_lesson_drawer)
                FallowLessonFragment()
            }
            R.id.nav_organizations -> {
                title = getString(R.string.title_organizations_main2_drawer)
                OrganizationsFragment()
            }
            else -> {
                title = getString(R.string.title_main2_activity)
                HomeMain2Fragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fl_main2, fragment).commit()
    }

    /**
     * Ana içeriğin eklenmesi
     */
    private fun replaceFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.fl_main2, HomeMain2Fragment()).commit()
    }
}
