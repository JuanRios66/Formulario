package com.juanrios66.formulario

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juanrios66.formulario.databinding.ActivityMainBinding

private const val EMPTY = ""
private const val SPACE = " "
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.saveButton.setOnClickListener {
            val email = mainBinding.emailText.text.toString()
            val password = mainBinding.passText.text.toString()
            val repeatpass = mainBinding.repeatPassText.text.toString()
            val genre = if (mainBinding.femaleRadiobutt.isChecked) getString(R.string.female) else getString(R.string.male)
            var hobbies = if(mainBinding.dancecheckBox.isChecked) getString(R.string.Dance) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.eatcheckBox.isChecked) getString(R.string.Eat) else EMPTY
            hobbies = hobbies + SPACE + if(mainBinding.SportcheckBox.isChecked) getString(R.string.Sport) else EMPTY
            hobbies = hobbies + SPACE + if(mainBinding.readcheckBox.isChecked) getString(R.string.Read) else EMPTY

            if (email.isNotEmpty()) {
                if (password == repeatpass) {
                    saveUser(email, password, genre, hobbies)
                    mainBinding.repeatPass.error = null
                } else {
                    mainBinding.repeatPass.error = getString(R.string.error)
                }
            } else {
                Toast.makeText(this, getString(R.string.empty), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun saveUser(email: String, password: String, genre: String, hobbies: String) {
        val newUser = user(email, password, genre, hobbies)
        mainBinding.textView2.text = newUser.email + "\n" + newUser.genre + "\n" + newUser.hobbies
    }
}