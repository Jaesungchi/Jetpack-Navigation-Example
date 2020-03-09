package com.kotlin.jaesungchi.jetpack_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class FirstFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_screen,container,false)
        view.findViewById<Button>(R.id.to_second_from_first).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_first_screen_to_second_screen)
        }
        view.findViewById<Button>(R.id.to_third_from_first).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_first_screen_to_third_screen)
        }
        return view
    }
}