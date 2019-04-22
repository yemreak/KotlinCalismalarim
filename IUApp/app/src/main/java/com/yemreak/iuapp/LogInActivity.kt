package com.yemreak.iuapp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.text.Layout
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.parse.LogInCallback
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        bindEvents()
    }

    private fun bindEvents() {
        ib_logIn.setOnClickListener { logInAsUser() }
        ib_guest.setOnClickListener{ logInAsGuest() }
    }

    private fun logInAsUser() {
        val userNo = et_userNo.text.toString()
        val password = et_password.text.toString()

        try {
            ParseUser.logInInBackground(userNo, password, { user, e ->
                if (user != null) {
                    Toast.makeText(this, "Başarılı", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                    showSignUpDialog()

                }
            })
        } catch (e: Exception) {
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }

    }

    private fun logInAsGuest(){
        val intent = Intent(this@LogInActivity, DepartmentAct::class.java)
        startActivity(intent)
    }

    /**
     * Kayıt olmak için alert dialog gösterir.
     */
    private fun showSignUpDialog() {
        val btn = Button(this)

        with (btn){
            text = getString(R.string.text_click_here)
            width = 80
            textSize = 15f
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            setOnClickListener { otherSignInDialog() }
        }

        val alertDialog = AlertDialog.Builder(this@LogInActivity)
                .setTitle(getString(R.string.title_alert_sign_up))
                .setMessage(R.string.msg_alert_sign_up)
                .setPositiveButton(R.string.text_button_yes_alert_sign_up, { dialog, which ->
                    Toast.makeText(this, "Lütfen biraz bekle", Toast.LENGTH_SHORT).show()
                })
                .setNegativeButton(R.string.text_button_no_alert_sign_up, { dialog, which ->
                    Toast.makeText(this, "Nasıl istersen", Toast.LENGTH_SHORT).show()
                }).create()

        with (alertDialog){
            setView(btn)
            show()
        }


    }

    /**
     * Öğrenci no ve şifre barlarının olduğu alert dialog gösterir.
     */
    private fun otherSignInDialog(){

        val userNo = EditText(this) // Eklenecek öğrenci no bar'ı
        with (userNo){
            hint = getString(R.string.hint_user_no)
            inputType = InputType.TYPE_CLASS_NUMBER // Klavyeden sadece sayı girişini sağlamak için.
            textAlignment = View.TEXT_ALIGNMENT_CENTER // Metni ortalamak için.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // API kontrolü
                setTextAppearance(R.style.TextAppearance_AppCompat_Body2) // Font'u ayarlamak için.
            }
        }

        val password = EditText(this) // Eklenecek şifre bar'ı
        with (password){
            hint = getString(R.string.hint_password)
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD // Klavyede öneri vermesini engellemek için.
            transformationMethod = PasswordTransformationMethod.getInstance() // Görünürlüğünü kapatmak için.
            textAlignment = View.TEXT_ALIGNMENT_CENTER // Metni ortalamak için.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // API kontrolü
                setTextAppearance(R.style.TextAppearance_AppCompat_Body2) // Font'u ayarlamak için.
            }
        }

        val layout = LinearLayout(this) // Eklenecek bar'ları düzende tutmak için gereken layout.
        with(layout){
            orientation = LinearLayout.VERTICAL // Layout düzeni ayarlama
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT) // Layout'un boyutu
            addView(userNo) // View'lerin eklenmesi.
            addView(password)
        }

        val alertDialog = AlertDialog.Builder(this@LogInActivity)
                .setTitle(R.string.title_alert_sign_up2)
                .setMessage(R.string.msg_alert_sign_up2)
                .setPositiveButton(R.string.text_button_yes_alert_sign_up2, { dialog, which ->
                    Toast.makeText(this, "Lütfen biraz bekle", Toast.LENGTH_SHORT).show()
                })
                .setNegativeButton(R.string.text_button_no_alert_sign_up2, { dialog, which ->
                    Toast.makeText(this, "Nasıl istersen", Toast.LENGTH_SHORT).show()
                }).create()

        with (alertDialog){
            setView(layout)
            show()
        }
    }
}
