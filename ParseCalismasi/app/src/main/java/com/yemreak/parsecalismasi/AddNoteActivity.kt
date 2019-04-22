package com.yemreak.parsecalismasi

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

var globalTitle = ""
var globalDetail = ""
lateinit var globalImage : Bitmap

class AddNoteActivity : AppCompatActivity() {

    lateinit var choosenImage : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        bindEvents()
    }

    private fun bindEvents(){
        iv_image.setOnClickListener { ibImageOnClickListener() }
        btn_next.setOnClickListener { btnNextOnClickListener() }
    }

    private fun ibImageOnClickListener(){
        permissionOperations()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun permissionOperations(){
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 2)
        else {
            startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            2 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode){
            1 -> {
                if (resultCode == Activity.RESULT_OK && data != null){
                    try {
                        choosenImage = MediaStore.Images.Media.getBitmap(this.contentResolver, data.data)
                        iv_image.setImageBitmap(choosenImage)
                    } catch (e : Exception){
                        Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun btnNextOnClickListener() {
        globalTitle = et_title.text.toString()
        globalDetail = et_detail.text.toString()
        globalImage = choosenImage

        startActivity(Intent(applicationContext, MapsActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_addnote, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.menu_addLoc -> {
                startActivity(Intent(applicationContext, MapsActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
