package com.example.foodiebhai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Toast
import com.example.foodiebhai.databinding.ActivitySignBinding
import com.example.foodiebhai.databinding.ActivityStartBinding
import com.example.foodiebhai.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignActivity : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var password : String
    private lateinit var userName : String
    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference
    private lateinit var googleSignInClint : GoogleSignInClient


    private val binding: ActivitySignBinding by lazy{
        ActivitySignBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       // val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        //initialize firebase auth
        auth = Firebase.auth


        //initialize firebase Database
        database = Firebase.database.reference
        //googleSignInClint = GoogleSignIn.getClient(this,googleSignInOptions)


        binding.createAccountButton.setOnClickListener{
            userName = binding.userName.text.toString()
            email = binding.emailAddress.text.toString().trim()
            password = binding.passwordButton.text.toString().trim()

            if(email.isEmpty() || password.isBlank() || userName.isBlank()){
                Toast.makeText(this,"Please Fill all the details",Toast.LENGTH_SHORT).show()

            }
            else{
                CreateAccount(email,password)
            }
        }
        binding.alreadyhavebutton.setOnClickListener{
            val intent = Intent(this,LoginActivity:: class.java)
            startActivity(intent)
        }
    }

    private fun CreateAccount(email: String, password: String) {
       auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
           task->
           if(task.isSuccessful){
               Toast.makeText(this,"Account created successfully",Toast.LENGTH_SHORT).show()
               saveUserData()
               startActivity(Intent(this,LoginActivity::class.java))
               finish()
           }
           else
           {
               Toast.makeText(this,"Account creation failed",Toast.LENGTH_SHORT).show()
               Log.d("Account","CreateAccount : Failure",task.exception)
           }
       }
    }

    private fun saveUserData() {
        //retreve input data
        userName = binding.userName.text.toString()
        password = binding.passwordButton.text.toString().trim()
        email = binding.emailAddress.text.toString().trim()

        val user = UserModel(userName,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        //save data to firebase
        database.child("user").child(userId).setValue(user)
    }
}