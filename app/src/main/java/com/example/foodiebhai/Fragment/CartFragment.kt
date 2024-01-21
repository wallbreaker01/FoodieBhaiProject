package com.example.foodiebhai.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodiebhai.CongratsBottomSheet
import com.example.foodiebhai.PayOutActivity
import com.example.foodiebhai.R
import com.example.foodiebhai.adapter.CartAdapter
import com.example.foodiebhai.databinding.CartItemBinding
import com.example.foodiebhai.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)


        val cartFoodName = listOf("Burger","Sandwich","Momo","Item","french fry","Momo")
        val cartItemPrice = listOf("$5","$6","$8","$10","$6","$5")
        val cartImage = listOf(R.drawable.menu_photo,R.drawable.photo_menu,R.drawable.menu_photo__4_,R.drawable.menu_photo__3_,R.drawable.menu_photo__2_,R.drawable.menu_photo__4_)
        val adapter = CartAdapter(ArrayList(cartFoodName),ArrayList(cartItemPrice),ArrayList(cartImage))
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        binding.proceedButton.setOnClickListener{
            val intent = Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }




        return binding.root
    }
    companion object {

    }
}