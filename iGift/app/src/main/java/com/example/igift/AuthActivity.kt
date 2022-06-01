package com.example.igift

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONTokener
import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.igift.databinding.ActivityAuthBinding
import com.example.igift.databinding.FragmentProfileBinding
import com.example.igift.services.NetworkConnection
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException


class AuthActivity : AppCompatActivity() {

    //Data Store
    private lateinit var dataStore: DataStore<Preferences>

    //Binding
    private lateinit var binding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        // Binding
        binding = ActivityAuthBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        //Local Storage
        dataStore = createDataStore(name = "settings")

        // Eventual connectivity
        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, Observer { isConnected ->
            if(!isConnected){
                Log.v("CON","Is  Not Connected")
                Toast.makeText(applicationContext,"Connection Lost",Toast.LENGTH_SHORT).show()

                setContentView(R.layout.disconection)

            }
            else {
                Log.v("CON", "Is  Connected")
                setContentView(binding.root)

                binding.signupImage.setImageResource(R.drawable.login)
                Toast.makeText(applicationContext,"Connected to Network",Toast.LENGTH_SHORT).show()

                //Local Storage
                lifecycleScope.launch{
                    val emailEditText = binding.emailEditText
                    val value = read("email")
                    emailEditText.setText(value?: "" , TextView.BufferType.EDITABLE);
                }

                //LogIn
                login(binding)
                //SignUp
                signup(binding)

            }
        })
    }

    //Hacer Login
    private fun login(binding: ActivityAuthBinding){
        var loginButton = binding.loginButton
        var emailEditText = binding.emailEditText
        var passwordEditText = binding.passwordEditText

        loginButton.setOnClickListener{

            //Verificacion Firebase
            if( emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()  ){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful) {

                        Toast.makeText(applicationContext,"Log In Successfull",Toast.LENGTH_LONG).show()
                        Toast.makeText(applicationContext,"Email Saved",Toast.LENGTH_LONG).show()

                        //Save to local storage
                        lifecycleScope.launch{
                            save(
                                "email",
                                emailEditText.text.toString()
                            )
                        }
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

    //En caso de que se desee registrar (Signup)
    //En caso de que desee hacer log in en vez de registrarse
    private fun signup(binding: ActivityAuthBinding){
        val signupText = binding.signupText
        signupText.setOnClickListener{
            val loginIntent = Intent(this, SignupActivity::class.java).apply {
            }
            startActivity(loginIntent)
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

    //Mostrar el home de la APP
    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }

    //Obtener JSOP
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

    //Local Storage Save Function
    private suspend fun save(key:String, value:String){
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
        Log.d("SAVED",value)
    }

    //Local Storage Read Function
    private suspend fun read(key:String): String?{
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}