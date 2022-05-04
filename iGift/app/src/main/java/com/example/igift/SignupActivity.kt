package com.example.igift
import android.util.Log
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Register
        register()

        //Login
        login()
    }

    private fun register(){

        var signupButton = findViewById<Button>(R.id.signupButton)
        var editTextTextPersonName = findViewById<EditText>(R.id.editTextTextPersonName)
        var emailEditText = findViewById<EditText>(R.id.emailEditText)
        var passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        var confirmpasswordEditText = findViewById<EditText>(R.id.confirmpasswordEditText)

        signupButton.setOnClickListener{
            if(editTextTextPersonName.text.isNotEmpty() && emailEditText.text.isNotEmpty()
                && passwordEditText.text.isNotEmpty()  && confirmpasswordEditText.text.isNotEmpty() &&
                passwordEditText.text.toString() == confirmpasswordEditText.text.toString()
                ){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful) {
                        showPreferences(it.result?.user?.email ?:"",editTextTextPersonName.text.toString())
                    }else{
                        showAlert()
                    }
                }
            }else{
                showAlert()
            }
        }


    }

    //Mostrar Alerta en caso de Error
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error al registrar un usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // En caso de que se haya registrado correctamente mostrar el home de la app
    private fun showPreferences(email: String, name: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registro Realizado")
        builder.setMessage("Se ha registrado correctamente con el email: $email")
        val dialog: AlertDialog = builder.create()
        dialog.show()

        val preferencesIntent = Intent(this, Preferences::class.java).apply {

            putExtra("email", email)
            putExtra("name", name)

        }
        Thread.sleep(5_000)
        startActivity(preferencesIntent)
    }

    //En caso de que desee hacer log in en vez de registrarse
    private fun login(){
        var loginTextView = findViewById<TextView>(R.id.loginTextView)
        loginTextView.setOnClickListener{
            val loginIntent = Intent(this, AuthActivity::class.java).apply {
            }
            startActivity(loginIntent)
        }
    }





}