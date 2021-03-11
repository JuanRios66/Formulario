package com.juanrios66.formulario

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juanrios66.formulario.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.saveButton.setOnClickListener {
            var email = mainBinding.emailText.text.toString()
            var password = mainBinding.passText.text.toString()
            var repeatpass = mainBinding.repeatPassText.text.toString()

            if (email.isNotEmpty() and password.isNotEmpty()) {
                mainBinding.repeatPass.error = null
                if (password == repeatpass || repeatpass.isNotEmpty()) {
                    mainBinding.textView2.text = email
                } else {
                    mainBinding.repeatPass.error = getString(R.string.error)
                }
            } else {
                Toast.makeText(this, getString(R.string.empty), Toast.LENGTH_LONG).show()
            }
        }


    }
}