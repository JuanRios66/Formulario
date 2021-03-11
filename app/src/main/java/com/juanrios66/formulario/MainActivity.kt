package com.juanrios66.formulario

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.juanrios66.formulario.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        private lateinit var mainBinding = ActivityMainBinding

        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.save_Button.setOnClickListener{it:View
            var email = mainBinding.email_Text.text.toString()
            var password = mainBinding.pass_text.text.toString()
            var repeat_pass = mainBinding.repeat_pass_text.text.toString()


        }


    }
}