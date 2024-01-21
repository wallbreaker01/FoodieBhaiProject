package com.example.foodiebhai

import android.os.Binder
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.foodiebhai.adapter.MenuAdapter
import com.example.foodiebhai.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentMenuBottomSheetBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater,container,false)

        binding.buttonBack.setOnClickListener {
            dismiss()
        }

        val menuFoodName = listOf("Burger","Sandwich","Momo","Item","french fry","Momo","Item","french fry","Momo")
        val menuItemPrice = listOf("$5","$6","$8","$10","$6","$5","$10","$6","$5")
        val menuImage = listOf(R.drawable.menu_photo,R.drawable.photo_menu,R.drawable.menu_photo__4_,R.drawable.menu_photo__3_,R.drawable.menu_photo__2_,R.drawable.menu_photo__4_,R.drawable.menu_photo__3_,R.drawable.menu_photo__2_,R.drawable.menu_photo__4_)
        val adapter = MenuAdapter(ArrayList(menuFoodName),ArrayList(menuItemPrice),ArrayList(menuImage),requireContext())
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter
        return binding.root

    }

    companion object {

    }
}