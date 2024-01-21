package com.example.foodiebhai.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodiebhai.R
import com.example.foodiebhai.adapter.BuyAgainAdapter
import com.example.foodiebhai.databinding.FragmentHistoryBinding
import com.example.foodiebhai.databinding.FragmentSearchBinding


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter : BuyAgainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHistoryBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        setupRecyclerView()
        return binding.root
    }
    private fun setupRecyclerView(){
        val buyAgainFoodName = arrayListOf("Food 1","Food 2","Food 3","Food 4","Food 5")
        val buyAgainFoodPrice = arrayListOf("$5","$3","$10","$12","$9")
        val buyAgainFoodImage = arrayListOf(R.drawable.menu_photo,R.drawable.menu_photo__4_,R.drawable.menu_photo__2_,R.drawable.photo_menu,R.drawable.menu_photo__3_)
        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.BuyAgainRecyclerView.adapter = buyAgainAdapter
        binding.BuyAgainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {

    }
}