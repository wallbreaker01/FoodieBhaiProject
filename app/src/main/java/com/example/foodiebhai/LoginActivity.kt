package com.example.foodiebhai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodiebhai.databinding.ActivityLoginBinding
import com.example.foodiebhai.databinding.ActivityStartBinding
import com.example.foodiebhai.model.UserModel
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private var username : String ?= null
    private lateinit var email : String
    private lateinit var password: String
    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference


    private val binding: ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialization of firebase auth
        auth = Firebase.auth

        //initialization of firebase database
        database = Firebase.database.reference

        //log in with email and password



        binding.loginButton.setOnClickListener{
            //get data from text field

            email = binding.emailaddress.text.toString().trim()
            password = binding.password.text.toString().trim()

            if(email.isBlank() || password.isBlank()){
                Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show()
            }
            else{
                createUser()
                Toast.makeText(this,"Log in successful",Toast.LENGTH_SHORT).show()
            }


        }
        binding.donthavebutton.setOnClickListener{
            val intent = Intent(this,SignActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUser() {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            task->
            if(task.isSuccessful){
                val user = auth.currentUser
                updateUI(user)
            }
            else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    task ->
                    if(task.isSuccessful){
                        saveUserData()
                        val user = auth.currentUser
                        updateUI(user)
                    }
                    else{
                        Toast.makeText(this,"Sign in failed",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

    }

    private fun saveUserData() {
        //get data from text field


        email = binding.emailaddress.text.toString().trim()
        password = binding.password.text.toString().trim()
        val user = UserModel(username,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        //save data in firebase
        database.child("user").child(userId).setValue(user)


    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}