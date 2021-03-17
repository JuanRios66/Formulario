package com.juanrios66.formulario

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juanrios66.formulario.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

private const val EMPTY = ""
private const val SPACE = " "


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var users: MutableList<user> = mutableListOf()
    private var fecha: String = ""
    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val formato = SimpleDateFormat("dd-MM-yyyy", Locale.US)
            fecha = formato.format(cal.time).toString()
            mainBinding.dateBirth.setText(fecha)
        }

        mainBinding.dateBirth.setOnClickListener {
            DatePickerDialog(this,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        mainBinding.saveButton.setOnClickListener {
            val name = mainBinding.nameText.text.toString()
            val email = mainBinding.emailText.text.toString()
            val password = mainBinding.passText.text.toString()
            val repeatpass = mainBinding.repeatPassText.text.toString()
            val genre = if (mainBinding.femaleRadiobutt.isChecked) getString(R.string.female) else getString(R.string.male)
            var hobbies = if (mainBinding.dancecheckBox.isChecked) getString(R.string.Dance) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.eatcheckBox.isChecked) getString(R.string.Eat) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.SportcheckBox.isChecked) getString(R.string.Sport) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.readcheckBox.isChecked) getString(R.string.Read) else EMPTY

            if (name.isNotEmpty() and email.isNotEmpty() and password.isNotEmpty()) {
                if (password == repeatpass) {
                    saveUser(name, email, password, genre, hobbies, fecha)
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
        with(mainBinding) {
            nameText.setText(EMPTY)
            emailText.setText(EMPTY)
            passText.setText(EMPTY)
            repeatPassText.setText(EMPTY)
            femaleRadiobutt.isChecked = true
            dancecheckBox.isChecked = false
            SportcheckBox.isChecked = false
            eatcheckBox.isChecked = false
            readcheckBox.isChecked = false
            dateBirth.setText(EMPTY)
        }
    }

    private fun saveUser(name: String, email: String, password: String, genre: String, hobbies: String, fecha:String) {
        val newUser = user(name, email, password, genre, hobbies, fecha)
        users.add(newUser)
        printUserData()

    }

    private fun printUserData() {
        var info = ""
        for (user in users) {
            info = info + "\n\n" + user.name+"\n" + user.email + "\n"+user.fecha +"\n" + user.genre + "\n" + user.hobbies
        }
        mainBinding.textView2.text = info
    }

}