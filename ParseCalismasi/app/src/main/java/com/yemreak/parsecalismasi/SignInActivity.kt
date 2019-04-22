package com.yemreak.parsecalismasi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.parse.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.util.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        ParseAnalytics.trackAppOpenedInBackground(intent)

        bindEvents()

        // putAndSaveToParse("Fruits", "name", "apple")
        // printingDataFromParse()
    }

    private fun bindEvents() {
        btn_sign_in.setOnClickListener{ btnSignInOnClick() }
        btn_sign_up.setOnClickListener{ btnSignUpOnClick() }
    }

    private fun btnSignInOnClick() {
        ParseUser.logInInBackground(et_username.text.toString(), et_password.text.toString(), LogInCallback { user, e ->
            if (e != null)
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(applicationContext, "Giriş başarılı :)", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, LocActivity::class.java))
            }
        })
    }

    private fun btnSignUpOnClick() {
        val user = ParseUser()
        user.username = et_username.text.toString()
        user.setPassword(et_password.text.toString())
        user.signUpInBackground { e : ParseException? ->
            if (e != null)
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(applicationContext, "Kayıt olma başarılı :) ", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, LocActivity::class.java))
            }
        }
    }

    /**
     * Sunucuya veri kaydetme
     */
    private fun putAndSaveToParse(className : String, key : String, value : Any) {
        val parseObject = ParseObject(className)
        parseObject.put(key, value)
        parseObject.saveInBackground { e ->
            if (e != null)
                Toast.makeText(applicationContext, "Kaydedilemedi", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "Kaydedildi", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Veri okumma işlemleri
     */
    private fun printingDataFromParse(){
        val query = ParseQuery.getQuery<ParseObject>("Fruits")
        query.whereEqualTo("name", "apple") // Ismi apple olanları bulacak sadece. (Filtreleme)
        query.whereLessThan("calories", 140) // 140'tan düşük olan calorileri bulur sadece. (Filtreleme)
        query.findInBackground { objects, e ->
            if (e != null)
                Toast.makeText(applicationContext, "Veri almada sorun oluştu", Toast.LENGTH_SHORT).show()
            else {
                for (parseObject in objects){
                    println(parseObject["name"] as String) // Objelerde takılama yapılmak zorunda
                }
            }
        }
    }
}
