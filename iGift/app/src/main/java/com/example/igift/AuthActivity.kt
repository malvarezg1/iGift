package com.example.igift

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONTokener
import android.content.Context
import java.io.File
import java.io.IOException


class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        //JSON

        //LogIn
        login()
    }

    private fun login(){
        var loginButton = findViewById<Button>(R.id.loginButton)
        var emailEditText = findViewById<EditText>(R.id.emailEditText)
        var passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        loginButton.setOnClickListener{
            if( emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()  ){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful) {
                        showHome(it.result?.user?.email ?:"",ProviderType.BASIC)
                    }else{
                        print(emailEditText.text.toString())
                        print(passwordEditText.text.toString())
                        showAlert()

                    }
                }
            }
        }
    }

    //Mostrar Alerta en caso de Error
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error al autenticar un usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


}