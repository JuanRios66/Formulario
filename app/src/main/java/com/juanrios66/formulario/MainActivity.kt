package com.juanrios66.formulario

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juanrios66.formulario.databinding.ActivityMainBinding

private const val EMPTY = ""
private const val SPACE = " "
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var users: MutableList<user> = mutableListOf()

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
            clearViews()
        }

    }

    private fun clearViews() {
        with(mainBinding){
            emailText.setText(EMPTY)
            passText.setText(EMPTY)
            repeatPassText.setText(EMPTY)
            femaleRadiobutt.isChecked = true
            dancecheckBox.isChecked = false
            SportcheckBox.isChecked = false
            eatcheckBox.isChecked = false
            readcheckBox.isChecked = false
        }
    }

    private fun saveUser(email: String, password: String, genre: String, hobbies: String) {
        val newUser = user(email, password, genre, hobbies)
        users.add(newUser)
        printUserData()

    }

    private fun printUserData() {
        var info =""
        for (user in users){
            info = info + "\n\n" + user.email + "\n" + user.genre + "\n" + user.hobbies
        }
        mainBinding.textView2.text = info
    }
}