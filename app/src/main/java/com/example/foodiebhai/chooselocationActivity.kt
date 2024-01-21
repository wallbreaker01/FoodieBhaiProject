package com.example.foodiebhai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.foodiebhai.databinding.ActivityChooselocationBinding
import com.example.foodiebhai.databinding.ActivitySignBinding

class chooselocationActivity : AppCompatActivity() {
    private val binding: ActivityChooselocationBinding by lazy{
        ActivityChooselocationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val locationlist : Array<String> = arrayOf("SUST","Akhalia","Surma","BGB Gate","Topobun")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationlist)
        val autoCompleteTextView = binding.listOfLocation
        autoCompleteTextView.setAdapter(adapter)
    }
}