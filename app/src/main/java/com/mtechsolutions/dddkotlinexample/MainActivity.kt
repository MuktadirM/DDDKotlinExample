package com.mtechsolutions.dddkotlinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mtechsolutions.dddkotlinexample.databinding.ActivityMainBinding
import com.mtechsolutions.dddkotlinexample.domain.auth.AuthFailure
import com.mtechsolutions.dddkotlinexample.domain.auth.EmailAddress
import com.mtechsolutions.dddkotlinexample.domain.auth.EmailAddress.Companion.emailAddress
import com.mtechsolutions.dddkotlinexample.domain.auth.Password
import com.mtechsolutions.dddkotlinexample.domain.auth.Password.Companion.password
import com.mtechsolutions.dddkotlinexample.domain.auth.UniqueId.Companion.uniqueId

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object val TAG = "MainActivity"

        var mref = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: ")

        binding.name.text = "Muktadir";

        mref = "Hello";
       val email : EmailAddress = emailAddress("emdmd")
        val  pass : Password = password("232");

   Log.d(TAG,"Valid "+email.isValid())
        val failure = AuthFailure.CancelledByUser("");
        Log.d(TAG, "onCreate: "+uniqueId().value.right().get())

    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart: "+mref)

    }

    fun getErrorMsg(authFailure: AuthFailure<String>):Any{
        return when(authFailure){
            is AuthFailure.InvalidEmailAndPasswordCombination -> AuthFailure.InvalidEmailAndPasswordCombination("Wrong Email or Password");
            is AuthFailure.EmailAlreadyInUse -> AuthFailure.EmailAlreadyInUse("Email Already in Use")
            is AuthFailure.ServerError ->AuthFailure.ServerError("Sever Error")
            is AuthFailure.CancelledByUser -> AuthFailure.CancelledByUser("Login cancel")
        }
    }
}