package com.example.foodiebhai.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodiebhai.R
import com.example.foodiebhai.adapter.MenuAdapter
import com.example.foodiebhai.databinding.FragmentSearchBinding
import com.example.foodiebhai.databinding.MenuItemBinding


class SearchFragment : Fragment() {
private lateinit var binding: FragmentSearchBinding
private lateinit var adapter : MenuAdapter
private val originalMenuFoodName = listOf("Burger","Sandwich","Momo","Item","french fry","Momo","Item","french fry","Momo")
    private val originalMenuItemPrice = listOf("$5","$6","$8","$10","$6","$5","$10","$6","$5")
    private val originalMenuImage = listOf(R.drawable.menu_photo,R.drawable.photo_menu,R.drawable.menu_photo__4_,R.drawable.menu_photo__3_,R.drawable.menu_photo__2_,R.drawable.menu_photo__4_,R.drawable.menu_photo__3_,R.drawable.menu_photo__2_,R.drawable.menu_photo__4_)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val filterMenuFoodName = mutableListOf<String>()
    private val filterMenuFoodItemPrice = mutableListOf<String>()
    private val filterMenuFoodImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        adapter = MenuAdapter(filterMenuFoodName,filterMenuFoodItemPrice,filterMenuFoodImage,requireContext())
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        //setup for search viwe

        setupSearchView()

        //show all menu items
        showAllMenu()


        return binding.root
    }

    private fun showAllMenu() {
        filterMenuFoodName.clear()
        filterMenuFoodItemPrice.clear()
        filterMenuFoodImage.clear()

        filterMenuFoodName.addAll(originalMenuFoodName)
        filterMenuFoodItemPrice.addAll(originalMenuItemPrice)
        filterMenuFoodImage.addAll(originalMenuImage)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener { ///
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })

    }

    private fun filterMenuItems(query: String) {
        filterMenuFoodName.clear()
        filterMenuFoodItemPrice.clear()
        filterMenuFoodImage.clear()

        originalMenuFoodName.forEachIndexed { index, foodName ->
            if (foodName.contains(query, ignoreCase = true)) {
                filterMenuFoodName.add(foodName)
                filterMenuFoodItemPrice.add(originalMenuItemPrice[index])
                filterMenuFoodImage.add(originalMenuImage[index])
            }

        }
        adapter.notifyDataSetChanged()
    }

    companion object {

    }
}